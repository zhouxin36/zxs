package com.zx.microservice.microservice.spel;

import com.zx.microservice.microservice.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/3/7
 */
public class TestMain {

    private static final Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    private static void test1() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");
        String message = exp.getValue(String.class);
        logger.info(message);
    }

    private static void test2() {
        User tesla = new User(1, "Serbian");
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("userName");
        String name = (String) exp.getValue(tesla);
        exp = parser.parseExpression("userName == 'Nikola Tesla'");
        Boolean result = exp.getValue(tesla, Boolean.class);
        logger.info(name);
        logger.info(result + "");
    }

    private static void test3() {
        class Simple {
            public List<Boolean> booleanList = new ArrayList<>();
        }
        Simple simple = new Simple();
        simple.booleanList.add(false);
        simple.booleanList.add(false);
        // forReadOnlyDataBinding 权限
        EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().
                build();
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        spelExpressionParser.parseExpression("booleanList[0]")
                .setValue(context, simple, "true");
        Boolean b1 = simple.booleanList.get(0);
        logger.info(b1 + "");
    }

    private static void test4() {
        class Demo {
            public List<String> list;
        }
        SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode
                .MIXED,TestMain.class.getClassLoader(),true, true,Integer.MAX_VALUE);
        ExpressionParser parser = new SpelExpressionParser(config);
        Expression expression = parser.parseExpression("list[3]");
        Demo demo = new Demo();
        expression.setValue(demo,1);
        Object o = expression.getValue(demo);
        // o 为"" 空串
        logger.info(o+"");
    }

}
