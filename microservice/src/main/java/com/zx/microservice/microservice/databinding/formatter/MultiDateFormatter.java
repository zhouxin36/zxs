package com.zx.microservice.microservice.databinding.formatter;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author zhouxin
 * @since 2019/3/4
 */
public class MultiDateFormatter implements Formatter<LocalDateTime> {

    private List<DateTimeFormatter> dateFormats;
    public MultiDateFormatter() {
        dateFormats = new ArrayList<>(4);
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy+MM+dd HH:mm:ss"));
    }

    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        if ( !StringUtils.hasText(text)) {
            // Treat empty String as null value.
            return null;
        }else {
            Exception e1 = null;
            for (DateTimeFormatter dateFormat : dateFormats) {
                try {
                    return LocalDateTime.parse(text,dateFormat);
                } catch (Exception e) {
                    e1 = e;
                }
            }
            assert e1 != null;
            throw new IllegalArgumentException("Could not parse date: " + e1.getMessage(), e1);
        }
    }

    @Override
    public String print(LocalDateTime object, Locale locale) {
        return dateFormats.get(0).format(object);
    }
}
