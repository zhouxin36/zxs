/**
 * 格式转换
 * addConverter Converter,ConverterFactory, or GenericConverter interfaces
 * addFormatter Formatter
 * 1、{@link com.zx.spingbootmicrowebflux.databinding.formatter.MultiDateFormatter}
 * {@link com.zx.spingbootmicrowebflux.databinding.editor.StringToInteger}
 * 注册{@link com.zx.spingbootmicrowebflux.config.MyConfiguration#addFormatters}好像只能GET
 * <p>
 * 2、{@link com.zx.spingbootmicrowebflux.databinding.editor.MultiDateParseEditor}
 * 注册{@link com.zx.spingbootmicrowebflux.databinding.DataBindingController} 好像只能GET
 * <p>
 * 3、{@link com.zx.spingbootmicrowebflux.config.MyConfiguration}
 * <p>
 * <p>
 * 1、2   调用方法分叉{@link org.springframework.beans.TypeConverterDelegate#convertIfNecessary(String propertyName
 *, Object oldValue, Object newValue, Class requiredType, TypeDescriptor typeDescriptor)}
 * 2优先级比1高
 *
 * @author zhouxin
 * @since 2019/3/4
 */
package com.zx.spingbootmicrowebflux.databinding;

import org.springframework.core.convert.TypeDescriptor;