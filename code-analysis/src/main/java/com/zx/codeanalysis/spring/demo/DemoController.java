package com.zx.codeanalysis.spring.demo;

import com.zx.codeanalysis.spring.annotation.Autowried;
import com.zx.codeanalysis.spring.annotation.Controller;
import com.zx.codeanalysis.spring.annotation.RequestMapping;
import com.zx.codeanalysis.spring.annotation.RequestParam;
import com.zx.codeanalysis.spring.demo.interfaces.BaseService;
import com.zx.codeanalysis.spring.springv1.springmvcv1.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @date 2018/10/25
 */
@Controller
public class DemoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

  @Autowried
  private BaseService demo1Service;

  @RequestMapping("/index")
  public ModelAndView index(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id) {
    String name = demo1Service.getName();
    LOGGER.info("----------->" + id + name);
    return out(response, id + name);

  }

  private ModelAndView out(HttpServletResponse resp, String str) {
    try {
      resp.getWriter().write(str);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @RequestMapping("/index.html")
  public ModelAndView query(@RequestParam("teacher") String teacher) {
    Map<String, Object> model = new HashMap<>();
    model.put("userName", teacher);
    return new ModelAndView("/index.html", model);
  }

}
