package com.seatrain.bettersecondskill.commons.service;

import com.seatrain.bettersecondskill.commons.dto.GoodsDTO;
import com.seatrain.bettersecondskill.commons.entity.GoodsVo;
import java.util.List;

public interface MiaoShaGoodsService {

  /**
   * 创建商品
   *
   * @param goodsDTO 商品创建信息
   * @return 商品id
   */
  Integer create(GoodsDTO goodsDTO);

  /**
   * 查看商品详情
   *
   * @param id 商品id
   * @return 商品详情实体类
   */
  GoodsVo selectInfo(Integer id);

  /**
   * 查看商品列表
   *
   * @return 商品详情实体类列表
   */
  List<GoodsVo> selectList();
}
