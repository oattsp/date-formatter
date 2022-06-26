package com.thod.dateformatter.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

@SpringBootTest
public class DateFormatterServiceTests {
    @Autowired
    private DateFormatterService dateFormatterService;

    @Test
    void testValidateDateString() {
        String date = Assertions.assertDoesNotThrow(() -> dateFormatterService.verifyFormat(mockupDate.case1));
        Assertions.assertEquals("01/02/2022", date);

        date = Assertions.assertDoesNotThrow(() -> dateFormatterService.verifyFormat(mockupDate.case2));
        Assertions.assertEquals("01/02/2022", date);

        date = Assertions.assertDoesNotThrow(() -> dateFormatterService.verifyFormat(mockupDate.case3));
        Assertions.assertEquals("11/02/2022", date);

        date = Assertions.assertDoesNotThrow(() -> dateFormatterService.verifyFormat(mockupDate.case4));
        Assertions.assertEquals("01/12/2022", date);

        Assertions.assertThrows(RuntimeException.class, () -> dateFormatterService.verifyFormat(mockupDate.case5));
        Assertions.assertThrows(RuntimeException.class, () -> dateFormatterService.verifyFormat(mockupDate.case6));
        Assertions.assertThrows(RuntimeException.class, () -> dateFormatterService.verifyFormat(mockupDate.case7));
        Assertions.assertThrows(RuntimeException.class, () -> dateFormatterService.verifyFormat(mockupDate.case8));
        Assertions.assertThrows(ParseException.class, () -> dateFormatterService.verifyFormat(mockupDate.case9));
        Assertions.assertThrows(RuntimeException.class, () -> dateFormatterService.verifyFormat(mockupDate.case10));
        Assertions.assertThrows(ParseException.class, () -> dateFormatterService.verifyFormat(mockupDate.case11));
        Assertions.assertThrows(ParseException.class, () -> dateFormatterService.verifyFormat(mockupDate.case12));
        Assertions.assertThrows(ParseException.class, () -> dateFormatterService.verifyFormat(mockupDate.case13));
        Assertions.assertThrows(ParseException.class, () -> dateFormatterService.verifyFormat(mockupDate.case14));
        Assertions.assertThrows(NullPointerException.class, () -> dateFormatterService.verifyFormat(null));
    }

    @Test
    void testDateExcel() throws IOException {
        FileInputStream file = new FileInputStream("src/test/resources/mockup_date.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        sheet.setRowBreak(5);
        sheet.setColumnBreak(2);

        DataFormatter dataFormatter = new DataFormatter(Locale.US);

        String date = Assertions.assertDoesNotThrow(() ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(0).getCell(0))));
        Assertions.assertEquals("02/01/2022", date);

        date = Assertions.assertDoesNotThrow(() ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(1).getCell(0))));
        Assertions.assertEquals("12/02/2022", date);

        Assertions.assertThrows(ParseException.class, () ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(2).getCell(0))));

        Assertions.assertThrows(ParseException.class, () ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(3).getCell(0))));

        date = Assertions.assertDoesNotThrow(() ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(0).getCell(1))));
        Assertions.assertEquals("02/01/2022", date);

        date = Assertions.assertDoesNotThrow(() ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(1).getCell(1))));
        Assertions.assertEquals("12/02/2022", date);

        Assertions.assertThrows(ParseException.class, () ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(2).getCell(1))));

        Assertions.assertThrows(ParseException.class, () ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(3).getCell(1))));

        date = Assertions.assertDoesNotThrow(() ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(0).getCell(2))));
        Assertions.assertEquals("02/01/2022", date);

        date = Assertions.assertDoesNotThrow(() ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(1).getCell(2))));
        Assertions.assertEquals("12/02/2022", date);

        Assertions.assertThrows(ParseException.class, () ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(2).getCell(2))));

        Assertions.assertThrows(ParseException.class, () ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(3).getCell(2))));

        date = Assertions.assertDoesNotThrow(() ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(4).getCell(2))));
        Assertions.assertEquals("02/01/2022", date);

        Assertions.assertThrows(RuntimeException.class, () ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(5).getCell(2))));

        date = Assertions.assertDoesNotThrow(() ->
                dateFormatterService.verifyFormat(dataFormatter.formatCellValue(sheet.getRow(6).getCell(2))));
        Assertions.assertEquals("02/01/2565", date);
    }

    interface mockupDate {
        String case1 = "01/02/2022";
        String case2 = "1/02/2022";
        String case3 = "11/2/2022";
        String case4 = "1/12/2022";
        String case5 = "1/1/22";
        String case6 = "1/2/2/2022";
        String case7 = "00012/2022";
        String case8 = "";
        String case9 = "1/Jun/2022";
        String case10 = "01-02-2022";
        String case11 = "as/ww/erty";
        String case12 = "45/01/2022";
        String case13 = "45/01/2020";
        String case14 = "20/13/2020";
    }
}
