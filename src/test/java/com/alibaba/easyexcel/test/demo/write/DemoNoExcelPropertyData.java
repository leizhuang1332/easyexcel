package com.alibaba.easyexcel.test.demo.write;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.excel.annotation.ExcelIgnoreCondition;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.ExcelPropertyConditionMulti;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础数据类
 *
 * @author Jiaju Zhuang
 **/
@Data
public class DemoNoExcelPropertyData extends BaseRowModel {


    private Double doubleData;

    private Long longData;

    private Integer integerData;

    private BigDecimal bigDecimalData;

}
