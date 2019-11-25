package com.zx.codeanalysis.spring.springv1.springmvcv1;

import java.util.Map;

/**
 * @author zhouxin
 * @date 2018-10-28
 */
public class ModelAndView {
  private String viewName;
  private Map<String, Object> model;

  public ModelAndView(String viewName, Map<String, Object> model) {
    this.viewName = viewName;
    this.model = model;
  }

  public String getViewName() {
    return viewName;
  }

  public void setViewName(String viewName) {
    this.viewName = viewName;
  }

  public Map<String, Object> getModel() {
    return model;
  }

  public void setModel(Map<String, Object> model) {
    this.model = model;
  }
}
