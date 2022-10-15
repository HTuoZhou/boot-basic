package com.boot.basic.internal.controller;

import com.boot.basic.common.base.BaseController;
import com.boot.basic.internal.service.IEasyexcellService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.SneakyThrows;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author HTuoZhou
 */
@RestController
@RequestMapping("/easyexcell")
@Api(tags = "easyexcell控制器")
@Validated
public class EasyexcellController extends BaseController {

    private final IEasyexcellService easyexcellService;

    public EasyexcellController(IEasyexcellService easyexcellService) {
        this.easyexcellService = easyexcellService;
    }

    @ApiOperation("下载模板")
    @GetMapping("/downloadTemplate")
    @SneakyThrows(Exception.class)
    public void downloadTemplate() {
        easyexcellService.downloadTemplate(response);
    }

    @ApiOperation("导入模板")
    @PostMapping("/importTemplate")
    @SneakyThrows(Exception.class)
    public void importTemplate(@ApiParam(value = "上传文件", required = true) @NotNull(message = "模板文件不能为空") MultipartFile multipartFile) {
        easyexcellService.importTemplate(multipartFile);
    }

    @ApiOperation("导出信息")
    @GetMapping("/exportInfo")
    @SneakyThrows(Exception.class)
    public void exportInfo() {
        easyexcellService.exportInfo(response);
    }
}
