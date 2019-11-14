package com.zx.utils.trans;

import java.util.List;

/**
 * @author zhouxin
 * @since 2019/11/12
 */
public class ResultDTO {

  private String from;

  private String to;

  private List<TransDTO> transResult;

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public List<TransDTO> getTransResult() {
    return transResult;
  }

  public void setTransResult(List<TransDTO> transResult) {
    this.transResult = transResult;
  }

  @Override
  public String toString() {
    return "ResultDTO{" +
        "from='" + from + '\'' +
        ", to='" + to + '\'' +
        ", transResult=" + transResult +
        '}';
  }
}
