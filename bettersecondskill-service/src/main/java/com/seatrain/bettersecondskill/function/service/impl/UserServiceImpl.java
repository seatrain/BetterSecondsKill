package com.seatrain.bettersecondskill.function.service.impl;

import com.seatrain.bettersecondskill.function.entity.DO.User;
import com.seatrain.bettersecondskill.function.mapper.UserMapper;
import com.seatrain.bettersecondskill.function.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author longshibin
 * @since 2020-06-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
