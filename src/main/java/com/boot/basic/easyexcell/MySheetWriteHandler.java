package com.boot.basic.easyexcell;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.List;
import java.util.Map;

/**
 * @author HTuoZhou
 */
public class MySheetWriteHandler implements SheetWriteHandler {

    private Map<Integer, String[]> mapDropDown;
    private List<Integer> columnIndexList;

    public MySheetWriteHandler() {
    }

    public MySheetWriteHandler(Map<Integer, String[]> mapDropDown, List<Integer> columnIndexList) {
        this.mapDropDown = mapDropDown;
        this.columnIndexList = columnIndexList;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        //获取工作簿
        Sheet sheet = writeSheetHolder.getSheet();

        //开始设置下拉框
        DataValidationHelper helper = sheet.getDataValidationHelper();
        //设置下拉框
        if (CollUtil.isNotEmpty(mapDropDown)) {
            mapDropDown.forEach((k, v) -> {
                CellRangeAddressList addressList = new CellRangeAddressList(2, 65535, k, k);
                DataValidationConstraint constraint = helper.createExplicitListConstraint(mapDropDown.get(k));
                DataValidation dataValidation = helper.createValidation(constraint, addressList);
                sheet.addValidationData(dataValidation);
            });
        }

        // 设置隐藏列
        if (CollUtil.isNotEmpty(columnIndexList)) {
            columnIndexList.forEach((x) -> {
                sheet.setColumnHidden(x, true);
            });
        }

        //冻结表头
        sheet.createFreezePane(0, 2, 0, 2);
    }

}
