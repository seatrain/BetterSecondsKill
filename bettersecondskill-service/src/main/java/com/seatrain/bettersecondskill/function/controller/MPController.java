package com.seatrain.bettersecondskill.function.controller;

import com.seatrain.bettersecondskill.function.service.MPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "MPController", description = "mp管理")
@RestController
@RequestMapping("/mp")
public class MPController {

  @Autowired
  MPService mpService;

  @ApiOperation(value = "mp根据表名生成对应的entity,service,dao")
  @PostMapping(value = "/generateEntityForTable")
  private String generateEntityForTable(
      @ApiParam(value = "表名", required = true)
      @RequestParam String tableName,

      @ApiParam(value = "实体类名", required = true)
      @RequestParam String entityName
  ) {
    mpService.generateEnTityAndServiceAndDao(tableName, entityName);

    return "success";
  }


}
