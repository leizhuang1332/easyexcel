package com.alibaba.easyexcel.test.demo.write;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.IndexedColors;

public class CustomHorizontalCellStyleStrategy extends HorizontalCellStyleStrategy {

    @Override
    public String uniqueValue() {
        return "CustomHorizontalCellStyleStrategy";
    }

    public CustomHorizontalCellStyleStrategy(WriteCellStyle headWriteCellStyle,
                                             List<WriteCellStyle> contentWriteCellStyleList) {
        super(headWriteCellStyle, contentWriteCellStyleList);
    }

    public static WriteHandler loadHeadHandler() {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontName("宋体");
        headWriteFont.setFontHeightInPoints((short)14);
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);
        return new CustomHorizontalCellStyleStrategy(headWriteCellStyle, new ArrayList<WriteCellStyle>());
    }
}
