package com.boot.basic.internal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.boot.basic.common.base.BusinessException;
import com.boot.basic.common.base.ResultCodeEnum;
import com.boot.basic.common.base.SystemProperties;
import com.boot.basic.common.constant.LocalDateTimePatternConstant;
import com.boot.basic.easyexcell.MyCellStyleWriteHandler;
import com.boot.basic.easyexcell.MyRowHeightStyleHandler;
import com.boot.basic.easyexcell.MySheetWriteHandler;
import com.boot.basic.easyexcell.export.ExportTitle;
import com.boot.basic.easyexcell.inport.ImportTitle;
import com.boot.basic.easyexcell.listener.ImportTitleListener;
import com.boot.basic.internal.service.IEasyexcellService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author HTuoZhou
 */
@Service
@Slf4j
public class EasyexcellServiceImpl implements IEasyexcellService {

    private final Environment environment;

    public EasyexcellServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    @SneakyThrows(Exception.class)
    public void downloadTemplate(HttpServletResponse response) {
        Map<Integer, String[]> mapDropDown = new LinkedHashMap<>();
        mapDropDown.put(2, new String[]{"是", "否"});

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("导入模板", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(response.getOutputStream()).build();
            excelWriter.write(new ArrayList<>(),
                    EasyExcel.writerSheet(0, "工作表1")
                            .head(ImportTitle.class)
                            .registerWriteHandler(new MyRowHeightStyleHandler())
                            .registerWriteHandler(new MyCellStyleWriteHandler())
                            .registerWriteHandler(new MySheetWriteHandler(mapDropDown, Collections.singletonList(3)))
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    @Override
    @SneakyThrows(Exception.class)
    public void importTemplate(MultipartFile multipartFile) {
        //解析到得数据列表
        List<ImportTitle> importTitleList = new ArrayList<>();
        //模板格式校验
        AtomicBoolean templateFlag = new AtomicBoolean(true);

        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(multipartFile.getInputStream()).build();
            excelReader.read(EasyExcel.readSheet(0, "工作表1")
                    .head(ImportTitle.class)
                    .registerReadListener(new ImportTitleListener(importTitleList, templateFlag))
                    .headRowNumber(0).build());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelReader != null) {
                excelReader.finish();
            }
        }

        if (templateFlag.get()) {
            long count = 0;

            if (CollUtil.isNotEmpty(importTitleList)) {
                count += importTitleList.stream()
                        .filter(importTitle -> importTitle.getResult().endsWith("导入失败"))
                        .count();
            }

            if (count == 0) {
                log.info("保存模板信息 TODO");
            } else {
                String errorFilePathDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern(LocalDateTimePatternConstant.NORM_DATE_PATTERN));
                String errorFilePath = SystemProperties.ROOT_PATH + environment.getProperty(SystemProperties.FILE_DOWNLOAD_PATH) + errorFilePathDate;
                String errorFileName = "导入错误.xlsx";
                File parentFile = new File(errorFilePath);
                if (!parentFile.exists()) {
                    boolean createFlag = parentFile.mkdirs();
                    if (createFlag) {
                        log.info("下载文件目录【{}】创建成功", parentFile);
                    } else {
                        log.info("下载文件目录【{}】创建失败", parentFile);
                    }
                }

                ExcelWriter excelWriter = null;
                try {
                    File file = new File(errorFilePath, errorFileName);
                    excelWriter = EasyExcel.write(file).build();
                    excelWriter.write(importTitleList,
                            EasyExcel.writerSheet(0, "工作表1")
                                    .head(ImportTitle.class)
                                    .registerWriteHandler(new MyRowHeightStyleHandler())
                                    .registerWriteHandler(new MyCellStyleWriteHandler())
                                    .registerWriteHandler(new MySheetWriteHandler())
                                    .build());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (excelWriter != null) {
                        excelWriter.finish();
                    }
                }
                throw new BusinessException(ResultCodeEnum.FAILED_TEMPLATE_IMPORT, errorFilePathDate + "/" + errorFileName);
            }
        } else {
            throw new BusinessException(ResultCodeEnum.FAILED_TEMPLATE, "模板不正确，请下载正确模板");
        }
    }

    @Override
    @SneakyThrows(Exception.class)
    public void exportInfo(HttpServletResponse response) {
        List<ExportTitle> exportTitleList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExportTitle exportTitle = new ExportTitle();
            exportTitle.setTitle1("标题1");
            exportTitle.setTitle2("标题2");
            exportTitle.setTitle3("标题3");
            exportTitleList.add(exportTitle);
        }

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("导出信息", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(response.getOutputStream()).build();
            excelWriter.write(exportTitleList,
                    EasyExcel.writerSheet(0, "工作表1")
                            .head(ExportTitle.class)
                            .registerWriteHandler(new MyRowHeightStyleHandler())
                            .registerWriteHandler(new MyCellStyleWriteHandler())
                            .registerWriteHandler(new MySheetWriteHandler())
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
