package com.alibaba.excel.strategy;

/**
 * @author leizhuang
 * @param <T>
 */
public interface AbstractRelationalOperator<T> {

    /**
     * 相等判断
     * @param left 左值
     * @param right 右值
     * @return boolean
     */
    boolean eq(T left, T right);
    /**
     * 不等判断
     * @param left 左值
     * @param right 右值
     * @return boolean
     */
    boolean ne(T left, T right);
    /**
     * 大于判断
     * @param left 左值
     * @param right 右值
     * @return boolean
     */
    boolean gt(T left, T right);
    /**
     * 大于等于判断
     * @param left 左值
     * @param right 右值
     * @return boolean
     */
    boolean ge(T left, T right);
    /**
     * 小于判断
     * @param left 左值
     * @param right 右值
     * @return boolean
     */
    boolean lt(T left, T right);
    /**
     * 小于等于判断
     * @param left 左值
     * @param right 右值
     * @return boolean
     */
    boolean le(T left, T right);
}
