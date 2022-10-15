package com.boot.basic.common.base;

/**
 * @author HTuoZhou
 */
public interface SystemProperties {

    /**
     * 项目根目录
     */
    String ROOT_PATH = System.getProperty("user.dir");

    /**
     * 应用名称
     */
    String SPRING_APPLICATION_NAME = "spring.application.name";

    /**
     * 文件上传目录
     */
    String FILE_UPLOAD_PATH = "file.upload.path";

    /**
     * 文件下载目录
     */
    String FILE_DOWNLOAD_PATH = "file.download.path";


    /**
     * 文件上传大小限制
     */
    String SPRING_SERVLET_MULTIPART_MAX_FILE_SIZE = "spring.servlet.multipart.max-file-size";

    /**
     * netty通讯端口
     */
    String NETTY_PORT = "netty.port";

}
