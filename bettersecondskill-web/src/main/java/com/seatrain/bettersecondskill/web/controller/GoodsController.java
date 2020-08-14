package com.seatrain.bettersecondskill.web.controller;

import com.seatrain.bettersecondskill.commons.dto.GoodsDTO;
import com.seatrain.bettersecondskill.commons.entity.GoodsVo;
import com.seatrain.bettersecondskill.function.service.GoodsService;
import com.seatrain.bettersecondskill.web.http.CustomizedResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "商品管理")
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {

  @Autowired
  private GoodsService goodsService;

  @ApiOperation(value = "创建商品")
  @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
  public CustomizedResponseEntity<Long> create(
      @Valid GoodsDTO goodsDTO
  ) {
    Long id = goodsService.create(goodsDTO);

    log.info("创建商品标题为：{}，id为:{}", goodsDTO.getTitle(), id);
    return CustomizedResponseEntity.ok(id);
  }

  @ApiOperation(value = "获取商品详情")
  @GetMapping(value = "/getInfo", produces = MediaType.APPLICATION_JSON_VALUE)
  public CustomizedResponseEntity<GoodsVo> getInfo(
      @ApiParam(value = "商品id", required = true)
      @RequestParam Integer id
  ) {
    GoodsVo goodsVo = goodsService.selectInfo(id);

    return CustomizedResponseEntity.ok(goodsVo);
  }

  @ApiOperation(value = "获取商品列表")
  @GetMapping(value = "/getList", produces = MediaType.APPLICATION_JSON_VALUE)
  public CustomizedResponseEntity<List<GoodsVo>> getList(

  ) {
    List<GoodsVo> goodsVoList = goodsService.selectList();

    return CustomizedResponseEntity.ok(goodsVoList);
  }
}
