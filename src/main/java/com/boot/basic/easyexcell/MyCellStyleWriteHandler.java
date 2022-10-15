package com.boot.basic.easyexcell;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.StyleUtil;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;

import java.util.Objects;

/**
 * @author HTuoZhou
 */
public class MyCellStyleWriteHandler extends AbstractCellStyleStrategy {

    private Workbook workbook;


    public MyCellStyleWriteHandler() {
    }

    @Override
    protected void initCellStyle(Workbook workbook) {
        // 初始化信息时，保存Workbook对象，转换时需要使用
        this.workbook = workbook;
    }

    @Override
    protected void setHeadCellStyle(Cell cell, Head head, Integer relativeRowIndex) {

        WriteCellStyle writeCellStyle = new WriteCellStyle();

        // 设置表头首行字体颜色为红色
        if (Objects.equals(0, relativeRowIndex)) {
            WriteFont writeFont = new WriteFont();
            writeFont.setColor(IndexedColors.RED.getIndex());
            writeCellStyle.setWriteFont(writeFont);
        }

        CellStyle headCellStyle = StyleUtil.buildHeadCellStyle(workbook, writeCellStyle);
        cell.setCellStyle(headCellStyle);
    }

    @Override
    protected void setContentCellStyle(Cell cell, Head head, Integer relativeRowIndex) {

        WriteCellStyle writeCellStyle = new WriteCellStyle();

        writeCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        writeCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle contentCellStyle = StyleUtil.buildContentCellStyle(workbook, writeCellStyle);
        cell.setCellStyle(contentCellStyle);

    }
}
