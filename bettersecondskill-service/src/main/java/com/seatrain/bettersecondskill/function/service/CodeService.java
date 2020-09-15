package com.seatrain.bettersecondskill.function.service;

import com.seatrain.bettersecondskill.commons.entity.MiaoShaUser;
import java.awt.image.BufferedImage;

public interface CodeService {

  /**
   * 生成验证码图片，并将用户姓名+商品id作为key，验证码作为value存入redis中
   *
   * @param miaoShaUser 当前登录用户
   * @param goodsId 商品id
   * @return java.awt.image.BufferedImage 生成的验证码图片
   * @author seatrain
   * @date 2020/09/14 14:58
   */
  BufferedImage getVerificationCode(MiaoShaUser miaoShaUser, long goodsId);

  /**
   * 生成用户+商品对应的秒杀接口路径
   *
   * @param miaoShaUser 秒杀用户
   * @param goodsId 商品id
   * @return java.lang.String 对应的秒杀接口路径
   * @author seatrain
   * @date 2020/09/15 13:47
   */
  String getMiaoShaPath(MiaoShaUser miaoShaUser, long goodsId);

  /**
   * 通过用户+商品id组装为key，获取对应的值和输入的验证码进行比较
   *
   * @param miaoShaUser 当前登录用户
   * @param goodsId 商品id
   * @param verificationCode 验证码
   * @return boolean true 验证码正确；false 验证码错误
   * @author seatrain
   * @date 2020/09/15 15:12
   */
  boolean checkVerificationCode(MiaoShaUser miaoShaUser, long goodsId, String verificationCode);

  /**
   * 通过用户+商品id组装为key，获取对应的值和输入的秒杀路径进行比较
   *
   * @param miaoShaUser 当前登录用户
   * @param goodsId 商品id
   * @param miaoShaPath 输入的秒杀路径
   * @return boolean boolean true 秒杀路径正确；false 秒杀路径错误
   * @author seatrain
   * @date 2020/09/15 15:41
   */
  boolean checkMiaoShaPath(MiaoShaUser miaoShaUser, long goodsId, String miaoShaPath);
}
