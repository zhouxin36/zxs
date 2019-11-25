package com.zx.microservice.microservice.spel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author zhouxin
 * @since 2019/3/7
 */
public class LangTest {

  private static final Logger logger = LoggerFactory.getLogger(LangTest.class);

  public static void main(String[] args) throws NoSuchMethodException {
//        test1();
    test2();
//        test3();
//        test4();
//        test6();
  }

  /**
   * Literal Expressions  文字表达式
   */
  private static void test1() {
    ExpressionParser parser = new SpelExpressionParser();
    String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();
    Double avogadrosNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();
    Integer maxValue = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();
    Boolean trueValue = (Boolean) parser.parseExpression("true").getValue();
    Object nullValue = parser.parseExpression("null").getValue();
  }

  /**
   * Properties, Arrays, Lists, Maps, and Indexers
   */
  private static void test2() {
    Inventor inventor = new Inventor();
    inventor.setBirthdate(new Date());
    inventor.setPlaceOfBirth(new PlaceOfBirth("city"));
    Inventor inventor2 = new Inventor();
    inventor2.setInventions(new String[]{"hehe", "haha"});
    SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode
        .IMMEDIATE, TestMain.class.getClassLoader(), true, true, Integer.MAX_VALUE);
    ExpressionParser parser = new SpelExpressionParser(config);
    //  getValue(context,inventor2,String.class) 优先 withRootObject(inventor)
    EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().withRootObject(inventor).
        build();
    Integer year = parser.parseExpression("Birthdate.Year + 1900").getValue(context, Integer.class);
    String city = parser.parseExpression("placeOfBirth.City").getValue(context, String.class);
    // 空指针安全  需要权限forReadOnlyDataBinding()
    String city1 = parser.parseExpression("placeOfBirth?.City").getValue(context, inventor2, String.class);
    String invention = parser.parseExpression("inventions[1]").getValue(context, inventor2, String.class);

//        parser.parseExpression("Officers['advisors'][0].PlaceOfBirth.Country").setValue(new Society(), "Croatia");

    // 开启写权限forReadWriteDataBinding()
    String aleks = parser.parseExpression("Name = 'Aleksandar Seovic'").getValue(context, inventor, String.class);
    logger.info(year + "");
    logger.info(city);
    logger.info(invention);
    logger.info(aleks);
  }

  /**
   * Inline Lists , Maps
   */
  private static void test3() {
    ExpressionParser parser = new SpelExpressionParser();
    EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().
        build();
    //list
    List numbers = (List) parser.parseExpression("{1,2,3,4}").getValue(context);
    List listOfLists = (List) parser.parseExpression("{{'a','b'},{'x','y'}}").
        getValue(context);

    //map
    Map inventorInfo = (Map) parser.parseExpression("{name:'Nikola',dob:'10-July-1856'}").getValue(context);
    Map mapOfMaps = (Map) parser.parseExpression("{name:{first:'Nikola',last:'Tesla'},dob:{day:10,month:'July',year:1856}}")
        .getValue(context);

//      array
    int[] numbers1 = (int[]) parser.parseExpression("new int[4]").getValue(context);
    int[] numbers2 = (int[]) parser.parseExpression("new int[]{1,2,3}").getValue
        (context);
    int[][] numbers3 = (int[][]) parser.parseExpression("new int[4][5]").getValue
        (context);

    String bc = parser.parseExpression("'abc'.substring(1, 3)").getValue(String.class);
    logger.info(numbers.toString());
    logger.info(listOfLists.toString());
    logger.info(inventorInfo.toString());
    logger.info(mapOfMaps.toString());
    logger.info(numbers1.toString());
    logger.info(numbers2.toString());
    logger.info(numbers3.toString());
    logger.info(bc);
  }


  /**
   * Operator
   */
  private static void test4() {
    ExpressionParser parser = new SpelExpressionParser();
    Boolean trueValue = parser.parseExpression("2 == 2").getValue(Boolean.class);
    Boolean falseValue = parser.parseExpression("2 < -5.0").getValue(Boolean.class);
    Boolean trueValue2 = parser.parseExpression("'black' < 'block'").getValue(Boolean.class);

    Boolean falseValue1 = parser.parseExpression("'xyz' instanceof T(Integer)").getValue(Boolean.class);
    // 自动装箱
    Boolean falseValue2 = parser.parseExpression("1 instanceof T(int) ").getValue(Boolean.class);
    Boolean trueValue3 = parser.parseExpression(" 1 instanceof T(Integer) ").getValue(Boolean.class);
    Boolean trueValue1 = parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);

    Boolean falseValue3 = parser.parseExpression("true and false").getValue(Boolean.class);
    Boolean falseValue4 = parser.parseExpression("true && true").getValue(Boolean.class);

    Integer minusTwentyOne = parser.parseExpression("1+2-3*8").getValue(Integer.class);
  }

  private static void test5() {
    ExpressionParser parser = new SpelExpressionParser();
    //  class type
    Boolean trueValue = parser.parseExpression("T(java.math.RoundingMode).CEILING < T(java.math.RoundingMode).FLOOR")
        .getValue(Boolean.class);
    //  Constructors
    Inventor einstein = parser.parseExpression("new org.spring.samples.spel.inventor.Inventor('Albert Einstein','German')")
        .getValue(Inventor.class);

    //  Variables
    Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
    EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding()
        .build();
    context.setVariable("newName", "Mike Tesla");
    parser.parseExpression("Name = #newName").getValue(context, tesla);
    System.out.println(tesla.getName());
  }

  private static void test6() throws NoSuchMethodException {
    // create an array of integers
    List<Integer> primes = new ArrayList<>();
    primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));
// create parser and set variable 'primes' as the array of integers
    ExpressionParser parser = new SpelExpressionParser();
    EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
    context.setVariable("primes", primes);
// all prime numbers > 10 from the list (using selection ?{...})
// evaluates to [11, 13, 17]
    List<Integer> primesGreaterThanTen = (List<Integer>) parser.parseExpression("#primes.?[#this>10]").getValue(context);

    // 反射调用
    context.setVariable("reverseString", StringUtils.class.getDeclaredMethod("trimAllWhitespace", String.class));
    String helloWorldReversed = parser.parseExpression("#reverseString('he   llo')").getValue(context, String.class);
  }

  // bean
  private static void test7() {
    ExpressionParser parser = new SpelExpressionParser();
    StandardEvaluationContext context = new StandardEvaluationContext();
    context.setBeanResolver(new BeanFactoryResolver(null));
    Object bean = parser.parseExpression("@something").getValue(context);
    Object FactoryBean = parser.parseExpression("&something").getValue(context);
  }
}
