package com.thod.dateformatter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class DateFormatterService {
    private final static String PATTERN = "dd/MM/yyyy";

    public String verifyFormat(String date) throws ParseException {
        if (date == null) {
            throw new NullPointerException("date is null");
        }

        date = date.trim();

        if (date.length() < 8 || date.length() > 10) {
            throw new RuntimeException("date length : " + date.length());
        }

        if (date.split("/").length != 3) {
            throw new RuntimeException("date pattern error : " + date);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M/yyyy", Locale.US);
        simpleDateFormat.setLenient(false);
        Date dt = simpleDateFormat.parse(date);
        return new SimpleDateFormat(PATTERN, Locale.US).format(dt);
    }

}
