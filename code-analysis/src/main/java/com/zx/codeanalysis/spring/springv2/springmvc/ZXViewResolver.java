package com.zx.codeanalysis.spring.springv2.springmvc;

import com.zx.codeanalysis.spring.springv1.springmvcv1.ModelAndView;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author zhouxin
 * @date 2018/11/14
 */
public class ZXViewResolver {

  private String path;

  private File template;

  public ZXViewResolver(String path, File template) {
    this.path = path;
    this.template = template;
  }

  public String getPath() {
    return path;
  }

  public File getTemplate() {
    return template;
  }

  public String viewResolver(ModelAndView handler) {
    return getStringBuilder(handler).toString();
  }

  private StringBuilder getStringBuilder(ModelAndView handler) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      RandomAccessFile randomAccessFile = new RandomAccessFile(template, "r");
      String line;
      while (!StringUtils.isBlank(line = randomAccessFile.readLine())) {
        line = new String(line.getBytes(ISO_8859_1), UTF_8);
        Matcher matcher = matcher(line);
        while (matcher.find()) {
          for (int i = 0; i < matcher.groupCount(); i++) {
            String group = matcher.group(i);
            String name = group.replace("￥", "").replace("{", "").replace("}", "");
            Object o = handler.getModel()
                .get(name);
            if (o == null) {
              continue;
            }
            line = line.replaceAll("￥\\{" + name + "\\}", o.toString());
          }
        }
        stringBuilder.append(line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stringBuilder;
  }

  private Matcher matcher(String str) {
    Pattern pattern = Pattern.compile("￥\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(str);
    return matcher;
  }
}
