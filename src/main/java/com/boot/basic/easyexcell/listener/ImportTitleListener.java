package com.boot.basic.easyexcell.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.boot.basic.easyexcell.inport.ImportTitle;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author HTuoZhou
 */
public class ImportTitleListener extends AnalysisEventListener<ImportTitle> {

    /**
     * 解析到得数据列表
     */
    List<ImportTitle> importTitleList;

    /**
     * 模板格式校验
     */
    AtomicBoolean templateFlag;

    public ImportTitleListener() {
    }

    public ImportTitleListener(List<ImportTitle> importTitleList, AtomicBoolean templateFlag) {
        this.importTitleList = importTitleList;
        this.templateFlag = templateFlag;
    }

    @Override
    public void invoke(ImportTitle data, AnalysisContext context) {
        Integer rowIndex = context.readRowHolder().getRowIndex();

        if (Objects.equals(0, rowIndex)) {
            ImportTitle importTitle1 = new ImportTitle();

            importTitle1.setTitle1("导入模板\n" +
                    "说明：\n"
                    + "1、带 * 标识数据项为必填选项\n"
                    + "2、带 # 标识数据项为下拉选项，请下拉进行选择");
            importTitle1.setTitle2("导入模板\n" +
                    "说明：\n"
                    + "1、带 * 标识数据项为必填选项\n"
                    + "2、带 # 标识数据项为下拉选项，请下拉进行选择");
            importTitle1.setTitle3("导入模板\n" +
                    "说明：\n"
                    + "1、带 * 标识数据项为必填选项\n"
                    + "2、带 # 标识数据项为下拉选项，请下拉进行选择");
            importTitle1.setResult("导入模板\n" +
                    "说明：\n"
                    + "1、带 * 标识数据项为必填选项\n"
                    + "2、带 # 标识数据项为下拉选项，请下拉进行选择");

            if (!Objects.equals(data, importTitle1)) {
                templateFlag.set(false);
            }
        } else if (Objects.equals(1, rowIndex)) {
            ImportTitle importTitle2 = new ImportTitle();

            importTitle2.setTitle1("标题1 *");
            importTitle2.setTitle2("标题2 *");
            importTitle2.setTitle3("标题3 #");
            importTitle2.setResult("导入结果");

            if (!Objects.equals(data, importTitle2)) {
                templateFlag.set(false);
            }
        } else {
            if (StringUtils.isBlank(data.getTitle1())) {
                data.setResult("标题1选项属于必填选项，导入失败");
            } else if (StringUtils.isBlank(data.getTitle2())) {
                data.setResult("标题2选项属于必填选项，导入失败");
            } else if (StringUtils.isBlank(data.getTitle3())) {
                data.setResult("标题3选项属于必填选项，导入失败");
            }
            importTitleList.add(data);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
