package com.zx.codeanalysis.mybatis.mybatisv1.cofig;

import com.zx.codeanalysis.base.User;
import com.zx.codeanalysis.mybatis.mybatisv1.Select;

/**
 * @author zhouxin
 * @date 2018/10/22
 */
public interface Dao {

  @Select(sql = "select * from user where id = ?")
  User selectById(String id);

  @Select(sql = "select * from comp where user_id = ?")
  Comp selectTById(String id);

  @Select(sql = "select count(1) from user")
  Integer count();
}