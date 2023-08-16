package com.alibaba.easyexcel.test.demo.write;

import java.math.BigDecimal;

import com.alibaba.excel.annotation.ExcelIgnoreCondition;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.ExcelPropertyConditionMulti;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import lombok.Data;

@Data
public class DemoDataString {

    private Double doubleData;

    private String longData;

    private String integerData;

    private String bigDecimalData;

}
