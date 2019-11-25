package com.zx.codeanalysis.spring.springv1.springmvcv1.servlet;

import com.zx.codeanalysis.spring.annotation.Controller;
import com.zx.codeanalysis.spring.annotation.RequestMapping;
import com.zx.codeanalysis.spring.annotation.RequestParam;
import com.zx.codeanalysis.spring.springv1.conetxt.MyApplicationContext;
import com.zx.codeanalysis.spring.springv1.springmvcv1.HandlerAdapter;
import com.zx.codeanalysis.spring.springv1.springmvcv1.HandlerMapping;
import com.zx.codeanalysis.spring.springv1.springmvcv1.ModelAndView;
import com.zx.codeanalysis.spring.springv1.springmvcv1.ViewResolver;
import com.zx.codeanalysis.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@WebServlet(urlPatterns = "/dispatcherServlet", name = "dispatcherServlet"
//        , initParams = @WebInitParam(name = "contextConfigLocation"
//        , value = "classpath:application.properties")
//        , loadOnStartup = 2)
public class DispatcherServlet extends HttpServlet {

  private static Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);

  private List<HandlerMapping> handlerMappings = new ArrayList<>();

  private Map<HandlerMapping, HandlerAdapter> handlerAdapters = new HashMap<>();
  private List<ViewResolver> viewResolvers = new ArrayList<>();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String uri = req.getRequestURI();
//        String contextPath = req.getContextPath();
//        uri.replace(contextPath,"").replaceAll("/+","/");
//        HandlerMapping handlerMapping = this.handlerMapping.get(uri);
//        try {
//            handlerMapping.getMethod().invoke(handlerMapping.getController(),null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    try {
      doDispatch(req, resp);
    } catch (Exception e) {
      resp.getWriter().write("<font size='25' color='blue'>500 Exception</font><br/>Details:<br/>" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "")
          .replaceAll("\\s", "\r\n") + "<font color='green'><i>Copyright@GupaoEDU</i></font>");
      e.printStackTrace();

    }
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);

    MyApplicationContext context = new MyApplicationContext(config.getInitParameter("contextConfigLocation"));

    initStrategies(context);
  }

  private void initStrategies(MyApplicationContext context) {
    //有九种策略
    // 针对于每个用户请求，都会经过一些处理的策略之后，最终才能有结果输出
    // 每种策略可以自定义干预，但是最终的结果都是一致
    // ModelAndView

    // =============  这里说的就是传说中的九大组件 ================
    initMultipartResolver(context);//文件上传解析，如果请求类型是multipart将通过MultipartResolver进行文件上传解析
    initLocaleResolver(context);//本地化解析
    initThemeResolver(context);//主题解析

    //GPHandlerMapping 用来保存Controller中配置的RequestMapping和Method的一个对应关系
    initHandlerMappings(context);//通过HandlerMapping，将请求映射到处理器
    //HandlerAdapters 用来动态匹配Method参数，包括类转换，动态赋值
    initHandlerAdapters(context);//通过HandlerAdapter进行多类型的参数动态匹配

    initHandlerExceptionResolvers(context);//如果执行过程中遇到异常，将交给HandlerExceptionResolver来解析
    initRequestToViewNameTranslator(context);//直接解析请求到视图名

    //通过ViewResolvers实现动态模板的解析
    //自己解析一套模板语言
    initViewResolvers(context);//通过viewResolver解析逻辑视图到具体视图实现

    initFlashMapManager(context);//flash映射管理器

  }

  private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    HandlerMapping handlerMapping = getHandler(req);

    if (handlerMapping == null) {
      resp.getWriter().write("<font size='25' color='red'>404 Not Found</font><br/><font color='green'><i>Copyright@zhouxin</i></font>");
      return;
    }


    HandlerAdapter handlerAdapter = getHandlerAdapter(handlerMapping);

    ModelAndView modelAndView = handlerAdapter.handle(req, resp, handlerMapping);

    processDispatchResult(resp, modelAndView);
  }

  private void processDispatchResult(HttpServletResponse resp, ModelAndView modelAndView) throws Exception {

    if (null == modelAndView) {
      return;
    }

    if (this.viewResolvers.isEmpty()) {
      return;
    }

    for (ViewResolver viewResolver :
        viewResolvers) {

      if (!modelAndView.getViewName().equals(viewResolver.getViewName())) {
        continue;
      }
      String out = viewResolver.viewResolver(modelAndView);
      if (out != null) {
        resp.getWriter().write(out);
        break;
      }

    }
  }

  private HandlerAdapter getHandlerAdapter(HandlerMapping handlerMapping) {
    if (this.handlerAdapters.isEmpty()) {
      return null;
    }
    return this.handlerAdapters.get(handlerMapping);
  }

  private HandlerMapping getHandler(HttpServletRequest req) {

    if (this.handlerMappings.isEmpty()) {
      return null;
    }

    String uri = req.getRequestURI();
    String contextPath = req.getContextPath();
    uri.replace(contextPath, "").replaceAll("/+", "/");

    for (HandlerMapping handlerMapping :
        handlerMappings) {
      Matcher matcher = handlerMapping.getPattern().matcher(uri);
      if (!matcher.matches()) {
        continue;
      }
      return handlerMapping;
    }

    return null;
  }

  private void initHandlerMappings(MyApplicationContext context) {

    String[] beanNames = context.getBeanDefinitionNames();
    for (String beanName :
        beanNames) {
      Object controller = context.getBean(beanName);
      try {
//                Object targetObject = AopProxyUtils.getTargetObject(controller);
//                Class<?> clazz2 = targetObject.getClass();
        if (context.getBeanCacheMap().get(beanName) == null) {
          continue;
        }
        Class<?> clazz2 = context.getBeanCacheMap().get(beanName).getClass();
        if (!clazz2.isAnnotationPresent(Controller.class)) {
          continue;
        }
        String baseUrl = "";

        if (clazz2.isAnnotationPresent(RequestMapping.class)) {
          RequestMapping requestMapping = clazz2.getAnnotation(RequestMapping.class);
          baseUrl = requestMapping.value();
        }

        Method[] methods = clazz2.getMethods();

        for (Method method :
            methods) {
          if (!method.isAnnotationPresent(RequestMapping.class)) {
            continue;
          }
          RequestMapping annotation = method.getAnnotation(RequestMapping.class);
          String regex = ("/" + baseUrl + annotation.value().replaceAll("/+", ""));
          Pattern pattern = Pattern.compile(regex);
          this.handlerMappings.add(new HandlerMapping(pattern
              , controller
              , controller.getClass().getMethod(method.getName(), method.getParameterTypes())));
          LOGGER.info("-------mapping--------|url:{}|controller:{}|method:{}", pattern, controller, method);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void initHandlerAdapters(MyApplicationContext context) {

    for (HandlerMapping handlerMapping :
        handlerMappings) {
      Map<String, Integer> paramMapping = new HashMap<>();

      //只处理命名参数
      String beanName = SpringUtils.lowerFirstCase(handlerMapping.getController().getClass().getSimpleName().split("\\$\\$")[0]);
      Annotation[][] pa = new Annotation[0][];
      try {
        pa = context.getBeanCacheMap()
            .get(beanName).getClass()
            .getMethod(handlerMapping.getMethod().getName(), handlerMapping.getMethod().getParameterTypes()).getParameterAnnotations();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      }
//            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
      for (int i = 0; i < pa.length; i++) {
        for (Annotation a :
            pa[i]) {
          if (a instanceof RequestParam) {
            String paraName = ((RequestParam) a).value();
            if (!"".equals(paraName.trim())) {
              paramMapping.put(paraName, i);
            }
          }
        }
      }

      Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();
      for (int i = 0; i < paramTypes.length; i++) {
        Class<?> type = paramTypes[i];
        if (type == HttpServletResponse.class || type == HttpServletRequest.class) {
          paramMapping.put(type.getName(), i);
        }
      }

      this.handlerAdapters.put(handlerMapping, new HandlerAdapter(paramMapping));

    }
  }

  private void initViewResolvers(MyApplicationContext context) {
    //在页面敲一个 http://localhost/first.html
    //解决页面名字和模板文件关联的问题
    String templateRoot = context.getConfig().getProperty("templateRoot");
    String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getPath();

    File templateRootDir = new File(templateRootPath);

    for (File template : Objects.requireNonNull(templateRootDir.listFiles())) {
      this.viewResolvers.add(new ViewResolver(template.getName(), template));
    }
  }

  private void initFlashMapManager(MyApplicationContext context) {
  }

  private void initRequestToViewNameTranslator(MyApplicationContext context) {
  }

  private void initHandlerExceptionResolvers(MyApplicationContext context) {
  }

  private void initThemeResolver(MyApplicationContext context) {
  }

  private void initLocaleResolver(MyApplicationContext context) {
  }

  private void initMultipartResolver(MyApplicationContext context) {
  }
}
