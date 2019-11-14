package com.zx.jdkanalysis.bean.mbean;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * @author zhouxin
 * @date 2019/1/25
 */
public class Main {

    public static void main(String[] args)
            throws Exception {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.example:type=Hello");
        Hello mbean = new Hello();
        mbs.registerMBean(mbean, name);

        System.out.println("Waiting forever...");
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * @author zhouxin
     * @date 2019/1/25
     */
    public static class Hello extends NotificationBroadcasterSupport implements HelloMBean {
        public void sayHello() {
            System.out.println("hello, world");
        }

        public int add(int x, int y) {
            return x + y;
        }

        public String getName() {
            return this.name;
        }

        public int getCacheSize() {
            return this.cacheSize;
        }

        public synchronized void setCacheSize(int size) {

            this.cacheSize = size;
            System.out.println("Cache size now " + this.cacheSize);
        }

        @Override
        public MBeanNotificationInfo[] getNotificationInfo() {
            String[] types = new String[]{
                    AttributeChangeNotification.ATTRIBUTE_CHANGE
            };

            String name = AttributeChangeNotification.class.getName();
            String description = "An attribute of this MBean has changed";
            MBeanNotificationInfo info =
                    new MBeanNotificationInfo(types, name, description);
            return new MBeanNotificationInfo[]{info};
        }

        private final String name = "Reginald";
        private int cacheSize = DEFAULT_CACHE_SIZE;
        private static final int
                DEFAULT_CACHE_SIZE = 200;
    }

    /**
     * @author zhouxin
     * @date 2019/1/25
     */
    public static interface HelloMBean {

        public void sayHello();
        public int add(int x, int y);

        public String getName();

        public int getCacheSize();
        public void setCacheSize(int size);
    }
}
