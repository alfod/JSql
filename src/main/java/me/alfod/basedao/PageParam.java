package com.gaosi.api.common.basedao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Yang Dong
 * @createTime 2017/7/20  10:59
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2017/7/20  10:59
 * @note
 */
public class PageParam  implements Serializable{

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页数据量
     */
    private Integer pageSize;

    /**
     * 排序规则 enum SortTypeEnum
     */
    private Integer sortOrder;

    /**
     * 排序规则代码. group by 后面的sql
     *  例子 : "id asc, update_time desc"
     */
    private String orderBy;








    /**
     *  排序字段  表字段名字
     */
    private String sortColumn;
    /**
     * 排序方向
     */
    private String sortDirection;

    /**
     * 拼接到select 后面
     * 示例： "i.id, i.name as i_name, i.code i_code "
     */
    private String selectSql;

    /**
     * 拼接到join 后面
     * 示例： "left join institution.institution i
     */
    private String joinSql;

    /**
     * 拼接到where 后面
     * 示例： "i.update_time > ? and i.update_time < ?"
     */
    private String whereSql;

    /**
     * 拼接到where 后面语句的参数
     * 示例： { "2015-10-01", "2017-10-01" }
     */
    private List<Object> whereParam;


    /**
     * 拼接到order by 后面
     * 示例： "i.id asc, i.name desc"
     */
    private String orderSql;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


    public PageParam() {
    }

    public PageParam(Integer pageNum, Integer pageSize, Integer sortOrder) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.sortOrder = sortOrder;
    }


    public List<Object> getWhereParam() {
        return whereParam;
    }

    public void setWhereParam(List<Object> whereParam) {
        this.whereParam = whereParam;
    }

    public String getSelectSql() {
        return selectSql;
    }

    public void setSelectSql(String selectSql) {
        this.selectSql = selectSql;
    }

    public String getJoinSql() {
        return joinSql;
    }

    public void setJoinSql(String joinSql) {
        this.joinSql = joinSql;
    }

    public String getWhereSql() {
        return whereSql;
    }

    public void setWhereSql(String whereSql) {
        this.whereSql = whereSql;
    }

    public String getOrderSql() {
        return orderSql;
    }

    public void setOrderSql(String orderSql) {
        this.orderSql = orderSql;
    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    @Deprecated
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", sortOrder=" + sortOrder +
                ", orderBy='" + orderBy + '\'' +
                ", sortColumn='" + sortColumn + '\'' +
                ", sortDirection='" + sortDirection + '\'' +
                '}';
    }
}
