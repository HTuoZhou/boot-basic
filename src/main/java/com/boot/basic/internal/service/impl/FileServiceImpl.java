package com.boot.basic.internal.service.impl;

import cn.hutool.core.util.IdUtil;
import com.boot.basic.common.base.SystemProperties;
import com.boot.basic.common.constant.LocalDateTimePatternConstant;
import com.boot.basic.internal.dto.FileDownloadDTO;
import com.boot.basic.internal.service.IFileService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author HTuoZhou
 */
@Service
@Slf4j
public class FileServiceImpl implements IFileService {

    private final Environment environment;

    public FileServiceImpl(Environment environment) {
        this.environment = environment;
    }


    @Override
    @SneakyThrows(Exception.class)
    public String upload(MultipartFile multipartFile) {
        //获取文件名
        String oldFileName = multipartFile.getOriginalFilename();
        //获取文件类型
        String oldFileType = Objects.requireNonNull(oldFileName).substring(oldFileName.lastIndexOf(".") + 1);

        //文件路径 采取时间文件夹的形式
        String uploadFilePathDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(LocalDateTimePatternConstant.NORM_DATE_PATTERN));
        String uploadFilePath = SystemProperties.ROOT_PATH + environment.getProperty(SystemProperties.FILE_UPLOAD_PATH) + uploadFilePathDate;
        // 用uuid给新文件命名
        String uuidName = IdUtil.simpleUUID();
        String uploadFileName = uuidName + "." + oldFileType;

        //创建目录
        File parentFile = new File(uploadFilePath);
        if (!parentFile.exists()) {
            boolean createFlag = parentFile.mkdirs();
            if (createFlag) {
                log.info("上传文件目录【{}】创建成功", parentFile);
            } else {
                log.info("上传文件目录【{}】创建失败", parentFile);
            }
        }

        // 完成文件上传
        File file = new File(uploadFilePath, uploadFileName);
        multipartFile.transferTo(file);
        return uploadFilePathDate + "/" + uploadFileName;
    }

    @Override
    public void download(FileDownloadDTO fileDownloadDTO, HttpServletResponse response) {
        String downloadFilePath = fileDownloadDTO.getDownloadFilePath();

        //下载的文件路径
        String filePath = SystemProperties.ROOT_PATH + environment.getProperty(SystemProperties.FILE_DOWNLOAD_PATH) + downloadFilePath;
        File file = new File(filePath);
        // 获取文件名
        String fileName = file.getName();

        //使用流的形式下载文件
        try (InputStream is = new FileInputStream(file);
             OutputStream os = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buffer = new byte[is.available()];
            int read = is.read(buffer);

            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            // Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            // attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名"
            // filename表示文件的默认名称，需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType("application/octet-stream");

            os.write(buffer);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
