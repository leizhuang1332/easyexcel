package com.alibaba.easyexcel.test.demo.write;

import com.alibaba.excel.annotation.ExcelIgnoreCondition;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.ExcelPropertyConditionMulti;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author ff
 * @since 2022-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SpmiCostBillReportExcelVO extends BaseRowModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "日期", multi = {
        @ExcelPropertyConditionMulti(value = "月份", test = "dateType==2")
    })
    private String inputDate;
    //    @ExcelProperty("寄件财务中心")
    //    private String sendFinancialCenterCode;
    @ExcelProperty("寄件财务中心")
    private String sendFinancialCenterName;
    @ExcelProperty("寄件网点编号")
    private String sendNetworkCode;
    @ExcelProperty("寄件网点")
    private String sendNetworkName;
    @ExcelProperty("寄件府编号")
    private String sendProvinceCode;
    //    @ExcelProperty("寄件府")
    //    private String sendProvince;
    @ExcelProperty("客户编码")
    @ExcelIgnoreCondition(test = "summaryType==2")
    private String customerCode;
    @ExcelProperty("客户名称")
    @ExcelIgnoreCondition(test = "summaryType==2")
    private String customerName;
    @ExcelProperty("收件府编号")
    private String receiveProvinceCode;
    //    @ExcelProperty("收件府")
    //    private String receiveProvince;
    @ExcelProperty("总票数")
    private BigDecimal totalWaybillNum;
    @ExcelProperty("总计费重量")
    private BigDecimal totalWeight;
    //    @ExcelProperty("总金额")
    //    private BigDecimal totalCostFee;
    @ExcelProperty("成本运费")
    private BigDecimal costFreightFee;
    @ExcelProperty("偏远费")
    private BigDecimal remoteFee;
    @ExcelProperty("运费折扣")
    private BigDecimal customerSubsidy;
    @ExcelProperty("净成本运费")
    private BigDecimal costFreightFeeNew;

    @ExcelProperty({"票数", "W≤0.5KG"})
    private Integer subWaybillNum1;
    @ExcelProperty({"票数", "0.5KG<W≤1KG"})
    private Integer subWaybillNum2;
    @ExcelProperty({"票数", "1KG<W≤2KG"})
    private Integer subWaybillNum3;
    @ExcelProperty({"票数", "2KG<W≤3KG"})
    private Integer subWaybillNum4;
    @ExcelProperty({"票数", "3KG<W≤4KG"})
    private Integer subWaybillNum5;
    @ExcelProperty({"票数", "4KG<W≤5KG"})
    private Integer subWaybillNum6;
    @ExcelProperty({"票数", "5KG<W≤6KG"})
    private Integer subWaybillNum7;
    @ExcelProperty({"票数", "6KG<W≤7KG"})
    private Integer subWaybillNum8;
    @ExcelProperty({"票数", "7KG<W≤8KG"})
    private Integer subWaybillNum9;
    @ExcelProperty({"票数", "8KG<W≤9KG"})
    private Integer subWaybillNum10;
    @ExcelProperty({"票数", "9KG<W≤10KG"})
    private Integer subWaybillNum11;
    @ExcelProperty({"票数", "10KG<W≤20KG"})
    private Integer subWaybillNum12;
    @ExcelProperty({"票数", "20KG<W"})
    private Integer subWaybillNum13;

    @ExcelProperty({"重量", "W≤0.5KG"})
    private BigDecimal subWeight1;
    @ExcelProperty({"重量", "0.5KG<W≤1KG"})
    private BigDecimal subWeight2;
    @ExcelProperty({"重量", "1KG<W≤2KG"})
    private BigDecimal subWeight3;
    @ExcelProperty({"重量", "2KG<W≤3KG"})
    private BigDecimal subWeight4;
    @ExcelProperty({"重量", "3KG<W≤4KG"})
    private BigDecimal subWeight5;
    @ExcelProperty({"重量", "4KG<W≤5KG"})
    private BigDecimal subWeight6;
    @ExcelProperty({"重量", "5KG<W≤6KG"})
    private BigDecimal subWeight7;
    @ExcelProperty({"重量", "6KG<W≤7KG"})
    private BigDecimal subWeight8;
    @ExcelProperty({"重量", "7KG<W≤8KG"})
    private BigDecimal subWeight9;
    @ExcelProperty({"重量", "8KG<W≤9KG"})
    private BigDecimal subWeight10;
    @ExcelProperty({"重量", "9KG<W≤10KG"})
    private BigDecimal subWeight11;
    @ExcelProperty({"重量", "10KG<W≤20KG"})
    private BigDecimal subWeight12;
    @ExcelProperty({"重量", "20KG<W"})
    private BigDecimal subWeight13;

    @ExcelProperty({"净成本运费", "W≤0.5KG"})
    private BigDecimal subFee1;
    @ExcelProperty({"净成本运费", "0.5KG<W≤1KG"})
    private BigDecimal subFee2;
    @ExcelProperty({"净成本运费", "1KG<W≤2KG"})
    private BigDecimal subFee3;
    @ExcelProperty({"净成本运费", "2KG<W≤3KG"})
    private BigDecimal subFee4;
    @ExcelProperty({"净成本运费", "3KG<W≤4KG"})
    private BigDecimal subFee5;
    @ExcelProperty({"净成本运费", "4KG<W≤5KG"})
    private BigDecimal subFee6;
    @ExcelProperty({"净成本运费", "5KG<W≤6KG"})
    private BigDecimal subFee7;
    @ExcelProperty({"净成本运费", "6KG<W≤7KG"})
    private BigDecimal subFee8;
    @ExcelProperty({"净成本运费", "7KG<W≤8KG"})
    private BigDecimal subFee9;
    @ExcelProperty({"净成本运费", "8KG<W≤9KG"})
    private BigDecimal subFee10;
    @ExcelProperty({"净成本运费", "9KG<W≤10KG"})
    private BigDecimal subFee11;
    @ExcelProperty({"净成本运费", "10KG<W≤20KG"})
    private BigDecimal subFee12;
    @ExcelProperty({"净成本运费", "20KG<W"})
    private BigDecimal subFee13;
}
