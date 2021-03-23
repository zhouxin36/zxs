package com.zx.jdkanalysis.动态追踪技术;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @author zhouxin
 * @since 2021/3/18
 */
public class Attacher {
    public static void main(String[] args) throws
            AttachNotSupportedException, IOException,
            AgentLoadException, AgentInitializationException {
        // 传入目标 JVM pid
        VirtualMachine vm = VirtualMachine.attach("12560");
        vm.loadAgent("F:/ideaproject/zxs/instrumentation/build/libs/instrumentation.jar");
    }
}
