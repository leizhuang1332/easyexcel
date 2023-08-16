package com.alibaba.excel.strategy;

import java.util.Date;

/**
 * @author leizhuang
 */
public class DateRelationalOperator implements AbstractRelationalOperator<Date> {
    @Override
    public boolean eq(Date left, Date right) {
        return left.compareTo(right) == 0;
    }

    @Override
    public boolean ne(Date left, Date right) {
        return left.compareTo(right) != 0;
    }

    @Override
    public boolean gt(Date left, Date right) {
        return left.compareTo(right) > 0;
    }

    @Override
    public boolean ge(Date left, Date right) {
        return left.compareTo(right) >= 0;
    }

    @Override
    public boolean lt(Date left, Date right) {
        return left.compareTo(right) < 0;
    }

    @Override
    public boolean le(Date left, Date right) {
        return left.compareTo(right) <= 0;
    }
}
