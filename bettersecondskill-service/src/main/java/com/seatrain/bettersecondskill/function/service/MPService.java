package com.seatrain.bettersecondskill.function.service;

/**
 * MyBatisPlus工具类
 */
public interface MPService {

  /**
   * 根据传入的表名生成对应的service 和 dao
   *
   * @param tableName 表名
   * @param entityName 实体类名
   */
  void generateEnTityAndServiceAndDao(String tableName, String entityName);
}
