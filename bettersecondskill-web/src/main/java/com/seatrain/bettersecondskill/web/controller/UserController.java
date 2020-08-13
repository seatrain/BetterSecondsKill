package com.seatrain.bettersecondskill.web.controller;

import com.seatrain.bettersecondskill.commons.dto.LoginDTO;
import com.seatrain.bettersecondskill.commons.dto.UserDTO;
import com.seatrain.bettersecondskill.commons.entity.MiaoShaUser;
import com.seatrain.bettersecondskill.commons.exception.BadRequestException;
import com.seatrain.bettersecondskill.commons.service.MiaoShaUserService;
import com.seatrain.bettersecondskill.web.http.CustomizedResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "用户管理")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  MiaoShaUserService miaoShaUserService;

  @ApiOperation(value = "创建用户")
  @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
  public CustomizedResponseEntity<Integer> create(@Valid UserDTO userDTO) {
    if (miaoShaUserService.userNameAlreadyContains(userDTO.getName())) {
      throw new BadRequestException("该用户名已存在");
    }

    Integer id = miaoShaUserService.create(userDTO);

    return CustomizedResponseEntity.ok(id);
  }

  @ApiOperation(value = "用户登录")
  @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public CustomizedResponseEntity<String> login(
      @Valid LoginDTO loginDTO,
      HttpServletResponse response
  ) {
    miaoShaUserService.login(loginDTO, response);

    return CustomizedResponseEntity.ok("success");
  }

  @ApiOperation(value = "获取当前登录用户的信息")
  @GetMapping(value = "/getOnlineUser", produces = MediaType.APPLICATION_JSON_VALUE)
  public CustomizedResponseEntity<MiaoShaUser> getOnlineUser(
      @ApiIgnore MiaoShaUser onlineUser
  ) {
    return CustomizedResponseEntity.ok(onlineUser);
  }

  @ApiOperation(value = "用户退出")
  @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
  public CustomizedResponseEntity<String> logout(
      HttpServletRequest request,
      HttpServletResponse response
  ) {
    miaoShaUserService.logout(request, response);

    return CustomizedResponseEntity.ok("success");
  }
}
