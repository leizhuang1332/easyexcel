package com.alibaba.easyexcel.test.demo.write;

import java.math.BigDecimal;

import com.alibaba.excel.annotation.ExcelIgnoreCondition;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.ExcelPropertyConditionMulti;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import lombok.Data;

@Data
public class DemoDataContStyle {

    @ExcelProperty(value = "Double数字标题", multi = {
        // 根据参数输出指定Head
        @ExcelPropertyConditionMulti(value = "数字标题-重量>5", test = "weight > 5"),
        @ExcelPropertyConditionMulti(value = "数字标题-重量>10", test = "weight > 10")
    })
    @ContentStyle(dataFormat = 4)
    private Double doubleData;

    @ExcelProperty(value = "Long数字标题")
    @ContentStyle(dataFormat = 3)
    private Long longData;

    @ExcelProperty(value = "Integer数字标题")
    @ContentStyle(dataFormat = 3)
    private Integer integerData;

    @ExcelProperty(value = "BigDecimal数字标题")
    @ContentStyle(dataFormat = 4)
    @ExcelIgnoreCondition(test = "weight!=3")
    private BigDecimal bigDecimalData;

}
