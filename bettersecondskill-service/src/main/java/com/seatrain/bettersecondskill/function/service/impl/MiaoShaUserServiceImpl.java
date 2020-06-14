package com.seatrain.bettersecondskill.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seatrain.bettersecondskill.commons.dto.UserDTO;
import com.seatrain.bettersecondskill.commons.service.MiaoShaUserService;
import com.seatrain.bettersecondskill.commons.utils.MD5Util;
import com.seatrain.bettersecondskill.function.entity.DO.User;
import com.seatrain.bettersecondskill.function.service.UserService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoShaUserServiceImpl implements MiaoShaUserService {

  @Autowired
  UserService userService;

  @Override
  public boolean userNameAlreadyContains(String userName) {

    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.lambda()
        .eq(User::getName, "name");

    int count = userService.count(userQueryWrapper);

    return count > 0;
  }

  @Override
  public Integer create(UserDTO userDTO) {
    User user = new User();
    user.setName(userDTO.getName());
    user.setPassword(MD5Util.formPassword2DBPassword(userDTO.getPassword()));
    user.setSalt(MD5Util.salt);
    user.setHeadUrl(userDTO.getHeadUrl());
    user.setRegisterDate(LocalDateTime.now());

    userService.save(user);

    return user.getId();
  }
}
