package com.seatrain.bettersecondskill.web.config;

import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

  @Bean
  public Docket createOuterRestApi() {
    List<ResponseMessage> responseMessageList = new ArrayList<>();
//    responseMessageList.add(new ResponseMessageBuilder().code(200).message("操作成功").build());
//    responseMessageList.add(new ResponseMessageBuilder().code(300).message("正在处理结果").build());
//    responseMessageList.add(new ResponseMessageBuilder().code(400).message("不支持的请求").build());
//    responseMessageList.add(new ResponseMessageBuilder().code(97).message("用户未登录或认证失败").build());
//    responseMessageList.add(new ResponseMessageBuilder().code(403).message("用户无权访问或服务器拒绝").build());
//    responseMessageList.add(new ResponseMessageBuilder().code(405).message("当前接口不支持的请求方式").build());
//    responseMessageList.add(new ResponseMessageBuilder().code(500).message("操作失败").build());

    ;

    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .globalResponseMessage(RequestMethod.GET, responseMessageList)
        .globalResponseMessage(RequestMethod.POST, responseMessageList)
        .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
        .globalResponseMessage(RequestMethod.PUT, responseMessageList)
        .apiInfo(outerApiInfo())
        .select()
        //.apis(MutipartBasePackageUtil.basePackage("ServerUser.rolePermission.controller"))
        .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo outerApiInfo() {//swagger-ui 外部接口的顶部标题信息
    return new ApiInfoBuilder()
        .title("新版秒杀")
        .description(" <strong>新版秒杀</strong>")
        .version("1.0.0")
        .build();
  }
}
