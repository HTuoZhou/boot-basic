package com.boot.basic.internal.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author HTuoZhou
 */
public interface IEasyexcellService {

    /**
     * 下载模板
     *
     * @param response HttpServletResponse
     */
    void downloadTemplate(HttpServletResponse response);

    /**
     * 导入模板
     *
     * @param multipartFile MultipartFile
     */
    void importTemplate(MultipartFile multipartFile);

    /**
     * 导出信息
     *
     * @param response HttpServletResponse
     */
    void exportInfo(HttpServletResponse response);
}

