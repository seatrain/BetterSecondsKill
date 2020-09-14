package com.seatrain.bettersecondskill.web.controller;

import com.seatrain.bettersecondskill.commons.entity.MiaoShaUser;
import com.seatrain.bettersecondskill.commons.exception.InternalException;
import com.seatrain.bettersecondskill.function.service.CodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "验证码管理")
@Slf4j
@RestController
@RequestMapping("/code")
public class CodeController {

  private CodeService codeService;

  @Autowired
  public void setCodeService(CodeService codeService) {
    this.codeService = codeService;
  }

  @ApiOperation(value = "获取二维码")
  @GetMapping(value = "/getVerificationCode", produces = MediaType.IMAGE_JPEG_VALUE)
  public void getVerificationCode(
      @RequestParam long goodsId,
      @ApiIgnore MiaoShaUser miaoShaUser,
      @ApiIgnore HttpServletResponse response
  ) {
    try {
      BufferedImage verificationCode = codeService.getVerificationCode(miaoShaUser, goodsId);
      response.setContentType("image/png");
      OutputStream outputStream = response.getOutputStream();
      ImageIO.write(verificationCode, "jpg", outputStream);
      outputStream.flush();
      outputStream.close();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new InternalException("二维码生成出错：" + e.getMessage());
    }
  }
}
