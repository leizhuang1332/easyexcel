package com.alibaba.easyexcel.test.demo.write;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreCondition;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.ExcelPropertyConditionMulti;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 基础数据类
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
public class DemoData extends BaseRowModel {
    private Long id;
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty(value = "日期标题", multi = {
        // 可以定义多个Head，根据test条件判断使用
        @ExcelPropertyConditionMulti(value = "日期标题日汇总", test = "dayMonthType ==1 and (summaryType==1 or code ==中国)"),
        @ExcelPropertyConditionMulti(value = "日期标题月月月", test = "dayMonthType ==2")
    })
    private Date date;

    private String name;
    @ExcelProperty(value = "年龄分布", multi = {
        // 可以定义多个Head，根据test条件判断使用
        @ExcelPropertyConditionMulti(value = "老年人", test = "age >=  1965-06-20 00:00:00 and age <  1985-06-20 00:00:00"),
        @ExcelPropertyConditionMulti(value = "中年人", test = "age >=  1985-06-20 00:00:00 and age <  1992-06-20 00:00:00"),
        @ExcelPropertyConditionMulti(value = "成年人", test = "age >= 1992-06-20 00:00:00 and age < 2005-06-20 00:00:00"),
        @ExcelPropertyConditionMulti(value = "未成年人", test = "age > 2005-06-20 00:00:00")
    })
    private String grownMan;

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
    private BigDecimal bigDecimalData;

    /**
     * 忽略这个字段
     */
    @ExcelProperty("忽略字段")
    @ExcelIgnoreCondition(test = "ignore!=null")
    private String ignore;
}
