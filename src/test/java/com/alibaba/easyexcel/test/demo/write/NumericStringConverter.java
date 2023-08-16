package com.alibaba.easyexcel.test.demo.write;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumericStringConverter implements Converter<String> {

    private static final Logger logger = LoggerFactory.getLogger(NumericStringConverter.class);

    @Override
    public Class<String> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                    GlobalConfiguration globalConfiguration) {
        logger.info("exce转化1");
        return cellData.getStringValue();
    }

    @Override
    public CellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty,
                                          GlobalConfiguration globalConfiguration) {
        logger.info("exce转化2:{}",value);
        String cleanedValue = value.replaceAll(",", "");
        try {
            BigDecimal bigDecimal = new BigDecimal(cleanedValue);
            String formattedValue = formatBigDecimal(bigDecimal); // 格式化为带千分位的字符串
            return new CellData<>(CellDataTypeEnum.NUMBER, formattedValue);
        } catch (NumberFormatException e) {
            return new CellData<>(value);
        }

    }
    // 格式化 BigDecimal 为带千分位的字符串
    private String formatBigDecimal(BigDecimal value) {
        NumberFormat numberFormat = DecimalFormat.getNumberInstance(Locale.US);
        return numberFormat.format(value);
    }

}
