/**
 * @author zhouxin
 * @date 2018/10/16
 */
module code.analysis.main {
    requires logback.access;
    requires cglib;
    requires asm;
    requires mysql.connector.java;
    requires java.sql;
    requires snakeyaml;
    requires commons.lang3;
    requires tomcat.embed.core;
    requires jdk.management;
    requires java.instrument;
    requires java.base;
    requires org.slf4j;

    exports com.zx.codeanalysis.pattern.proxy.cglib;
    exports com.zx.codeanalysis.spring.demo;
    exports com.zx.codeanalysis.base;
}