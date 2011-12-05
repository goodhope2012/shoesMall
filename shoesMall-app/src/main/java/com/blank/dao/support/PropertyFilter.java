package com.blank.dao.support;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 与具体ORM实现无关的属性过滤条件封装类.
 * <p/>
 * PropertyFilter主要记录页面中简单的搜索过滤条件,比Hibernate的Criterion要简单很多.
 * 可按项目扩展其他对比方式如大于、小于及其他数据类型如数字和日期.
 *
 * @author calvin
 */
public class PropertyFilter {

    /**
     * 多个属性间OR关系的分隔符.
     */
    public static final String OR_SEPARATOR = "_OR_";

    /**
     * 属性比较类型.
     */
    public enum MatchType {
        EQ, LIKE, GE, LE, GT, LT
    }

    private String propertyName;
    private Object value;
    private MatchType matchType = MatchType.EQ;
    private String condition;

    public PropertyFilter() {
    }

    public PropertyFilter(final String propertyName, final MatchType matchType, final Object value) {
        this.propertyName = propertyName;
        this.matchType = matchType;
        this.value = value;
        this.condition = this.matchType.name().toLowerCase();
    }

    /**
     * 获取属性名称,可用'_OR_'分隔多个属性,此时属性间是or的关系.
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * 设置属性名称,可用'_OR_'分隔多个属性,此时属性间是or的关系.
     */
    public void setPropertyName(final String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    @JsonIgnore
    public MatchType getMatchType() {
        return matchType;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
        this.matchType = MatchType.valueOf(condition.toUpperCase());
    }

    public void setMatchType(final MatchType matchType) {
        this.matchType = matchType;
    }

}
