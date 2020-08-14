package com.seatrain.bettersecondskill.commons.service;

import com.seatrain.bettersecondskill.commons.dto.MiaoshaGoodsDTO;
import com.seatrain.bettersecondskill.commons.entity.MiaoshaoGoodsVo;

public interface MiaoShaGoodsService {

  /**
   * 根据商品id生成对应的秒杀商品
   *
   * @param miaoshaGoodsDTO 秒杀商品创建参数
   * @return 生成的秒杀商品的id
   */
  Long create(MiaoshaGoodsDTO miaoshaGoodsDTO);

  /**
   * 根据商品id获取对应的秒杀商品vo
   *
   * @param goodsId 商品id
   * @return 秒杀商品vo
   */
  MiaoshaoGoodsVo getVoByGoodsId(long goodsId);

  /**
   * 根据id获取对应的秒杀商品vo
   *
   * @param id id
   * @return 秒杀商品vo
   */
  MiaoshaoGoodsVo getVoById(long id);
}
