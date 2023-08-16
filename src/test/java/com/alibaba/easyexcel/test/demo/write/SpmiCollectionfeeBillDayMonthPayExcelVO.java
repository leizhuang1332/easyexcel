package com.alibaba.easyexcel.test.demo.write;

import java.math.BigDecimal;

import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 云路供应链科技有限公司 版权所有 © Copyright 2019<br>
 * 联合账单日汇总Excel VO
 *
 * @author zuting ou
 * @project: SpmiUnionDetailVO<br>
 * @since Created in 2020/5/22 2:01 下午
 */
@Data
@Accessors(chain = true)
public class SpmiCollectionfeeBillDayMonthPayExcelVO extends BaseRowModel{


    /**
     * 账单类型名称
     */
    private String billTypeName;

    /**
     * 客户code
     */
    private String customerCode;
    /**
     * 结算网点名称
     */
    private String networkName;

    /**
     * 结算加盟商名称
     */
    private String franchiseeNetworkName;
    /**
     * 结算财务中心名称
     */
    private String financialCenterName;

    /**
     * 总票数
     */
    private BigDecimal waybillNumSum;

    /**
     * 总件数
     */
    private Integer waybillPieceSum;


    private BigDecimal collectionfeeSum;

/*    private BigDecimal customerFeeSum;*/



    /**
     * 手续费  应收-结算网点=签收网点，列表展示费用类型【应付派件手续费】；应付-结算网点=寄件网点，列表展示费用类型【应收寄件手续费】
     */
//    private BigDecimal collectFeeSum;

    private String dateTime;

    private Integer signWaybillNum;

    private Integer settleSum;

    private Integer customerRefundSum;

    private Integer badPieceSum;

    private Integer refundSum;

//    private Integer collectFeeIsZeroSum;


}
