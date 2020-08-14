package com.seatrain.bettersecondskill.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seatrain.bettersecondskill.commons.dto.GoodsDTO;
import com.seatrain.bettersecondskill.commons.entity.GoodsVo;
import com.seatrain.bettersecondskill.function.entity.DO.Goods;
import com.seatrain.bettersecondskill.function.mapper.GoodsMapper;
import com.seatrain.bettersecondskill.function.redisManage.RedisClient;
import com.seatrain.bettersecondskill.function.redisManage.keysBean.MiaoShaGoodsKey;
import com.seatrain.bettersecondskill.function.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author longshibin
 * @since 2020-06-28
 */
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
  @Autowired
  private GoodsService goodsService;

  @Autowired
  private RedisClient redisClient;

  @Override
  public Long create(GoodsDTO goodsDTO) {
    Goods goods = new Goods();
    BeanUtils.copyProperties(goodsDTO, goods);
    goodsService.save(goods);

    if (!redisClient.set(MiaoShaGoodsKey.goodsInfo(), String.valueOf(goods.getId()), goods)) {
      log.error("商品id为：{}的详情未存入缓存中！", goods.getId());
    }
    return goods.getId();
  }

  @Override
  public GoodsVo selectInfo(Integer id) {
    Goods goods = redisClient.get(MiaoShaGoodsKey.goodsInfo(), String.valueOf(id), Goods.class);
    if (goods == null) {
      goods = goodsService.getById(id);
      redisClient.set(MiaoShaGoodsKey.goodsInfo(), String.valueOf(id), goods);
    }
    GoodsVo goodsVo = new GoodsVo();
    BeanUtils.copyProperties(goods, goodsVo);
    return goodsVo;
  }

  @Override
  public List<GoodsVo> selectList() {
    List<Goods> goodsList = goodsService.list(new QueryWrapper<>());

    if (CollectionUtils.isEmpty(goodsList)) {
      return new ArrayList<>(0);
    }

    List<GoodsVo> goodsVoList = new ArrayList<>(goodsList.size());
    goodsList
        .forEach(item -> {
          GoodsVo goodsVo = new GoodsVo();
          BeanUtils.copyProperties(item, goodsVo);
          goodsVoList.add(goodsVo);
        });
    return goodsVoList;
  }
}
