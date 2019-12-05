package entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author: 磊大大
 * @date: 2019/10/12 15:44
 * 分页返回参数封装
 */
public class PageRecordsDto<T> implements Serializable {

    private Integer total = 0;

    private List<T> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
