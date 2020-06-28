package com.seatrain.bettersecondskill.function.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.seatrain.bettersecondskill.function.service.MPService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MPServiceImpl implements MPService {

  @Value("${spring.datasource.url}")
  private String databaseUrl;

  @Value("${spring.datasource.driver-class-name}")
  private String driverName;

  @Value("${spring.datasource.username}")
  private String databaseUserName;

  @Value("${spring.datasource.password}")
  private String databasePassword;

  @Value("${mybatis-plus.generator-moudle-name}")
  private String moudleName;

  @Value("${mybatis-plus.mapperXmlPath}")
  private String mapperXmlPath;

  @Value("${mybatis-plus.projectPath}")
  private String projectPath;

  @Override
  public void generateEnTityAndServiceAndDao(String tableName, String entityName) {
    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    gc.setOutputDir(projectPath + "/src/main/java");
    gc.setAuthor("longshibin");
    gc.setOpen(false);
    gc.setEntityName(entityName);
    gc.setServiceName(entityName + "Service");
    gc.setServiceImplName(entityName + "ServiceImpl");
    gc.setMapperName(entityName + "Mapper");
    gc.setSwagger2(true); // 实体属性 Swagger2 注解
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl(databaseUrl);
    // dsc.setSchemaName("public");
    dsc.setDriverName(driverName);
    dsc.setUsername(databaseUserName);
    dsc.setPassword(databasePassword);
    mpg.setDataSource(dsc);

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setParent(moudleName);
    pc.setEntity("entity.DO");
    pc.setService("service");
    pc.setMapper("mapper");
    mpg.setPackageInfo(pc);

    // 自定义配置
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        // to do nothing
      }
    };

    // 如果模板引擎是 freemarker
    String templatePath = "/templates/mapper.xml.ftl";
    // 如果模板引擎是 velocity
    // String templatePath = "/templates/mapper.xml.vm";

    // 自定义输出配置
    List<FileOutConfig> focList = new ArrayList<>();
    // 自定义配置会被优先输出
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        return projectPath + mapperXmlPath + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
      }
    });
    cfg.setFileOutConfigList(focList);
    mpg.setCfg(cfg);

    // 配置模板
    TemplateConfig templateConfig = new TemplateConfig();

    // 配置自定义输出模板
    //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
    // templateConfig.setEntity("templates/entity2.java");
    // templateConfig.setService();
    // templateConfig.setController();

    templateConfig.setXml(null);
    mpg.setTemplate(templateConfig);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//    strategy.setSuperEntityClass("event.eventmanager.model.Do.BaseEntity");
    strategy.setEntityLombokModel(true);
    strategy.setRestControllerStyle(true);
    // 公共父类
//    strategy.setSuperControllerClass("event.eventmanager.controller.BaseController");
    // 写于父类中的公共字段
//    strategy.setSuperEntityColumns("id");
    strategy.setInclude(tableName.split(","));
    strategy.setControllerMappingHyphenStyle(true);
    strategy.setTablePrefix(pc.getModuleName() + "_");
    mpg.setStrategy(strategy);
    mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    mpg.execute();
  }

}
