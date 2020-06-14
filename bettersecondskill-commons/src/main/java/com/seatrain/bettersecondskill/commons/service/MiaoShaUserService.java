package com.seatrain.bettersecondskill.commons.service;

import com.seatrain.bettersecondskill.commons.dto.UserDTO;

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
}
