package com.boot.basic.easyexcell.export;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * @author HTuoZhou
 */
@Data
@ColumnWidth(20)
public class ExportTitle {

    @ExcelProperty({"导出信息", "标题1"})
    private String title1;

    @ExcelProperty({"导出信息", "标题2"})
    private String title2;

    @ExcelProperty({"导出信息", "标题3"})
    private String title3;

}
