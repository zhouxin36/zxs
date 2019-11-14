package com.zx.codeanalysis.mybatis.mybatisv1;

import com.zx.codeanalysis.utils.MybatisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;

/**
 * @author zhouxin
 * @date 2018/10/22
 */
public class DefaultResultSetHandler implements ResultSetHandler {

    private final static Logger logger = LoggerFactory.getLogger(DefaultResultSetHandler.class);

    public <T> T handler(ResultSet resultSet, Class<T> returnType){
        logger.info("ResultSetHandler执行");
        try(resultSet) {
            //返回基本类型（不支持）
            if(!Object.class.isAssignableFrom(returnType)){
                resultSet.next();
                logger.info(resultSet.getString(1));
                return null;
            }
            //返回基本类型的封装类型
            if (Number.class.isAssignableFrom(returnType)) {
                Method valueOf = returnType.getMethod("valueOf", String.class);
                resultSet.next();
                //noinspection unchecked
                return (T)valueOf.invoke(null,resultSet.getString(1));
            }
            if(String.class.equals(returnType)){
                resultSet.next();
                //noinspection unchecked
                return (T)resultSet.getString(1);
            }
            Constructor<T> constructor = returnType.getConstructor();
            T t = constructor.newInstance();
            if (resultSet.next()) {
                for (Field field : t.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if(String.class.equals(field.getType())){
                        field.set(t,resultSet.getObject(MybatisUtils.columnHandler(field.getName())).toString());
                    }else {
                        field.set(t, resultSet.getObject(MybatisUtils.columnHandler(field.getName())));
                    }
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
