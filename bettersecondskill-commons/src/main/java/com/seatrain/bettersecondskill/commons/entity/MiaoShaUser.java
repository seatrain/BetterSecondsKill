package com.seatrain.bettersecondskill.commons.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MiaoShaUser {

  private static final long serialVersionUID = 1L;

  private Integer id;

  private String name;

  private String salt;

  private String password;

  private String headUrl;

  private LocalDateTime registerDate;

  private LocalDateTime lastLoginDate;

  private Integer lastLoginIp;
}
