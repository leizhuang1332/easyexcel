package com.alibaba.easyexcel.test.demo.write;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 写的常见写法
 *
 * @author Jiaju Zhuang
 */
@Ignore
public class MyWriteTest {

    @Test
    public void simpleWrite() {
        // 写法1
        String fileName = MyWriteTest.class.getResource("/")
            .getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = new ExcelWriter(new FileOutputStream(fileName), ExcelTypeEnum.XLSX);
            WriteSheet writeSheet = getSheet();

            excelWriter.write(data(), writeSheet);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    public static List<SpmiCollectionfeeBillDayMonthPayExcelVO> data() {
        List<SpmiCollectionfeeBillDayMonthPayExcelVO> datas = new ArrayList<>();
        SpmiCollectionfeeBillDayMonthPayExcelVO vo = new SpmiCollectionfeeBillDayMonthPayExcelVO();
        vo.setBadPieceSum(132323111);
        datas.add(vo);
        return datas;
    }

    public static WriteSheet getSheet() {

        List sheetList = new ArrayList<List<String>>();

        List sheetHead1 = new ArrayList<String>();
        sheetHead1.add("账单类型");
        List sheetHead2 = new ArrayList<String>();
        sheetHead2.add("客户编码");
        List sheetHead3 = new ArrayList<String>();
        sheetHead3.add("结算网点");
        List sheetHead4 = new ArrayList<String>();
        sheetHead4.add("所属加盟商");
        List sheetHead5 = new ArrayList<String>();
        sheetHead5.add("结算财务中心");
        List sheetHead6 = new ArrayList<String>();
        sheetHead6.add("总票数");
        List sheetHead7 = new ArrayList<String>();
        sheetHead7.add("总件数");
        List sheetHead8 = new ArrayList<String>();
        sheetHead8.add("货款金额");

        List sheetHead9 = new ArrayList<String>();
        List sheetHead10 = new ArrayList<String>();
        List sheetHead11 = new ArrayList<String>();

        List sheetHead12 = new ArrayList<String>();
        sheetHead12.add("签收票数");
        List sheetHead13 = new ArrayList<String>();
        sheetHead13.add("结算票数");
        List sheetHead14 = new ArrayList<String>();
        sheetHead14.add("客户返款票数");
        List sheetHead15 = new ArrayList<String>();
        sheetHead15.add("问题件票数");
        List sheetHead16 = new ArrayList<String>();
        sheetHead16.add("退回件票数");

        List sheetHead17 = new ArrayList<String>();
        List sheetHead18 = new ArrayList<String>();

        String timeHeadName =  "日期";
        sheetHead11.add("产生" + timeHeadName);

        sheetList.add(sheetHead1);
        sheetList.add(sheetHead2);
        sheetList.add(sheetHead3);
        sheetList.add(sheetHead4);
        sheetList.add(sheetHead5);
        sheetList.add(sheetHead6);
        sheetList.add(sheetHead7);
        sheetList.add(sheetHead8);


        sheetList.add(sheetHead11);
        sheetList.add(sheetHead12);
        sheetList.add(sheetHead13);
        sheetList.add(sheetHead14);
        sheetList.add(sheetHead15);
        sheetList.add(sheetHead16);


        WriteSheet writeSheet = initSheetTest(sheetList, "日汇总", "CB");
        return writeSheet;
    }

    public static WriteSheet initSheetTest(List<List<String>> headList, String sheetName,String lgType) {
        return EasyExcel.writerSheet(0,sheetName)
            .registerWriteHandler(new CustomCellWriteHandlerStrategy(20))
            .head(SpmiCollectionfeeBillDayMonthPayExcelVO.class)
            .head(headList)
            .build();
    }
}
