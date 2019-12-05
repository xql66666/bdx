package entity;

import java.io.Serializable;

/**
 * @author: 磊大大
 * @date: 2019/10/12 15:49
 * 分页参数及返回父类
 */
public class PageDto implements Serializable {

    private Integer currentPage = 1;

    private Integer pageSize = 10;

    public Integer getcurrentPage() {
        if (null == currentPage || currentPage < 1) {
            return 1;
        }
        return currentPage;
    }

    public void setcurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
