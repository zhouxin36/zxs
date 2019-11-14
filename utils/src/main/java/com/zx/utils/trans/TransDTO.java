package com.zx.utils.trans;

/**
 * @author zhouxin
 * @since 2019/11/12
 */
public class TransDTO {

  private String src;

  private String dst;

  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }

  @Override
  public String toString() {
    return "TransDTO{" +
        "src='" + src + '\'' +
        ", dst='" + dst + '\'' +
        '}';
  }

  public String getDst() {
    return dst;
  }

  public void setDst(String dst) {
    this.dst = dst;
  }
}
