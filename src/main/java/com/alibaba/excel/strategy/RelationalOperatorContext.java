package com.alibaba.excel.strategy;

/**
 * @author leizhuang
 */
public class RelationalOperatorContext<T> {
    private static final String EQ="==";
    private static final String NE="!=";
    private static final String GT=">";
    private static final String GE=">=";
    private static final String LT="<";
    private static final String LE="<=";
    private final AbstractRelationalOperator<T> operator;
    public RelationalOperatorContext(AbstractRelationalOperator<T> operator) {
        this.operator = operator;
    }
    public boolean eval(String op, T left, T right) {
        boolean b = false;
        if (EQ.equals(op)) {
            b = operator.eq(left, right);
        } else if (NE.equals(op)) {
            b = operator.ne(left, right);
        } else if (GT.equals(op)) {
            b = operator.gt(left, right);
        } else if (GE.equals(op)) {
            b = operator.ge(left, right);
        } else if (LT.equals(op)) {
            b = operator.lt(left, right);
        } else if (LE.equals(op)) {
            b = operator.le(left, right);
        }
        return b;
    }
}
