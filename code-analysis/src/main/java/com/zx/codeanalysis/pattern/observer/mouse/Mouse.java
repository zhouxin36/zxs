package com.zx.codeanalysis.pattern.observer.mouse;

import com.zx.codeanalysis.pattern.observer.core.EventLisenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mouse extends EventLisenter {

    private static Logger logger = LoggerFactory.getLogger(Mouse.class);

    public void click(){
        logger.info("鼠标单击");
        this.trigger(MouseEventType.ON_CLICK);
    }


    public void doubleClick(){
        logger.info("鼠标双击");
        this.trigger(MouseEventType.ON_DOUBLE_CLICK);
    }

    public void up(){
        logger.info("鼠标弹起");
        this.trigger(MouseEventType.ON_UP);
    }

    public void down(){
        logger.info("鼠标按下");
        this.trigger(MouseEventType.ON_DOWN);
    }


    public void wheel(){
        logger.info("鼠标滚动");
        this.trigger(MouseEventType.ON_WHEEL);
    }

    public void move(){
        logger.info("鼠标移动");
        this.trigger(MouseEventType.ON_MOVE);
    }

    public void over(){
        logger.info("鼠标悬停");
        this.trigger(MouseEventType.ON_OVER);
    }


}
