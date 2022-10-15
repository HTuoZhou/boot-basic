package com.boot.basic.common.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * @author HTuoZhou
 */
public class NewMybatisPlusGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/boot_basic?serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf8", "root", "123456")
                .globalConfig(builder -> builder.author("TuoZhou")
                        .enableSwagger()
                        // .fileOverride()
                        .outputDir(System.getProperty("user.dir") + "/mybatis-plus-generator" + "/src/main/java")
                        .disableOpenDir())
                .packageConfig(builder -> builder.parent("com.boot.basic.internal")
                        // .moduleName("")
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/mybatis-plus-generator" + "/src/main/resources/mapper")))
                .strategyConfig(builder -> builder.addInclude("user")
                        .entityBuilder()
                        .enableLombok()
                        .enableChainModel()
                        .idType(IdType.ASSIGN_ID)
                        .enableTableFieldAnnotation()
                        .versionColumnName("version")
                        .versionPropertyName("version")
                        .logicDeleteColumnName("deleted")
                        .logicDeletePropertyName("deleted")
                        .addTableFills(new Column("create_time", FieldFill.INSERT))
                        .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
