package learn.lhb.my.shop.commons.dto;

import java.io.Serializable;

/**
 * 分页
 *
 * @author 梁鸿斌
 * @date 2020/3/14.
 * @time 14:20
 */
public class PageParams implements Serializable {
    private static final long serialVersionUID = -190562612435416657L;

    /**
     * 分页的页数
     */
    private Integer pageIndex;
    /**
     * 页面加载的数据条数
     */
    private Integer pageSize;
    /**
     * 排序的字段
     */
    private String sortName;
    /**
     * 排序的类型
     */
    private String sortType;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
