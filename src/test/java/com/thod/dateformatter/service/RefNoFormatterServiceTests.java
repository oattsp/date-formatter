package com.thod.dateformatter.service;

import com.thod.dateformatter.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RefNoFormatterServiceTests {

    @Autowired
    private RefNoFormatterService refNoFormatterService;

    @Test
    void testFormat() {
        String format = refNoFormatterService.format(mockRefNo.case1);
        Assertions.assertEquals("refNo1", format);

        format = refNoFormatterService.format(mockRefNo.case2);
        Assertions.assertEquals("refNo1,refNo2,refNo3", format);

        format = refNoFormatterService.format(mockRefNo.case3);
        Assertions.assertEquals("refNo1,refNo2,refNo3", format);

        format = refNoFormatterService.format(mockRefNo.case4);
        Assertions.assertEquals("refNo1,refNo2,refNo3", format);

        format = refNoFormatterService.format(mockRefNo.case5);
        Assertions.assertEquals("refNo1,refNo2,refNo3", format);

        format = refNoFormatterService.format(mockRefNo.case6);
        Assertions.assertEquals("refNo1,refNo2,refNo3", format);

        format = refNoFormatterService.format(mockRefNo.case7);
        Assertions.assertEquals("refNo1,refNo2,refNo3", format);

        format = refNoFormatterService.format(mockRefNo.case8);
        Assertions.assertEquals(StringUtils.EMPTY, format);

        format = refNoFormatterService.format(mockRefNo.case9);
        Assertions.assertEquals(StringUtils.EMPTY, format);

        format = refNoFormatterService.format(mockRefNo.case10);
        Assertions.assertEquals(StringUtils.EMPTY, format);

        System.out.println(mockRefNo.case10);
    }

    interface mockRefNo {
        String case1 = "refNo1";
        String case2 = "refNo1\"|\"refNo2\"|\"refNo3";
        String case3 = "refNo1\"|\"refNo2\"|\"refNo3\"|\"";
        String case4 = "refNo1\"|\"refNo2\"|\"refNo3\"|\"\"|\"\"|\"";
        String case5 = "\"|\"\"|\"refNo1\"|\"\"|\"refNo2\"|\"refNo3\"|\"\"|\"\"|\"";
        String case6 = "refNo1 \"|\" refNo2\"|\"refNo3 \"|\"";
        String case7 = "refNo1\"|\"\"refNo2\"|\"refNo3\"|\"\"";
        String case8 = null;
        String case9 = "";
        String case10 = "\"|\"\"|\"\"|\"\"|\"\"|\"\"|\"";
    }
}
