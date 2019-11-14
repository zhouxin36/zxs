package com.zx.codeanalysis.spring.springv1.servlet;

import com.zx.codeanalysis.spring.springv1.conetxt.MyApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhouxin
 * @date 2018/10/25
 */

//@WebServlet(urlPatterns = "/servlet2", name = "servlet2"
//        , initParams = @WebInitParam(name = "contextConfigLocation"
//        , value = "classpath:application.properties")
//        , loadOnStartup = 2)
public class Dispatch2Servlet extends HttpServlet {

    private final static Logger logger = LoggerFactory.getLogger(Dispatch2Servlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("-----------------  调用doPost ---------------------");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info("-----------------  调用init ---------------------");

        new MyApplicationContext(config.getInitParameter("contextConfigLocation"));
    }

}