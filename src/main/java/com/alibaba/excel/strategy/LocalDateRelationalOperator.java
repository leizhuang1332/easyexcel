package com.alibaba.excel.strategy;

import java.time.LocalDate;

/**
 * @author leizhuang
 */
public class LocalDateRelationalOperator implements AbstractRelationalOperator<LocalDate> {
    @Override
    public boolean eq(LocalDate left, LocalDate right) {
        return left.isEqual(right);
    }

    @Override
    public boolean ne(LocalDate left, LocalDate right) {
        return !left.isEqual(right);
    }

    @Override
    public boolean gt(LocalDate left, LocalDate right) {
        return left.isAfter(right);
    }

    @Override
    public boolean ge(LocalDate left, LocalDate right) {
        return !left.isBefore(right);
    }

    @Override
    public boolean lt(LocalDate left, LocalDate right) {
        return left.isBefore(right);
    }

    @Override
    public boolean le(LocalDate left, LocalDate right) {
        return !left.isAfter(right);
    }
}
