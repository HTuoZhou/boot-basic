package com.boot.basic.common.generator;// package com.boot.basic.common.generator;
//
// import com.baomidou.mybatisplus.annotation.FieldFill;
// import com.baomidou.mybatisplus.annotation.IdType;
// import com.baomidou.mybatisplus.generator.AutoGenerator;
// import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
// import com.baomidou.mybatisplus.generator.config.GlobalConfig;
// import com.baomidou.mybatisplus.generator.config.PackageConfig;
// import com.baomidou.mybatisplus.generator.config.StrategyConfig;
// import com.baomidou.mybatisplus.generator.config.po.TableFill;
// import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
// import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
// import java.util.Arrays;
//
// /**
//  * @author HTuoZhou
//  */
// public class OldMybatisPlusGenerator {
//
//     public static void main(String[] args) {
//         // 代码生成器
//         AutoGenerator mpg = new AutoGenerator();
//         mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//
//         // 数据源配置
//         DataSourceConfig dsc = new DataSourceConfig();
//         dsc.setUrl("jdbc:mysql://localhost:3306/boot_basic?serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf8");
//         dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//         dsc.setUsername("root");
//         dsc.setPassword("123456");
//         mpg.setDataSource(dsc);
//
//         // 全局配置
//         GlobalConfig gc = new GlobalConfig();
//         gc.setAuthor("TuoZhou")
//                 .setSwagger2(true)
//                 // .setFileOverride(true)
//                 .setOutputDir(System.getProperty("user.dir") + "/mybatis-plus-generator" + "/src/main/java")
//                 .setOpen(false)
//                 .setIdType(IdType.ID_WORKER);
//         mpg.setGlobalConfig(gc);
//
//         // 包配置
//         PackageConfig pc = new PackageConfig();
//         pc.setParent("com.boot.basic");
//                 // .setModuleName("")
//         mpg.setPackageInfo(pc);
//
//         // 策略配置
//         StrategyConfig strategy = new StrategyConfig();
//         strategy.setInclude("user")
//                     .setNaming(NamingStrategy.underline_to_camel)
//                     .setColumnNaming(NamingStrategy.underline_to_camel)
//                     .setEntityLombokModel(true)
//                     .setEntityTableFieldAnnotationEnable(true)
//                     .setVersionFieldName("version")
//                     .setLogicDeleteFieldName("deleted")
//                     .setTableFillList(Arrays.asList(new TableFill("create_time", FieldFill.INSERT),new TableFill("update_time", FieldFill.INSERT_UPDATE)))
//                     .setRestControllerStyle(true)
//                     .setControllerMappingHyphenStyle(true);
//         mpg.setStrategy(strategy);
//         mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//         mpg.execute();
//     }
//
// }
