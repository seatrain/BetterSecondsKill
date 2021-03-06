package com.seatrain.bettersecondskill.function.service.impl;

import com.seatrain.bettersecondskill.commons.entity.MiaoShaUser;
import com.seatrain.bettersecondskill.commons.exception.BadRequestException;
import com.seatrain.bettersecondskill.commons.utils.MD5Util;
import com.seatrain.bettersecondskill.function.redisManage.RedisClient;
import com.seatrain.bettersecondskill.function.redisManage.keysBean.CodeKey;
import com.seatrain.bettersecondskill.function.service.CodeService;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class CodeServiceImpl implements CodeService {

  // 验证码图片高
  private static final int WIDTH = 95;

  // 验证码图片宽
  private static final int HEIGHT = 25;

  // 验证码图片干扰线数量
  private static final int LINE_SIZE = 40;

  // 验证码图片字符个数
  private static final int CHAR_NUMS = 4;

  private Random random = new Random();

  //随机产生只有数字的字符串 private String
  private String RANDOM_String = "0123456789";

  private RedisClient redisClient;

  @Autowired
  public void setRedisClient(RedisClient redisClient) {
    this.redisClient = redisClient;
  }

  @Override
  public BufferedImage getVerificationCode(MiaoShaUser miaoShaUser, long goodsId) {
    // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);

    // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
    Graphics graphics = image.getGraphics();

    //图片大小
    graphics.fillRect(0, 0, WIDTH, HEIGHT);

    //字体大小
    graphics.setFont(new Font("Times New Roman", Font.PLAIN, 18));

    //字体颜色
    graphics.setColor(getRandomColor(110, 133));

    // 绘制随机字符
    for (int i = 0; i <= LINE_SIZE; i++) {
      drawLine(graphics);
    }

    // 绘制随机字符
    String randomString = "";
    for (int i = 1; i <= CHAR_NUMS; i++) {
      randomString = drawString(graphics, randomString, i);
    }

    log.info("用户：{}，商品：{}的对应验证码为：{}", miaoShaUser.getName(), goodsId, randomString);
    graphics.dispose();

    redisClient.set(CodeKey.verificationCode(), miaoShaUser.getName() + ":" + goodsId, randomString);
    return image;
  }

  private Color getRandomColor(int fc, int bc) {
    fc = Math.min(fc, 255);
    bc = Math.min(bc, 255);

    int r = fc + random.nextInt(bc - fc - 16);
    int g = fc + random.nextInt(bc - fc - 14);
    int b = fc + random.nextInt(bc - fc - 18);
    return new Color(r, g, b);
  }

  /**
   * 绘制干扰线
   */
  private void drawLine(Graphics g) {
    int x = random.nextInt(WIDTH);
    int y = random.nextInt(HEIGHT);
    int xl = random.nextInt(13);
    int yl = random.nextInt(15);
    g.drawLine(x, y, x + xl, y + yl);
  }

  /**
   * 绘制字符串
   */
  private String drawString(Graphics g, String randomString, int i) {
    g.setFont(getFont());
    g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
        .nextInt(121)));
    String rand = String.valueOf(getRandomString(random.nextInt(RANDOM_String
        .length())));
    randomString += rand;
    g.translate(random.nextInt(3), random.nextInt(3));
    g.drawString(rand, 13 * i, 16);
    return randomString;
  }

  /**
   * 获得字体
   */
  private Font getFont() {
    return new Font("Fixedsys", Font.PLAIN, 18);
  }

  /**
   * 获取随机的字符
   */
  public String getRandomString(int num) {
    return String.valueOf(RANDOM_String.charAt(num));
  }

  @Override
  public String getMiaoShaPath(MiaoShaUser miaoShaUser, long goodsId) {
    String uuid = UUID.randomUUID().toString().replace("-", "");

    String md5 = MD5Util.md5(uuid + "dbqaq123");
    redisClient.set(CodeKey.miaoShaPath(), miaoShaUser.getName() + ":" + goodsId, md5);
    log.info("用户：{}，商品：{}的对应秒杀路径为：{}", miaoShaUser.getName(), goodsId, md5);
    return md5;
  }

  @Override
  public boolean checkVerificationCode(MiaoShaUser miaoShaUser, long goodsId, String verificationCode) {
    if (StringUtils.isEmpty(verificationCode)) {
      throw new BadRequestException("验证码为空!");
    }

    String value = redisClient.get(CodeKey.verificationCode(), miaoShaUser.getName() + ":" + goodsId, String.class);

    if (verificationCode.equals(value)) {
      return true;
    }

    return false;
  }

  @Override
  public boolean checkMiaoShaPath(MiaoShaUser miaoShaUser, long goodsId, String miaoShaPath) {
    if (StringUtils.isEmpty(miaoShaPath)) {
      throw new BadRequestException("秒杀路径为空!");
    }

    String value = redisClient.get(CodeKey.miaoShaPath(), miaoShaUser.getName() + ":" + goodsId, String.class);

    if (miaoShaPath.equals(value)) {
      return true;
    }

    return false;
  }
}
