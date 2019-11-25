package com.zx.utils;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UtilsApplicationTests {

  private static final Logger logger = LoggerFactory.getLogger(UtilsApplicationTests.class);

  public static void main(String[] args) {

    /**
     * {
     * 	"store": {
     * 		"book": [{
     * 			"category": "reference",
     * 			"author": "NigelRees",
     * 			"title": "SayingsoftheCentury",
     * 			"price": 8.95
     *                },
     *        {
     * 			"category": "fiction",
     * 			"author": "EvelynWaugh",
     * 			"title": "SwordofHonour",
     * 			"price": 12.99
     *        },
     *        {
     * 			"category": "fiction",
     * 			"author": "HermanMelville",
     * 			"title": "MobyDick",
     * 			"isbn": "0-553-21311-3",
     * 			"price": 8.99
     *        },
     *        {
     * 			"category": "fiction",
     * 			"author": "J.R.R.Tolkien",
     * 			"title": "TheLordoftheRings",
     * 			"isbn": "0-395-19395-8",
     * 			"price": 22.99
     *        }],
     * 		"bicycle": {
     * 			"color": "red",
     * 			"price": 19.95
     *        }* 	},
     * 	"expensive": 10
     * }
     */
    String json = "{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"NigelRees\",\"title\":\"SayingsoftheCentury\",\"price\":8.95},{\"category\":\"fiction\",\"author\":\"EvelynWaugh\",\"title\":\"SwordofHonour\",\"price\":12.99},{\"category\":\"fiction\",\"author\":\"HermanMelville\",\"title\":\"MobyDick\",\"isbn\":\"0-553-21311-3\",\"price\":8.99},{\"category\":\"fiction\",\"author\":\"J.R.R.Tolkien\",\"title\":\"TheLordoftheRings\",\"isbn\":\"0-395-19395-8\",\"price\":22.99}],\"bicycle\":{\"color\":\"red\",\"price\":19.95}},\"expensive\":10}";
    logger.info(JsonPath.read(json, "$.store..price").toString());
    Random random = new Random();

  }

  static boolean check(String str) {
    // 一-鿕(中文范围4e00-9fd5)   !-~(字符、数字、英文范围 0021-007e)
    String reg = "[\u0000-\u0020]|[\u007f-\u4dff]|[\u9fd6-\uffff]";
    Pattern pat = Pattern.compile(reg);
    Matcher matcher = pat.matcher(str);
    return !matcher.find();
  }

  @Test
  public void test2() {
    HashMap<Integer, String> map = new HashMap<>();
    for (int i = 0; i < 10000; i += 16) {
      map.put(i, i + "");
      map.put(i + 1, i + 1 + "");
      map.put(i + 2, i + 2 + "");
    }

  }

  @Test
  public void test3() throws InterruptedException {
    logger.info("");
    new Thread(() -> {
      logger.info("");
    }, "thread-1").start();
    new Thread(() -> {
      logger.info("");
    }, "thread-2").start();

  }

  @Test
  public void test4() throws InterruptedException {
    String str1 = "\uD83D\uDE02";
    String str2 = "\u4e2d";
    String str3 = "ヾ";
    String str4 = "◔";
    String str5 = "☺";
    String str6 = "◍";
    System.out.println(Arrays.toString(str1.getBytes()));
    System.out.println("-------------------------------------------------------------------------");
    System.out.println(Arrays.toString(str2.getBytes()));
    System.out.println("-------------------------------------------------------------------------");
    System.out.println(Arrays.toString(str3.getBytes()));
    System.out.println("-------------------------------------------------------------------------");
    System.out.println(Arrays.toString(str4.getBytes()));
    System.out.println("-------------------------------------------------------------------------");
    System.out.println(Arrays.toString(str5.getBytes()));
  }

  @Test
  public void test6() throws Exception {
    User user = new User();
    user.setId(1);
    user.setUserName("zx");
    user.setLocalDateTime(LocalDateTime.now());
//        unsafe.staticFieldOffset(User.class.getDeclaredField("a"))
    Integer integer = 1;
    int inte = 2;
    Class<?> clazz = Unsafe.class;
    Field theUnsafe = clazz.getDeclaredField("theUnsafe");
    theUnsafe.setAccessible(true);
    Unsafe unsafe = (Unsafe) theUnsafe.get(null);
    logger.info(user + "");
    User user1;
//        System.out.println(user1);
  }
}
