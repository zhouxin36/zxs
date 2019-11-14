package com.zx.codeanalysis.pattern.observer;

import com.zx.codeanalysis.pattern.observer.core.Event;
import com.zx.codeanalysis.pattern.observer.mouse.Mouse;
import com.zx.codeanalysis.pattern.observer.mouse.MouseEventCallback;
import com.zx.codeanalysis.pattern.observer.mouse.MouseEventType;

import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        //观察者和被观察者之间没有必然联系
        //注册的时候，才产生联系
        //解耦
        try {
            MouseEventCallback target = new MouseEventCallback();
            Method callback = MouseEventCallback.class.getMethod("onClick", Event.class);

            //人为的调用鼠标的单击事件
            Mouse mouse = new Mouse();
            mouse.addLisenter(MouseEventType.ON_CLICK, target,callback);
            mouse.click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
