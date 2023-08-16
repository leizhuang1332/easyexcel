package com.alibaba.easyexcel.test.demo.write;

import java.util.Arrays;
import java.util.List;

import com.alibaba.excel.metadata.Head;

public class CustomCellWriteHandlerStrategy extends AbstractCustomCellWriteHandlerStrategy {

    /**
     * 千分号整数字段
     */
    private List<String> fmtNumFields = Arrays.asList("waybillPieceSum","signWaybillNum","settleSum",
        "customerRefundSum","badPieceSum","refundSum","collectFeeIsZeroSum","longData","integerData");
    /**
     * 千分号小数点字段
     */
    private List<String> fmtRateFields = Arrays.asList("waybillNumSum","collectionfeeSum","customerFeeSum","collectFeeSum","doubleData","bigDecimalData");

    private Integer columnWidth;

    /**
     * @param fmtNumFields
     * @param fmtRateFields
     * @param columnWidth
     */
    public CustomCellWriteHandlerStrategy(List<String> fmtNumFields, List<String> fmtRateFields, Integer columnWidth) {
        this.fmtNumFields = fmtNumFields;
        this.fmtRateFields = fmtRateFields;
        this.columnWidth = columnWidth;
    }

    /**
     * @param columnWidth
     */
    public CustomCellWriteHandlerStrategy(Integer columnWidth) {
        this.columnWidth = columnWidth;
    }

    @Override
    protected Integer columnWidth(Head head, Integer columnIndex) {
        return columnWidth;
    }

    @Override
    protected List<String> getFmtNumFields() {
        return this.fmtNumFields;
    }

    @Override
    protected List<String> getFmtRateFields() {
        return this.fmtRateFields;
    }
}
