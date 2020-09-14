package com.seatrain.bettersecondskill.function.service;

import com.seatrain.bettersecondskill.commons.entity.MiaoShaUser;
import java.awt.image.BufferedImage;

public interface CodeService {

  /**
   * 生成验证码图片，并将用户姓名+商品id作为key，验证码作为value存入redis中
   *
   * @param miaoShaUser 当前登录用户
   * @param goodId 商品id
   * @return java.awt.image.BufferedImage 生成的验证码图片
   * @author seatrain
   * @date 2020/09/14 14:58
   */
  BufferedImage getVerificationCode(MiaoShaUser miaoShaUser, long goodId);
}
