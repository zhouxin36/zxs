package com.zx.codeanalysis.spring.demo;

import com.zx.codeanalysis.spring.annotation.Autowried;
import com.zx.codeanalysis.spring.annotation.Service;
import com.zx.codeanalysis.spring.annotation.Transactional;
import com.zx.codeanalysis.spring.demo.interfaces.BaseService;

/**
 * @author zhouxin
 * @date 2018/10/25
 */

@Service
public class Demo1Service implements BaseService {

    @Autowried
    private Demo demo;

    @Transactional
    public String getName(){
        demo.select("dc1dc52e-79b9-11e8-9c15-fdc7eb479f4a");
        demo.updateAge(222,"dc1dc52e-79b9-11e8-9c15-fdc7eb479f4a");
        demo.updateName("bbb","dc1dc52e-79b9-11e8-9c15-fdc7eb479f4a");
//        throw new RuntimeException("");
        return "周鑫";
    }
}
