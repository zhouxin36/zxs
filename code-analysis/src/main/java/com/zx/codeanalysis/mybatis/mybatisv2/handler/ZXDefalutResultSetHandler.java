package com.zx.codeanalysis.mybatis.mybatisv2.handler;

import com.zx.codeanalysis.mybatis.mybatisv2.interfaces.ZXResultSetHandler;
import com.zx.codeanalysis.mybatis.mybatisv2.type.TypeHandlerFactory;
import com.zx.codeanalysis.utils.MybatisUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class ZXDefalutResultSetHandler implements ZXResultSetHandler {
  @Override
  public <T> T handler(ResultSet resultSet, Class<T> returnType) {
    try {
      if (Number.class.isAssignableFrom(returnType)) {
        resultSet.next();
        //noinspection unchecked
        return (T) returnType.getMethod("valueOf", String.class).invoke(null, resultSet.getString(1));
      }
      T t = returnType.getDeclaredConstructor().newInstance();
      setByMethod(resultSet, returnType, t);
//            setParamByField(resultSet, returnType, t);
      return t;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  // 通过获取set方法set值
  private <T> void setByMethod(ResultSet resultSet, Class<T> returnType, T t) throws SQLException {
    Method[] methods = returnType.getMethods();
    resultSet.next();
    Arrays.stream(methods).forEach(e -> {
      if (e.getName().contains("set")) {
        try {
          e.invoke(t
              , TypeHandlerFactory.getTypeHandler(
                  e.getParameterTypes()[0])
                  .getValue(resultSet, MybatisUtils.columnHandler(MybatisUtils.getFieldByMethod(e.getName()))));
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    });
  }

  // 反射通过获取权限set私有域值
  private <T> void setParamByField(ResultSet resultSet, Class<T> returnType, T t) throws SQLException, IllegalAccessException {
    Field[] declaredFields = returnType.getDeclaredFields();
    resultSet.next();
    for (Field field :
        declaredFields) {
      field.setAccessible(true);
      if (field.getType().equals(String.class)) {
        field.set(t, resultSet.getObject(MybatisUtils.columnHandler(field.getName())).toString());
      } else {
        field.set(t, resultSet.getObject(MybatisUtils.columnHandler(field.getName())));
      }
    }
  }
}
