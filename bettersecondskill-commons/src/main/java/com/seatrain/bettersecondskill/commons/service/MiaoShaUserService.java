package com.seatrain.bettersecondskill.commons.service;

import com.seatrain.bettersecondskill.commons.dto.LoginDTO;
import com.seatrain.bettersecondskill.commons.dto.UserDTO;
import com.seatrain.bettersecondskill.commons.entity.MiaoShaUser;
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
}
