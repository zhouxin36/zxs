package com.zx.microservice.microservice.databinding.editor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomNumberEditor;

/**
 * @author zhouxin
 * @since 2019/3/4
 */
public class MyCustomNumberEditor extends CustomNumberEditor {

  public MyCustomNumberEditor() throws IllegalArgumentException {
    super(Integer.class, true);
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    if (StringUtils.isBlank(text) || text.trim().equals("")) {
      setValue(null);
    } else {
      long value;
      try {
        //按照标准的数字格式尝试转换
        value = Long.parseLong(text);
      } catch (NumberFormatException e) {
        //尝试去除逗号 然后再转换
        text = text.replace(",", "");
        value = Long.parseLong(text);
      }
      //转好之后将值返给被映射的属性
      setValue(value);
    }
  }
}
