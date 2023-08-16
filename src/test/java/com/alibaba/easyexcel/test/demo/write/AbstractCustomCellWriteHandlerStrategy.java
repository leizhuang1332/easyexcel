package com.alibaba.easyexcel.test.demo.write;

import java.util.List;
import java.util.Objects;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.AbstractCellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class AbstractCustomCellWriteHandlerStrategy extends AbstractCellWriteHandler {

    public CellStyle fmtNumCellStyle;

    public CellStyle fmtRateCellStyle;

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                 List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
//        if (head == null) {
//            return;
//        }

        if (!isHead && cell.getCellTypeEnum()==CellType.NUMERIC){
            String fieldName = "head.getFieldName()";
            Workbook workbook = writeSheetHolder.getParentWriteWorkbookHolder().getWorkbook();
            //设置千分号整数
            List<String> fmtNumFields = getFmtNumFields();
            //设置千分号小数
            List<String> fmtRateFields = getFmtRateFields();
            if (true) {
                if(Objects.isNull(fmtNumCellStyle)){
                    fmtNumCellStyle = workbook.createCellStyle();
                }
                //整数
                short fmtFormat = HSSFDataFormat.getBuiltinFormat("#,##0");
                fmtNumCellStyle.setDataFormat(fmtFormat);
                cell.setCellStyle(fmtNumCellStyle);
            }else if (fmtRateFields.contains(fieldName)){
                if(Objects.isNull(fmtRateCellStyle)){
                    fmtRateCellStyle = workbook.createCellStyle();
                }
                //小数
                short fmtFormat = HSSFDataFormat.getBuiltinFormat("#,##0.00");
                fmtRateCellStyle.setDataFormat(fmtFormat);
                cell.setCellStyle(fmtRateCellStyle);
            }
        }
        //设置宽度
        boolean needSetWidth = relativeRowIndex != null && (isHead || relativeRowIndex == 0);
        if (!needSetWidth) {
            return;
        }
        Integer width = columnWidth(head, cell.getColumnIndex());
        if (width != null) {
            width = width * 256;
            writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), width);
        }
    }



    protected abstract Integer columnWidth(Head head, Integer columnIndex);

    /**
     * gets the FmtNumFields column when head create
     */
    protected abstract List<String> getFmtNumFields();

    /**
     * gets the FmtRateFields column when head create
     */
    protected abstract List<String> getFmtRateFields();
}
