package com.zx.codeanalysis.spring.springv2;

import com.zx.codeanalysis.spring.annotation.Controller;
import com.zx.codeanalysis.spring.annotation.RequestMapping;
import com.zx.codeanalysis.spring.annotation.RequestParam;
import com.zx.codeanalysis.spring.springv1.springmvcv1.ModelAndView;
import com.zx.codeanalysis.spring.springv2.context.ZXApplicationContext;
import com.zx.codeanalysis.spring.springv2.springmvc.ZXHandleMapping;
import com.zx.codeanalysis.spring.springv2.springmvc.ZXHandlerAdapter;
import com.zx.codeanalysis.spring.springv2.springmvc.ZXViewResolver;
import com.zx.codeanalysis.utils.SpringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
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

/**
 * @author zhouxin
 * @date 2018-11-12
 */
@WebServlet(name = "zxServlet1", urlPatterns = "/*", loadOnStartup = 1
        , initParams = @WebInitParam(name = "classLocation"
        , value = "classpath:application.yml"))
public class ZXServlet extends HttpServlet {

    private List<ZXHandleMapping> handleMappings = new ArrayList<>();

    private Map<ZXHandleMapping, ZXHandlerAdapter> adapterMap = new HashMap<>();

    private List<ZXViewResolver> resolverList = new ArrayList<>();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        ZXHandleMapping handlerMapping = getHandlerMapping(req);

        if(handlerMapping == null){
            resp.getWriter().write("<font size='25' color='red'>404 Not Found</font><br/><font color='green'><i>Copyright@zhouxin</i></font>");
            return;
        }

        ZXHandlerAdapter zxHandlerAdapter = adapterMap.get(handlerMapping);

        ModelAndView handler = zxHandlerAdapter.handler(req, resp, handlerMapping);

        processDispatchResult(resp, handler);

    }

    private void processDispatchResult(HttpServletResponse resp, ModelAndView handler) throws IOException {
        if(handler == null){
            return;
        }
        if(this.resolverList.isEmpty()){
            return;
        }
        for (ZXViewResolver zxViewResolver : resolverList) {
            if (!zxViewResolver.getPath().equals(handler.getViewName())) {
                continue;
            }
            String out = zxViewResolver.viewResolver(handler);

            resp.getWriter().print(out);
        }
    }

    private ZXHandleMapping getHandlerMapping(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String s = requestURI.replace(contextPath, "").replaceAll("/+", "/");

        for (ZXHandleMapping handleMapping : this.handleMappings) {
            Matcher matcher = handleMapping.getUrl().matcher(s);
            if(matcher.matches()){
                return handleMapping;
            }
        }
        return null;
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        String classLocation = config.getInitParameter("classLocation");
        ZXApplicationContext zxApplicationContext = new ZXApplicationContext(classLocation);
        initStrategies(zxApplicationContext);
    }

    private void initStrategies(ZXApplicationContext context) {
        //GPHandlerMapping 用来保存Controller中配置的RequestMapping和Method的一个对应关系
        initHandlerMappings(context);//通过HandlerMapping，将请求映射到处理器
        //HandlerAdapters 用来动态匹配Method参数，包括类转换，动态赋值
        initHandlerAdapters(context);//通过HandlerAdapter进行多类型的参数动态匹配
        initViewResolvers(context);//通过viewResolver解析逻辑视图到具体视图实现
    }

    private void initViewResolvers(ZXApplicationContext context) {
        String templateRoot = (String) context.getReader().getMap().get("templateRoot");
        String path = this.getClass().getClassLoader().getResource(templateRoot).getPath();
        File file = new File(path);
        if (file.listFiles() == null) {
            return;
        }
        setView(file.listFiles(), templateRoot);
    }

    private void setView(File[] files, String templateRoot) {
        Arrays.stream(files).forEach(e -> {
            if (e.isDirectory()) {
                setView(e.listFiles(), templateRoot);
            } else {
                String name = e.getPath();
                this.resolverList
                        .add(new ZXViewResolver(name
                                .substring(name.lastIndexOf(templateRoot) + templateRoot.length())
                                .replaceAll("\\\\", "/"), e));
            }
        });
    }

    private void initHandlerAdapters(ZXApplicationContext context) {
        handleMappings.forEach(e -> {
            String beanName = SpringUtils.lowerFirstCase(e.getController().getClass().getSimpleName().split("\\$\\$")[0]);
            HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
            try {
                Method method = context
                        .getBeanCacheMap().get(beanName).getClass()
                        .getMethod(e.getMethod().getName(), e.getMethod().getParameterTypes());
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                for (int i = 0; i < parameterAnnotations.length; i++) {
                    for (Annotation annotation : parameterAnnotations[i]) {
                        if (annotation instanceof RequestParam) {
                            var str = ((RequestParam) annotation).value();
                            stringIntegerHashMap.put(str, i);
                        }
                    }
                }
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    if(parameterTypes[i].equals(HttpServletResponse.class) || parameterTypes[i].equals(HttpServletRequest.class)){
                        stringIntegerHashMap.put(parameterTypes[i].getName(),i);
                    }
                }
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            }
            adapterMap.put(e, new ZXHandlerAdapter(stringIntegerHashMap));
        });
    }

    private void initHandlerMappings(ZXApplicationContext context) {
        String[] strings = context.getBeanDefinitionMap().keySet().toArray(String[]::new);
        Arrays.stream(strings).forEach(e -> {
            Object o = context.getBeanCacheMap().get(e);
            Class<?> controllerClass = o.getClass();
            if (!controllerClass.isAnnotationPresent(Controller.class)) {
                return;
            }
            String baseUrl = "";
            if (controllerClass.isAnnotationPresent(RequestMapping.class)) {
                baseUrl = controllerClass.getAnnotation(RequestMapping.class).value();
            }
            Object bean = context.getBean(e);
            Method[] declaredMethods = controllerClass.getDeclaredMethods();
            for (Method e2 : declaredMethods) {
                if (!e2.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                String value = e2.getAnnotation(RequestMapping.class).value();
                String url = baseUrl + value;
                try {
                    this.handleMappings.add(new ZXHandleMapping(bean, bean.getClass().getMethod(e2.getName(), e2.getParameterTypes()), Pattern.compile(url)));
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                }
            }

        });
    }

}
