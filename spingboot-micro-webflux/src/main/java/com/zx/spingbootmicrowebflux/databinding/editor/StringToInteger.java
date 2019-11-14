package com.zx.spingbootmicrowebflux.databinding.editor;

import org.springframework.core.convert.converter.Converter;

/**
 * @author zhouxin
 * @since 2019/3/6
 */
public class StringToInteger implements Converter<String, Integer> {
    public Integer convert(String source) {
        return Integer.valueOf(source.replace("(",""));
    }
}
