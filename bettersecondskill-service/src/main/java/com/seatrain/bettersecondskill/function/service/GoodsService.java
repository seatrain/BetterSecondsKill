package com.seatrain.bettersecondskill.function.service;

import com.seatrain.bettersecondskill.commons.dto.GoodsDTO;
import com.seatrain.bettersecondskill.commons.entity.GoodsVo;
import com.seatrain.bettersecondskill.function.entity.DO.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author longshibin
 * @since 2020-06-28
 */
public interface GoodsService extends IService<Goods> {
  /**
   * 创建商品
   *
   * @param goodsDTO 商品创建信息
   * @return 商品id
   */
  Long create(GoodsDTO goodsDTO);

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
