package com.zx.microservice.microservice.scope.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zhouxin
 * @since 2019/2/26
 */
@Controller
@RequestMapping("/scopeUser")
// 添加session信息的注解，可以实现 session信息与map的映射 赋值
@SessionAttributes("user")
public class ScopeUserController {
  @Autowired
  private BeanInstance beanInstance1;
  @Autowired
  private BeanInstance beanInstance2;

  public static void main(String[] args) {
    System.out.println(1 + 2 + 3 + 4 + 5 + 6 + 7);
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(String name, Model model, HttpServletRequest request, HttpSession session) {
    model.addAttribute("user", name);
    System.err.println("SessionBean-1");
    beanInstance1.getSessionBean().printId();
    System.err.println("SessionBean-2");
    beanInstance2.getSessionBean().printId();
    System.err.println("RequestBean-1");
    beanInstance1.getRequestBean().printId();
    System.err.println("RequestBean-2");
    beanInstance2.getRequestBean().printId();
    return "user/check";
  }

  /**
   * 检查自动装载的信息
   *
   * @param model
   * @param request
   * @param session
   * @return
   */
  @RequestMapping(value = "/check", method = RequestMethod.GET)
  public String check(Model model, HttpServletRequest request, HttpSession session) {
    System.err.println("SessionBean-1");
    beanInstance1.getSessionBean().printId();
    System.err.println("SessionBean-2");
    beanInstance2.getSessionBean().printId();
    System.err.println("RequestBean-1");
    beanInstance1.getRequestBean().printId();
    System.err.println("RequestBean-2");
    beanInstance2.getRequestBean().printId();
    return "user/check";
  }
}