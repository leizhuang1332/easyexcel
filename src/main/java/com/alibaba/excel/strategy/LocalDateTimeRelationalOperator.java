package com.alibaba.excel.strategy;

import java.time.LocalDateTime;

/**
 * @author leizhuang
 */
public class LocalDateTimeRelationalOperator implements AbstractRelationalOperator<LocalDateTime> {
    @Override
    public boolean eq(LocalDateTime left, LocalDateTime right) {
        return left.isEqual(right);
    }

    @Override
    public boolean ne(LocalDateTime left, LocalDateTime right) {
        return !left.isEqual(right);
    }

    @Override
    public boolean gt(LocalDateTime left, LocalDateTime right) {
        return left.isAfter(right);
    }

    @Override
    public boolean ge(LocalDateTime left, LocalDateTime right) {
        return !left.isBefore(right);
    }

    @Override
    public boolean lt(LocalDateTime left, LocalDateTime right) {
        return left.isBefore(right);
    }

    @Override
    public boolean le(LocalDateTime left, LocalDateTime right) {
        return !left.isAfter(right);
    }
}
