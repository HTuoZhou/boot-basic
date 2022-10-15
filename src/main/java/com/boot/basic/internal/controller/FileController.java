package com.boot.basic.internal.controller;

import com.boot.basic.common.base.ApiFinalResult;
import com.boot.basic.common.base.BaseController;
import com.boot.basic.internal.dto.FileDownloadDTO;
import com.boot.basic.internal.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author HTuoZhou
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件控制器")
@Validated
@Slf4j
public class FileController extends BaseController {

    private final IFileService fileService;

    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation("上传")
    @PostMapping("/upload")
    @SneakyThrows(Exception.class)
    public ApiFinalResult<String> upload(@ApiParam(value = "上传文件", required = true) @NotNull(message = "上传文件不能为空") MultipartFile multipartFile) {
        return ApiFinalResult.success(fileService.upload(multipartFile));
    }

    @ApiOperation("下载")
    @GetMapping("/download")
    public void download(@Valid FileDownloadDTO fileDownloadDTO) {
        fileService.download(fileDownloadDTO, response);
    }

}
