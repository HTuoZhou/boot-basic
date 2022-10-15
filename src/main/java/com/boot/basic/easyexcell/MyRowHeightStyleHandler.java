package com.boot.basic.easyexcell;

import com.alibaba.excel.write.style.row.AbstractRowHeightStyleStrategy;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author HTuoZhou
 */
public class MyRowHeightStyleHandler extends AbstractRowHeightStyleStrategy {

    @Override
    protected void setHeadColumnHeight(Row row, int i) {
        int rowNum = row.getRowNum();
        if (rowNum == 0) {
            row.setHeight((short) (100 * 20));
        } else {
            row.setHeight((short) (50 * 20));
        }
    }

    @Override
    protected void setContentColumnHeight(Row row, int i) {
        row.setHeight((short) (25 * 20));
    }

}
