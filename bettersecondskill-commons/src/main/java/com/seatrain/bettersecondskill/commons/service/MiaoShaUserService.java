package com.seatrain.bettersecondskill.commons.service;

import com.seatrain.bettersecondskill.commons.dto.LoginDTO;
import com.seatrain.bettersecondskill.commons.dto.UserDTO;
import com.seatrain.bettersecondskill.commons.entity.MiaoShaUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MiaoShaUserService {

  /**
   * 检查用户名是否已存在
   *
   * @param userName 用户名
   */
  boolean userNameAlreadyContains(String userName);

  /**
   * 创建用户
   *
   * @param userDTO 用户参数
   * @return 用户id
   */
  Integer create(UserDTO userDTO);

  void login(LoginDTO loginDTO, HttpServletResponse response);

  /**
   * 根据token获取当前登录用户的信息
   *
   * @param token token
   * @return 当前登录用户
   */
  MiaoShaUser getByToken(String token);

  /**
   * 用户退出,从缓存中删除用户信息，并且删除cookie
   */
  void logout(HttpServletRequest request, HttpServletResponse response);

  /**
   * 从请求中获取登录token
   */
  String getToken(HttpServletRequest request);

  /**
   * 刷新token的有效期， 更新缓存中用户的有效时间和cookie中的有效时间
   */
  void refreshToken(HttpServletRequest request, HttpServletResponse response);
}
