package com.blank.util;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * 注意所有序号从1开始.
 *
 * @author calvin
 * @param <T> Page中记录的类型.
 */
public class Page<T> {
    // 公共变量
    public static final String ASC = "asc";
    public static final String DESC = "desc";
    public static final int DEFAULT_PAGE_SIZE = 10 ;

    //分页参数
    protected int pageNo = 1;
    protected int pageSize = 3;
    protected String orderBy = null;
    protected String order = null;
    protected boolean autoCount = true;

    private int first = -1;

    //返回结果
    protected List<T> result = Collections.emptyList();
    protected long totalCount = -1;

    private int totalPage;

    // 构造函数

    public Page() {
    }

    public Page(int pageNo) {
        this(pageNo, DEFAULT_PAGE_SIZE);
    }

    public Page(int pageNo,final int pageSize) {
        this(pageNo, pageSize, true);
    }

    public Page(int pageNo,final int pageSize, final boolean autoCount) {
        this.pageNo = pageNo;
        setPageSize(pageSize);
        this.autoCount = autoCount;
    }

    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setPageNo(final int pageNo) {
        this.pageNo = pageNo;

        if (pageNo < 1) {
            this.pageNo = 1;
        }
    }

    /**
     * 获得每页的记录数量,默认为1.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页的记录数量,低于1时自动调整为1.
     */
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;

/*        if (pageSize < 1) {
            this.pageSize = 1;
        }*/
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    public int getFirst() {
        if (first == -1) {
            return ((pageNo - 1) * pageSize) + 1;
        } else {
            return first;
        }
    }

    public void setFirst(int first) {
        this.first = first;
    }

    /**
     * 获得排序字段,无默认值.多个排序字段时用','分隔.
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置排序字段,多个排序字段时用','分隔.
     */
    public void setOrderBy(final String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 是否已设置排序字段,无默认值.
     */
    public boolean isOrderBySetted() {
        return StringUtils.isNotBlank(orderBy);
    }

    /**
     * 获得排序方向.
     * <p/>
     * order 可选值为desc或asc,多个排序字段时用','分隔.
     */
    public String getOrder() {
        return order;
    }

    /**
     * 设置排序方式向.
     *
     * @param order 可选值为desc或asc,多个排序字段时用','分隔.
     */
    public void setOrder(final String order) {
        //检查order字符串的合法值
        String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
        for (String orderStr : orders) {
            if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr))
                throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
        }

        this.order = StringUtils.lowerCase(order);
    }

    /**
     * 查询对象时是否自动另外执行count查询获取总记录数,默认为false.
     */
    public boolean isAutoCount() {
        return autoCount;
    }

    /**
     * 查询对象时是否自动另外执行count查询获取总记录数.
     */
    public void setAutoCount(final boolean autoCount) {
        this.autoCount = autoCount;
    }

    // 查询结果函数

    /**
     * 取得页内的记录列表.
     */
    public List<T> getResult() {
        return result;
    }

    public void setResult(final List<T> result) {
        this.result = result;
    }

    /**
     * 取得总记录数,默认值为-1.
     */
    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(final long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 根据pageSize与totalCount计算总页数,默认值为-1.
     */
    public int getTotalPage() {

        return totalPage;
    }

    public void setTotalPage() {
        if (totalCount < 0) {
            totalPage = 0;
            return;
        }

        int count = (int) (totalCount / pageSize);
        if (totalCount % pageSize > 0) {
            count++;
        }
        this.totalPage = count;
    }

    /**
     * 是否还有下一页.
     */
    public boolean isHasNext() {
        return (pageNo + 1 <= getTotalPage());
    }

    /**
     * 取得下页的页号,序号从1开始.
     * 当前页为尾页时仍返回尾页序号.
     */
    public int getNextPage() {
        if (isHasNext())
            return pageNo + 1;
        else
            return pageNo;
    }

    /**
     * 是否还有上一页.
     */
    public boolean isHasPre() {
        return (pageNo - 1 >= 1);
    }

    /**
     * 取得上页的页号,序号从1开始.
     * 当前页为首页时返回首页序号.
     */
    public int getPrePage() {
        if (isHasPre())
            return pageNo - 1;
        else
			return pageNo;
	}
}
