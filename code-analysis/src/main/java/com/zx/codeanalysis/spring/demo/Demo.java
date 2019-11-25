package com.zx.codeanalysis.spring.demo;

import com.zx.codeanalysis.base.User;
import com.zx.codeanalysis.mybatis.mybatisv1.cofig.Comp;
import com.zx.codeanalysis.mybatis.mybatisv2.interfaces.ZXSelect;
import com.zx.codeanalysis.mybatis.mybatisv2.interfaces.ZXUpdate;
import com.zx.codeanalysis.spring.annotation.Mapper;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
@Mapper
public interface Demo {

  @ZXSelect("select * from user where id = ?")
  User select(String userId);

  @ZXSelect("select * from comp where user_id = ?")
  Comp selectTById(String id);

  @ZXSelect("select count(1) from user")
  Integer count();

  @ZXUpdate("update user set age = ? where id = ?")
  Integer updateAge(Integer age, String id);

  @ZXUpdate("update user set user_name = ? where id = ?")
  Integer updateName(String user_name, String id);
}
