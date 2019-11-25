package com.zx.reactive.x;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouxin
 * @since 2019/3/13
 */
public class Test1 {

  private static final Logger logger = LoggerFactory.getLogger(Test1.class);

  public static void main(String[] args) throws Exception {
//        test1();
//        test2();
//        test3();//flatMap  concat
//        test4();
    test5();
    TimeUnit.SECONDS.sleep(2);
  }

  private static void test5() {

    AsyncSubject.range(1, 1).observeOn(Schedulers.io()).doOnNext(e -> {
      logger.info("----------------------1----------------------------");
    }).doOnNext(e -> {
      logger.info("----------------------2----------------------------");
    }).doOnNext(e -> {
      logger.info("----------------------3----------------------------");
    }).doOnNext(e -> {
      logger.info("----------------------4----------------------------");
    }).doOnNext(e -> {
      logger.info("----------------------5----------------------------");
    }).doOnNext(e -> {
      logger.info("----------------------6----------------------------");
    }).doOnNext(e -> {
      logger.info("----------------------7----------------------------");
    }).doOnNext(e -> {
      logger.info("----------------------8----------------------------");
      throw new RuntimeException("你错啦啦啦啦");
    }).doOnNext(e -> {
      logger.info("----------------------9----------------------------");
    }).doOnNext(e -> {
      logger.info("----------------------0----------------------------");
    }).onErrorReturn(e -> {
      logger.info("出错了");
      return e.getMessage().length();
    }).subscribe();
  }

  private static void test4() {
    Observable<Integer> integerObservable1 = Observable.create((ObservableOnSubscribe<Integer>) e -> {
      logger.info("Observable thread is : " + Thread.currentThread().getName());
      e.onNext(1);
      e.onNext(11);
      e.onNext(111);
      e.onNext(1111);
//            e.onComplete();
    }).subscribeOn(Schedulers.newThread())
        .subscribeOn(Schedulers.io());

    Observable<Integer> integerObservable2 = Observable.create((ObservableOnSubscribe<Integer>) e -> {
      logger.info("Observable thread is : " + Thread.currentThread().getName());
      e.onNext(2);
      e.onNext(22);
      e.onNext(222);
      e.onNext(2222);
//            e.onComplete();
    }).subscribeOn(Schedulers.newThread())
        .subscribeOn(Schedulers.io());

    Observable<Integer> integerObservable3 = Observable.create((ObservableOnSubscribe<Integer>) e -> {
      logger.info("Observable thread is : " + Thread.currentThread().getName());
      e.onNext(3);
      e.onNext(33);
      e.onNext(333);
      e.onNext(3333);
//            e.onComplete();
    }).subscribeOn(Schedulers.io());
    Observable.zip(integerObservable1, integerObservable2, integerObservable3, (e1, e2, e3) -> (e1 + e2 + e3) + "")
        .observeOn(Schedulers.io())
        .subscribe(logger::info);
  }

  private static void test3() {

    Observable<Integer> integerObservable1 = Observable.create((ObservableOnSubscribe<Integer>) e -> {
      logger.info("Observable thread is : " + Thread.currentThread().getName());
      e.onNext(1);
      e.onComplete();
    }).subscribeOn(Schedulers.newThread())
        .subscribeOn(Schedulers.io());

    Observable<Integer> integerObservable2 = Observable.create((ObservableOnSubscribe<Integer>) e -> {
      logger.info("Observable thread is : " + Thread.currentThread().getName());
      e.onNext(2);
      throw new RuntimeException("2错误");
//            e.onComplete();
    }).subscribeOn(Schedulers.newThread())
        .subscribeOn(Schedulers.io());

    Observable<Integer> integerObservable3 = Observable.create((ObservableOnSubscribe<Integer>) e -> {
      logger.info("Observable thread is : " + Thread.currentThread().getName());
      e.onNext(3);
      e.onComplete();
    }).subscribeOn(Schedulers.io());
    Observable.concat(integerObservable1, integerObservable2, integerObservable3)
        .observeOn(Schedulers.single())
        .doOnNext(integer -> logger.info("{} After observeOn(single)，Current thread is {}", integer, Thread.currentThread().getName()))
        .observeOn(Schedulers.trampoline())//继承上一个
        .doOnNext(integer -> logger.info("{} After observeOn(trampoline)，Current thread is {}", integer, Thread.currentThread().getName()))
        .observeOn(Schedulers.io())
        .map(e -> ++e)
        .flatMap(e -> a -> {
          a.onNext(e + 10);//继续发射数据
          a.onNext(e + 20);
          a.onComplete();
        })
//                .onErrorReturn(Throwable::getMessage)
        .onErrorResumeNext(e -> {
          e.onNext("呵呵哒");
        })
        .doOnNext(integer -> logger.info("{} After observeOn(io)，Current thread is {}", integer, Thread.currentThread().getName()))
        .observeOn(Schedulers.computation())//代表CPU计算密集型的操作, 例如需要大量计算的操作
        .subscribe(integer -> logger.info("{} After observeOn(computation)，Current thread is {}", integer, Thread.currentThread().getName()));

  }

  /**
   * 简单地说，subscribeOn() 指定的就是发射事件的线程，observerOn 指定的就是订阅者接收事件的线程。
   * 多次指定发射事件的线程只有第一次指定的有效，也就是说多次调用 subscribeOn() 只有第一次的有效，其余的会被忽略。
   * 但多次指定订阅者接收线程是可以的，也就是说每调用一次 observerOn()，下游的线程就会切换一次。
   */
  private static void test2() {
    Disposable subscribe = Observable.create((ObservableOnSubscribe<Integer>) e -> {
      logger.info("Observable thread is : " + Thread.currentThread().getName());
      e.onNext(1);
      e.onComplete();
    }).subscribeOn(Schedulers.newThread())
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.single())
        .doOnNext(integer -> logger.info("{} After observeOn(single)，Current thread is {}", integer, Thread.currentThread().getName()))
        .observeOn(Schedulers.trampoline())//继承上一个
        .doOnNext(integer -> logger.info("{} After observeOn(trampoline)，Current thread is {}", integer, Thread.currentThread().getName()))
        .observeOn(Schedulers.io())
        .map(e -> ++e)
        .doOnNext(integer -> logger.info("{} After observeOn(io)，Current thread is {}", integer, Thread.currentThread().getName()))
        .observeOn(Schedulers.computation())//代表CPU计算密集型的操作, 例如需要大量计算的操作
        .subscribe(integer -> logger.info("{} After observeOn(computation)，Current thread is {}", integer, Thread.currentThread().getName()));


  }

  private static void test1() {
    Observable.create((ObservableOnSubscribe<String>) e -> {
      logger.info("Observable emit 1" + "\n");
      e.onNext("1");
      logger.info("Observable emit 2" + "\n");
      e.onNext("2");
      logger.info("Observable emit 3" + "\n");
      e.onNext("3");
      e.onComplete();
      logger.info("Observable emit 4" + "\n");
      e.onNext("4");
    }).subscribe(new Observer<String>() {

      // 第二步：初始化Observer
      private int i;
      private Disposable mDisposable;

      @Override
      public void onSubscribe(Disposable d) {
        logger.info("Disposable:{}", d);
        this.mDisposable = d;
      }

      @Override
      public void onNext(String s) {
        logger.info("next:{}", s);
        i++;
        if (i == 2) {
          // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
          mDisposable.dispose();
        }
      }

      @Override
      public void onError(Throwable e) {
        logger.info("error:{}", e.getMessage());
      }

      @Override
      public void onComplete() {
        logger.info("onComplete");
      }
    });
  }

}
