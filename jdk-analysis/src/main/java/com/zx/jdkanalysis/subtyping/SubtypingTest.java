package com.zx.jdkanalysis.subtyping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 父子类型
 *
 * @author zhouxin
 * @date 2019/1/17
 */
public class SubtypingTest {

    private static final Logger logger = LoggerFactory.getLogger(SubtypingTest.class);

    public static void main(String[] args)  {
        arraySub();
        logger.info("----------------------------------------这是一条华丽的分割线-------------------------------------------------");
        primitiveSub();

    }

    private static void primitiveSub() {
        logger.info("------------->{}",Integer.TYPE.isAssignableFrom(int.class));
        logger.info("------------->{}",int.class.isPrimitive());
    }

    @SuppressWarnings("ConstantConditions")
    private static void arraySub(){
        logger.info("Object > Object[] ------{}",Object.class.isAssignableFrom(Object[].class));
        logger.info("Object > Object[] ------{}",new Object[]{} instanceof Object);

        logger.info("Cloneable > Object[] ------{}",Cloneable.class.isAssignableFrom(Object[].class));
        logger.info("Cloneable > Object[] ------{}",new Object[]{} instanceof Cloneable);

        logger.info("java.io.Serializable > Object[] ------{}",java.io.Serializable.class.isAssignableFrom(Object[].class));
        logger.info("java.io.Serializable > Object[] ------{}",new Object[]{} instanceof java.io.Serializable);

        logger.info("Object > Integer[] ------{}",Object.class.isAssignableFrom(Integer[].class));
        logger.info("Object > Integer[] ------{}",new Integer[]{} instanceof Object);

        logger.info("Cloneable > Integer[] ------{}",Cloneable.class.isAssignableFrom(Integer[].class));
        logger.info("Cloneable > Integer[] ------{}",new Integer[]{} instanceof Cloneable);

        logger.info("Object[] > Integer[] ------{}",Object[].class.isAssignableFrom(Integer[].class));
        logger.info("Object[] > Integer[] ------{}",new Integer[]{} instanceof Object[]);


        logger.info("Number[] > Integer[] ------{}",Number[].class.isAssignableFrom(Integer[].class));
        logger.info("Number[] > Integer[] ------{}",new Integer[]{} instanceof Number[]);

        double[] doubles = {'0'};
        logger.info("--------------->doubles class:{}",doubles.getClass());
        logger.info("Object > primitive type ------{}",Object.class.isAssignableFrom(double[].class));
        logger.info("Object > primitive type ------{}",doubles instanceof Object); //编译错误

        logger.info("Object[] > primitive type ------{}",Object[].class.isAssignableFrom(double[].class));

        logger.info("Cloneable > primitive type ------{}",Cloneable.class.isAssignableFrom(double[].class));
        logger.info("Cloneable > primitive type ------{}",doubles instanceof Cloneable);

        logger.info("java.io.Serializable > primitive type ------{}",java.io.Serializable.class.isAssignableFrom(double[].class));
        logger.info("java.io.Serializable > primitive type ------{}",doubles instanceof java.io.Serializable);
    }
}