package com.seatrain.bettersecondskill.web.controller;

import com.seatrain.bettersecondskill.commons.dto.MiaoshaGoodsDTO;
import com.seatrain.bettersecondskill.commons.entity.MiaoshaoGoodsVo;
import com.seatrain.bettersecondskill.commons.service.MiaoShaGoodsService;
import com.seatrain.bettersecondskill.function.access.UserCheckAndLimit;
import com.seatrain.bettersecondskill.web.http.CustomizedResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒杀商品管理
 *
 * @author seatrain
 * @version 1.0
 * @date 2020/08/14 16:21
 */
@Api(tags = "秒杀商品管理")
@Slf4j
@RestController
@RequestMapping("/miaoshaGoods")
public class MiaoshaGoodsController {

  @Autowired
  private MiaoShaGoodsService miaoShaGoodsService;

  @ApiOperation(value = "创建")
  @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
  public CustomizedResponseEntity<Long> create(MiaoshaGoodsDTO miaoshaGoodsDTO) {
    Long id = miaoShaGoodsService.create(miaoshaGoodsDTO);

    return CustomizedResponseEntity.ok(id);
  }

  @ApiOperation(value = "根据商品id获取对应的秒杀商品信息")
  @GetMapping(value = "/getByGoodsId", produces = MediaType.APPLICATION_JSON_VALUE)
  public CustomizedResponseEntity<MiaoshaoGoodsVo> getByGoodsId(
      @RequestParam long goodsId
  ) {
    MiaoshaoGoodsVo miaoshaoGoodsVo = miaoShaGoodsService.getVoByGoodsId(goodsId);

    return CustomizedResponseEntity.ok(miaoshaoGoodsVo);
  }

  @UserCheckAndLimit(seconds = 60, maxCount = 5)
  @ApiOperation(value = "根据id获取对应的秒杀商品信息")
  @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
  public CustomizedResponseEntity<MiaoshaoGoodsVo> getById(
      @RequestParam long id
  ) {
    MiaoshaoGoodsVo miaoshaoGoodsVo = miaoShaGoodsService.getVoById(id);

    return CustomizedResponseEntity.ok(miaoshaoGoodsVo);
  }

}
