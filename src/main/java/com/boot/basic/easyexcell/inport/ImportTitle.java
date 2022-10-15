package com.boot.basic.easyexcell.inport;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * @author HTuoZhou
 */
@Data
@ColumnWidth(20)
public class ImportTitle {

    @ExcelProperty({"导入模板\n" +
            "说明：\n"
            + "1、带 * 标识数据项为必填选项\n"
            + "2、带 # 标识数据项为下拉选项，请下拉进行选择", "标题1 *"})
    private String title1;

    @ExcelProperty({"导入模板\n" +
            "说明：\n"
            + "1、带 * 标识数据项为必填选项\n"
            + "2、带 # 标识数据项为下拉选项，请下拉进行选择", "标题2 *"})
    private String title2;

    @ExcelProperty({"导入模板\n" +
            "说明：\n"
            + "1、带 * 标识数据项为必填选项\n"
            + "2、带 # 标识数据项为下拉选项，请下拉进行选择", "标题3 #"})
    private String title3;

    @ExcelProperty({"导入模板\n" +
            "说明：\n"
            + "1、带 * 标识数据项为必填选项\n"
            + "2、带 # 标识数据项为下拉选项，请下拉进行选择", "导入结果"})
    private String result;

}
