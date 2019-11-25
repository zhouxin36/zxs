package com.zx.codeanalysis.pattern.observer.mouse;

import com.zx.codeanalysis.pattern.observer.core.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MouseEventCallback {

  private static Logger LOGGER = LoggerFactory.getLogger(MouseEventCallback.class);

  public void onClick(Event e) {
    LOGGER.info("这是鼠标单击以后要执行的逻辑");
    LOGGER.info("=======触发鼠标单击事件========\n" + e);
  }

  public void onDoubleClick(Event e) {
    LOGGER.info("=======触发鼠标双击事件========\n" + e);
  }

  public void onUp(Event e) {
    LOGGER.info("=======触发鼠标弹起事件========\n" + e);
  }

  public void onDown(Event e) {
    LOGGER.info("=======触发鼠标按下事件========\n" + e);
  }

  public void onMove(Event e) {
    LOGGER.info("=======触发鼠标移动事件========\n" + e);
  }

  public void onWheel(Event e) {
    LOGGER.info("=======触发鼠标滚动事件========\n" + e);
  }

  public void onOver(Event e) {
    LOGGER.info("=======触发鼠标悬停事件========\n" + e);
  }

}
