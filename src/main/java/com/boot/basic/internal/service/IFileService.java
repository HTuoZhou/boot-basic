package com.boot.basic.internal.service;

import com.boot.basic.internal.dto.FileDownloadDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author HTuoZhou
 */
public interface IFileService {

    /**
     * 上传
     *
     * @param multipartFile MultipartFile
     * @return String
     */
    String upload(MultipartFile multipartFile);

    /**
     * 下载
     *
     * @param fileDownloadDTO FileDownloadDTO
     * @param response        HttpServletResponse
     */
    void download(FileDownloadDTO fileDownloadDTO, HttpServletResponse response);

}
