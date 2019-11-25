package com.zx.codeanalysis.spring.springv1.springmvcv1;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhouxin
 * @date 2018-10-28
 */
public class ViewResolver {

  private String viewName;

  private File template;

  public ViewResolver(String viewName, File template) {
    this.viewName = viewName;
    this.template = template;
  }

  public String viewResolver(ModelAndView modelAndView) throws Exception {
    StringBuffer sb = new StringBuffer();

    RandomAccessFile ra = new RandomAccessFile(this.template, "r");

    try {
      String line = null;
      while (null != (line = ra.readLine())) {
        line = new String(line.getBytes("ISO-8859-1"), "utf-8");
        Matcher m = matcher(line);
        while (m.find()) {
          for (int i = 1; i <= m.groupCount(); i++) {

            //要把￥{}中间的这个字符串给取出来
            String paramName = m.group(i);
            Object paramValue = modelAndView.getModel().get(paramName);
            if (null == paramValue) {
              continue;
            }
            line = line.replaceAll("￥\\{" + paramName + "\\}", paramValue.toString());
            line = new String(line.getBytes("utf-8"), "ISO-8859-1");
          }
        }
        sb.append(line);
      }
    } finally {
      ra.close();
    }

    return sb.toString();

  }

  public String getViewName() {
    return viewName;
  }

  public void setViewName(String viewName) {
    this.viewName = viewName;
  }

  public File getTemplate() {
    return template;
  }

  public void setTemplate(File template) {
    this.template = template;
  }

  private Matcher matcher(String str) {
    Pattern pattern = Pattern.compile("￥\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(str);
    return matcher;
  }


}
