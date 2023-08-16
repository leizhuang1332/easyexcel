package com.alibaba.excel.strategy;

import java.math.BigDecimal;

/**
 * @author leizhuang
 */
public class BigDecimalRelationalOperator implements AbstractRelationalOperator<BigDecimal> {
    @Override
    public boolean eq(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) == 0;
    }

    @Override
    public boolean ne(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) != 0;
    }

    @Override
    public boolean gt(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) > 0;
    }

    @Override
    public boolean ge(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) >= 0;
    }

    @Override
    public boolean lt(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) < 0;
    }

    @Override
    public boolean le(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) <= 0;
    }
}
