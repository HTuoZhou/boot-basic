package com.boot.basic.common.base;

import cn.hutool.core.util.PageUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author HTuoZhou
 */
@Data
public class PageFinalResult<T> {

    @ApiModelProperty(value = "当前页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "记录总数")
    private Integer totalCount;

    @ApiModelProperty(value = "页码总数")
    private Integer pageCount;

    @ApiModelProperty(value = "分页数据")
    private List<T> pageData;

    public PageFinalResult() {

    }

    public PageFinalResult(int pageNum, int pageSize, List<T> pageData) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;

        if (Objects.nonNull(pageData)) {
            this.totalCount = pageData.size();
            this.pageCount = PageUtil.totalPage(this.totalCount, this.pageSize);

            int start;
            int end;
            if (pageSize >= totalCount) {
                pageSize = totalCount;
            }
            if (pageNum < pageCount) {
                start = (pageNum - 1) * pageSize;
                end = start + pageSize;
            } else {
                pageNum = pageCount;
                start = (pageNum - 1) * pageSize;
                end = totalCount;
            }
            this.pageData = pageData.subList(start, end);
        } else {
            this.totalCount = 0;
            this.pageCount = 0;
            this.pageData = new ArrayList<>();
        }
    }

}
