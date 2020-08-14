package com.seatrain.bettersecondskill.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seatrain.bettersecondskill.commons.dto.MiaoshaGoodsDTO;
import com.seatrain.bettersecondskill.commons.entity.MiaoshaoGoodsVo;
import com.seatrain.bettersecondskill.commons.exception.InternalException;
import com.seatrain.bettersecondskill.commons.service.MiaoShaGoodsService;
import com.seatrain.bettersecondskill.function.entity.DO.Goods;
import com.seatrain.bettersecondskill.function.entity.DO.MiaoshaGoods;
import com.seatrain.bettersecondskill.function.mapper.MiaoshaGoodsMapper;
import com.seatrain.bettersecondskill.function.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class MiaoShaGoodsServiceImpl extends ServiceImpl<MiaoshaGoodsMapper, MiaoshaGoods> implements MiaoShaGoodsService {

  @Autowired
  private GoodsService goodsService;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public Long create(MiaoshaGoodsDTO miaoshaGoodsDTO) {
    Goods goods = goodsService.getById(miaoshaGoodsDTO.getGoodsId());
    if (goods == null) {
      throw new InternalException(String.format("商品%d不存在:", miaoshaGoodsDTO.getGoodsId()));
    }
    MiaoshaGoods miaoshaGoods = new MiaoshaGoods();
    miaoshaGoods.setGoodsId(goods.getId());
    miaoshaGoods.setMiaoshaPrice(miaoshaGoodsDTO.getMiaoshaPrice());
    miaoshaGoods.setStartDate(miaoshaGoodsDTO.getStartDate());
    miaoshaGoods.setEndDate(miaoshaGoodsDTO.getEndDate());

    boolean result = save(miaoshaGoods);
    if (!result) {
      throw new InternalException("创建秒杀商品失败:" + miaoshaGoodsDTO.toString());
    }
    log.info("创建秒杀商品成功:{}", miaoshaGoods.toString());

    return miaoshaGoods.getId();
  }

  @Override
  public MiaoshaoGoodsVo getVoByGoodsId(long goodsId) {
    QueryWrapper<MiaoshaGoods> queryWrapper = new QueryWrapper<>();
    queryWrapper.lambda()
        .eq(MiaoshaGoods::getGoodsId, goodsId);
    MiaoshaGoods miaoshaGoods = getOne(queryWrapper);

    MiaoshaoGoodsVo miaoshaoGoodsVo = new MiaoshaoGoodsVo();
    BeanUtils.copyProperties(miaoshaGoods, miaoshaoGoodsVo);

    return miaoshaoGoodsVo;
  }

  @Override
  public MiaoshaoGoodsVo getVoById(long id) {
    MiaoshaGoods miaoshaGoods = getById(id);

    MiaoshaoGoodsVo miaoshaoGoodsVo = new MiaoshaoGoodsVo();
    BeanUtils.copyProperties(miaoshaGoods, miaoshaoGoodsVo);

    return miaoshaoGoodsVo;
  }
}
