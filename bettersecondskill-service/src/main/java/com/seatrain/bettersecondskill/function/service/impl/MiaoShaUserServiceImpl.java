package com.seatrain.bettersecondskill.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.seatrain.bettersecondskill.commons.constant.LoginConstant;
import com.seatrain.bettersecondskill.commons.dto.LoginDTO;
import com.seatrain.bettersecondskill.commons.dto.UserDTO;
import com.seatrain.bettersecondskill.commons.entity.MiaoShaUser;
import com.seatrain.bettersecondskill.commons.exception.UserLoginException;
import com.seatrain.bettersecondskill.commons.service.MiaoShaUserService;
import com.seatrain.bettersecondskill.commons.utils.MD5Util;
import com.seatrain.bettersecondskill.commons.utils.UUIDUtil;
import com.seatrain.bettersecondskill.function.entity.DO.User;
import com.seatrain.bettersecondskill.function.redisManage.RedisClient;
import com.seatrain.bettersecondskill.function.redisManage.keysBean.MiaoShaUserKey;
import com.seatrain.bettersecondskill.function.service.UserService;
import java.time.LocalDateTime;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoShaUserServiceImpl implements MiaoShaUserService {

  @Autowired
  UserService userService;

  @Autowired
  Gson gson;

  @Autowired
  RedisClient redisClient;

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

  @Override
  public void login(LoginDTO loginDTO, HttpServletResponse response) {
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.lambda()
        .eq(User::getName, loginDTO.getUserName());
    User user = userService.getOne(userQueryWrapper);
    if (user == null) {
      throw new UserLoginException("用户名或密码错误!");
    }

    if (!user.getPassword().equals(MD5Util.formPassword2DBPassword(loginDTO.getPassword()))) {
      throw new UserLoginException("用户名或密码错误!");
    }

    user.setLastLoginDate(LocalDateTime.now());
    userService.updateById(user);

    String token = UUIDUtil.getUUID();
    Cookie cookie = new Cookie(LoginConstant.TOKEN, token);
    cookie.setMaxAge(LoginConstant.TOKEN_EXPIRE);
    cookie.setPath("/");

    response.addCookie(cookie);

    redisClient.set(MiaoShaUserKey.getToken(), token, user);

  }

  @Override
  public MiaoShaUser getByToken(String token) {
    User user = redisClient.get(MiaoShaUserKey.getToken(), token, User.class);
    MiaoShaUser result = new MiaoShaUser();
    BeanUtils.copyProperties(user, result);
    return result;
  }
}
