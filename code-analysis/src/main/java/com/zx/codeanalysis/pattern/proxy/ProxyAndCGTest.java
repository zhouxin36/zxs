package com.zx.codeanalysis.pattern.proxy;

import com.zx.codeanalysis.pattern.proxy.cglib.CglibProxy;
import com.zx.codeanalysis.pattern.proxy.cglib.CglibProxy2;
import com.zx.codeanalysis.pattern.proxy.cglib.PersonImp;
import com.zx.codeanalysis.pattern.proxy.dynamic.Person;
import com.zx.codeanalysis.pattern.proxy.dynamic.PersonImpl;
import com.zx.codeanalysis.pattern.proxy.dynamic.StuInvocationHandler;
import com.zx.codeanalysis.pattern.proxy.proxy.ProxyGenerator;
import net.sf.cglib.core.DebuggingClassWriter;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * @author zhouxin
 * @date 2019/1/3
 */
public class ProxyAndCGTest {
  public static void main(String[] args) {
    String path = "D://code";
    // CG
    System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, path);
    PersonImp CGpersonImp = (PersonImp) new CglibProxy().getInstance(PersonImp.class);
    Person CGperson = (Person) new CglibProxy().getInstance(Person.class);
    Person instance = (Person) new CglibProxy2().getInstance(new PersonImpl());
    instance.giveMoney();
    // JDK
    Person stuProxy = (Person) Proxy
        .newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, new StuInvocationHandler<>(Person.class));
    byte[] bytes1 = ProxyGenerator.generateProxyClass("$Proxy0", stuProxy.getClass().getInterfaces());
    try (FileOutputStream fileOutputStream = new FileOutputStream(path + "/PersonProxy.class")) {
      fileOutputStream.write(bytes1);
      fileOutputStream.flush();
    } catch (Exception e) {

    }
    Proxy.newProxyInstance(PersonImp.class.getClassLoader(), new Class<?>[]{}, new StuInvocationHandler<>(PersonImp.class));
    byte[] bytes2 = ProxyGenerator.generateProxyClass("$Proxy1", new Class<?>[]{});
    try (FileOutputStream fileOutputStream = new FileOutputStream(path + "/PersonImpIProxy.class")) {
      fileOutputStream.write(bytes2);
      fileOutputStream.flush();
    } catch (Exception e) {

    }
  }

}
