package com.zx.codeanalysis.spring;

import com.zx.codeanalysis.spring.springv2.ZXServlet;
import javax.servlet.annotation.WebServlet;
import org.apache.catalina.Host;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

/**
 * -classpath E:\idea-project\zxs\code-analysis\build\classes\java\main;E:\idea-project\zxs\code-analysis\build\resources\main;
 * --add-opens java.base/java.lang=cglib
 *
 * @author zhouxin
 * @date 2019/1/7
 */
public class TomcatRun {

  public static void main(String[] args) throws Exception {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8989);
    Host host = tomcat.getHost();
    host.setName("localhost");
    host.setAppBase("webapps");

    tomcat.addContext("/", null);

    Class<?> clazz = ZXServlet.class;
    WebServlet webServlet = clazz.getAnnotation(WebServlet.class);
    Wrapper wrapper = tomcat.addServlet("/", webServlet.name(), new ZXServlet());
    wrapper.addMapping(webServlet.urlPatterns()[0]);
    wrapper.addInitParameter(webServlet.initParams()[0].name(), webServlet.initParams()[0].value());

    Connector connector = new Connector();
    connector.setPort(8989);
    connector.setURIEncoding("UTF-8");
    tomcat.getService().addConnector(connector);

    tomcat.start();
    Thread thread = new Thread(() -> {
      tomcat.getServer().await();
    });
    thread.start();
  }
}