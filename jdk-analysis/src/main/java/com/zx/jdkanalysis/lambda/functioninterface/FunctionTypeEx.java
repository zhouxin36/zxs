package com.zx.jdkanalysis.lambda.functioninterface;

import java.io.EOFException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLTransientException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@FunctionalInterface
interface X {
  void m() throws IOException;
}

@FunctionalInterface
interface Y {
  void m() throws EOFException;
}

@FunctionalInterface
interface Z {
  void m() throws ClassNotFoundException;
}

@FunctionalInterface
interface XY extends X, Y {
}

@FunctionalInterface
interface XYZ extends X, Y, Z {
}

interface A {
  List<String> foo(List<String> arg) throws IOException, SQLTransientException;
}

// XY has function type ()->void throws EOFException
// XYZ has function type ()->void (throws nothing)

interface B {
  List foo(List<String> arg) throws EOFException, SQLException, TimeoutException;
}

interface C {
  List foo(List arg) throws Exception;
}

@FunctionalInterface
interface D extends A, B {
}

//@FunctionalInterface
interface E extends A, B, C {
}

interface G1 {
  <E extends Exception> Object m() throws E;
}

// D has function type (List<String>)->List<String> throws EOFException, SQLTransientException
// E has function type (List)->List throws EOFException, SQLTransientException

interface G2 {
  <F extends Exception> String m() throws Exception;
}

@FunctionalInterface
interface G extends G1, G2 {
}

/**
 * @author zhouxin
 * @date 2019/1/8
 */
public class FunctionTypeEx {
}

// G has function type <F extends Exception> ()->String throws F