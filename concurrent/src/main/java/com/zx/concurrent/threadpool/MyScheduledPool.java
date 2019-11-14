package com.zx.concurrent.threadpool;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouxin
 * @since 2019/5/15
 */
public class MyScheduledPool {

  private MyScheduledBlockingQueue blockingQueue;

  private List<Thread> threads = new ArrayList<>();

  private int maxCount;

  private long period;

  private TimeUnit timeUnit;

  private AtomicInteger atomicInteger = new AtomicInteger(0);

  public MyScheduledPool(int maxCount, long period, TimeUnit timeUnit) {
    this.maxCount = maxCount;
    this.blockingQueue = new MyScheduledBlockingQueue();
    this.timeUnit = timeUnit;
    this.period = period;
  }

  public FutureTask execute(Runnable runnable) {
    ScheduledFutureTask scheduledFutureTask = new ScheduledFutureTask(runnable, period, timeUnit,
        blockingQueue);
    while (maxCount > threads.size()) {
      Thread thread = new Thread(() -> {
        Runnable first;
        while ((first = getTask()) != null) {
          try {
            first.run();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }, "Thread-" + atomicInteger.incrementAndGet());
      thread.start();
      threads.add(thread);
    }
    blockingQueue.put(scheduledFutureTask);
    return scheduledFutureTask;
  }

  public Runnable getTask() {
    try {
      return blockingQueue.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

  static class MyScheduledBlockingQueue extends AbstractQueue<ScheduledFutureTask>
      implements BlockingQueue<ScheduledFutureTask> {

    private List<ScheduledFutureTask> runnables = new ArrayList<>();

    private int size = 0;

    @Override
    public Iterator<ScheduledFutureTask> iterator() {
      return null;
    }

    @Override
    public int size() {
      return size;
    }

    @Override
    public synchronized void put(ScheduledFutureTask runnable) {
      runnables.add(runnable);
      size++;
      this.notify();
    }

    @Override
    public boolean offer(ScheduledFutureTask runnable, long timeout, TimeUnit unit)
        throws InterruptedException {
      return false;
    }

    @Override
    public synchronized ScheduledFutureTask take() throws InterruptedException {
      for (; ; ) {
        if (runnables.isEmpty()) {
          this.wait();
        } else {
          ScheduledFutureTask takeValue = runnables.get(size - 1);
          long delay = takeValue.getDelay(MILLISECONDS);
          if (delay <= 0) {
            runnables.remove(size - 1);
            size--;
            return takeValue;
          } else {
            this.wait(delay);
          }
        }
      }
    }

    @Override
    public ScheduledFutureTask poll(long timeout, TimeUnit unit) throws InterruptedException {
      return null;
    }

    @Override
    public int remainingCapacity() {
      return 0;
    }

    @Override
    public int drainTo(Collection<? super ScheduledFutureTask> c) {
      return 0;
    }

    @Override
    public int drainTo(Collection<? super ScheduledFutureTask> c, int maxElements) {
      return 0;
    }

    @Override
    public boolean offer(ScheduledFutureTask runnable) {
      return false;
    }

    @Override
    public synchronized ScheduledFutureTask poll() {
      return null;
    }

    @Override
    public ScheduledFutureTask peek() {
      return null;
    }
  }

  private class ScheduledFutureTask extends FutureTask<Void> implements
      RunnableScheduledFuture<Void> {

    private long nextTime;

    private long period;

    private MyScheduledBlockingQueue myScheduledBlockingQueue;

    private Runnable runnable;

    public ScheduledFutureTask(Runnable runnable, long period, TimeUnit timeUnit,
        MyScheduledBlockingQueue myScheduledBlockingQueue) {
      super(runnable, null);
      this.myScheduledBlockingQueue = myScheduledBlockingQueue;
      this.period = timeUnit.toNanos(period);
      this.nextTime = System.nanoTime();
      this.runnable = runnable;
    }

    @Override
    public boolean isPeriodic() {
      return false;
    }

    @Override
    public Void get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException {
      this.wait(unit.toMillis(timeout));
      return super.get(timeout, unit);
    }

    @Override
    public long getDelay(TimeUnit unit) {
      return unit.convert(this.nextTime - System.nanoTime(), NANOSECONDS);
    }

    private void setNextRunTime() {
      this.nextTime += this.period;
    }

    @Override
    public void run() {
      runnable.run();
      setNextRunTime();
      myScheduledBlockingQueue.put(this);
    }

    @Override
    public int compareTo(Delayed o) {
      return 0;
    }
  }
}
