package com.zx.jdkanalysis.lambda.functioninterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author zhouxin
 * @date 2019/1/8
 */

interface Action<T> {
}

@FunctionalInterface
interface Runnable {
  void run();
}


//@FunctionalInterface
interface Foo1 {
  boolean equals(Object obj);
}
// Functional


@FunctionalInterface
interface Bar1 extends Foo1 {
  int compare(String o1, String o2);
}
// Not functional; equals is already an implicit member


@FunctionalInterface
interface Comparator<T> {
  boolean equals(Object obj);

  int compare(T o1, T o2);
}
// Functional; Bar has one abstract non-Object method


//@FunctionalInterface
interface Foo2 {
  int m();

  Object clone();
}
// Functional; Comparator has one abstract non-Object method


@FunctionalInterface
interface X1 {
  int m(Iterable<String> arg);
}
// Not functional; method Object.clone is not public


@FunctionalInterface
interface Y1 {
  int m(Iterable<String> arg);
}

@FunctionalInterface
interface Z1 extends X1, Y1 {
}

@FunctionalInterface
interface X2 {
  Iterable m(Iterable<String> arg);
}
// Functional: two methods, but they have the same signature


@FunctionalInterface
interface Y2 {
  Iterable<String> m(Iterable arg);
}

@FunctionalInterface
interface Z2 extends X2, Y2 {
}

@FunctionalInterface
interface X3 {
  int m(Iterable<String> arg);
}
// Functional: Y.m is a subsignature & return-type-substitutable


@FunctionalInterface
interface Y3 {
  int m(Iterable<Integer> arg);
}

interface X4 {
  int m(Iterable<String> arg, Class c);
}
//interface Z3 extends X3, Y3 {}
// Compiler error: No method has a subsignature of all abstract methods

interface Y4 {
  int m(Iterable arg, Class<?> c);
}

interface X5 {
  long m();
}
//interface Z4 extends X4, Y4 {}
// Compiler error: No method has a subsignature of all abstract methods

interface Y5 {
  int m();
}

interface Foo3<T> {
  void m(T arg);
}
//interface Z5 extends X5, Y5 {}
// Compiler error: no method is return type substitutable

interface Bar2<T> {
  void m(T arg);
}

//interface FooBar<X, Y> extends Foo3<X>, Bar2<Y> {}
// Compiler error: different signatures, same erasure
interface Foo4<T, N extends Number> {
  void m(T arg);

  void m(N arg);
}

interface Bar3 extends Foo4<String, Integer> {
}

@FunctionalInterface
interface Baz extends Foo4<Integer, Integer> {
}

// Foo is _not_ functional: different signatures for m
// Bar is _not_ functional: different signatures for m
// Baz is functional: same signature for m
@FunctionalInterface
interface Executor {
  <T> T execute(Action<T> a);
}

// Functional
@FunctionalInterface
interface X6 {
  <T> T execute(Action<T> a);
}

@FunctionalInterface
interface Y6 {
  <S> S execute(Action<S> a);
}

interface X7 {
  <T> T execute(Action<T> a);
}
//interface Exec extends X6, Y6 {}
// Functional: signatures are "the same"

interface Y7 {
  <S, T> S execute(Action<S> a);
}

public class FunctionInterfaceEx {

  private static final Logger logger = LoggerFactory.getLogger(FunctionInterfaceEx.class);

  public static void main(String[] args) {
    Arrays.stream(X2.class.getMethods()).forEach(FunctionInterfaceEx::toMethodSignature
    );
    Arrays.stream(Y2.class.getMethods()).forEach(FunctionInterfaceEx::toMethodSignature
    );
    Arrays.stream(Z2.class.getMethods()).forEach(FunctionInterfaceEx::toMethodSignature
    );
  }

  private static void toMethodSignature(Method e) {
    try {
      Method method = Method.class.getDeclaredMethod("getGenericSignature");
      method.setAccessible(true);
      logger.info("----------class:{}----------method:{}---------method signature:{}", e.getDeclaringClass().getSimpleName(), e.getName(), method.invoke(e));
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }


}
//interface Exec extends X7, Y7 {}
// Compiler error: different signatures, same erasure