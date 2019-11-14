package com.zx.microservice.microservice.scope.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author zhouxin
 * @since 2019/2/26
 */
@Service
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BeanInstance {
    @Autowired
    private IRequestBean requestBean;
    @Autowired
    private ISessionBean sessionBean;
    public IRequestBean getRequestBean() {
        return requestBean;
    }
    public ISessionBean getSessionBean() {
        return sessionBean;
    }
}