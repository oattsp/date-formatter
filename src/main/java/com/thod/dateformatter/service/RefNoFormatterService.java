package com.thod.dateformatter.service;

import com.thod.dateformatter.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class RefNoFormatterService {

    public String format(String refNo) {
        if (refNo == null) return StringUtils.EMPTY;

        refNo = refNo.trim();

        if (refNo.isEmpty()) return StringUtils.EMPTY;

        String[] split = refNo.split("[\"|]");

        return Arrays.stream(split).map(String::trim)
                .filter(str -> !str.isEmpty())
                .collect(Collectors.joining(","));
    }

}
