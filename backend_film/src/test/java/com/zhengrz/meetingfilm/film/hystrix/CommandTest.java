package com.zhengrz.meetingfilm.film.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.jupiter.api.Test;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class CommandTest {

    @Test
    public void testCommandExecute() throws Exception {

        long beginTime = System.currentTimeMillis();

        CommandDemo commandDemo = new CommandDemo("execute");

        String result = commandDemo.execute();

        System.out.println("result: " + result + ", speed: " + (System.currentTimeMillis() - beginTime));
    }

    @Test
    public void testCommandQueue() throws ExecutionException, InterruptedException {

        long beginTime = System.currentTimeMillis();

        CommandDemo commandDemo = new CommandDemo("queue");

        Future<String> future = commandDemo.queue();

        System.out.println("result: end" + ", speed: " + (System.currentTimeMillis() - beginTime));

        System.out.println("result: " + future.get() + ", speed: " + (System.currentTimeMillis() - beginTime));

    }

    @Test
    public void testObserve() {

        long beginTime = System.currentTimeMillis();

        CommandDemo commandDemo = new CommandDemo("observe");

        Observable<String> observe = commandDemo.observe();

        // 阻塞式调用
        String result = observe.toBlocking().single();

        System.out.println("result = " + result + ", speeding = " + (System.currentTimeMillis() - beginTime));

        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.err.println("observe , onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("observe , onError, throwable = " + throwable);
            }

            @Override
            public void onNext(String s) {
                System.err.println("observe , onNext result = " + result + " speed = " + (System.currentTimeMillis() - beginTime));
            }
        });
    }


    @Test
    public void testToObserve() throws InterruptedException {

        long beginTime = System.currentTimeMillis();

        CommandDemo c1 = new CommandDemo("toObserve1");

        Observable<String> o1 = c1.toObservable();

        String result = o1.toBlocking().single();

        System.out.println("result = " + result + " , speed = " + (System.currentTimeMillis() - beginTime));

        CommandDemo c2 = new CommandDemo("toObserve2");

        Observable<String> o2 = c2.toObservable();

        o2.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("toObservable , onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("toObservable , onError - throwable = " + throwable);
            }

            @Override
            public void onNext(String s) {
                long endTime = System.currentTimeMillis();
                System.out.println("toObservable , onNext result = " + result + " , speed = " + (System.currentTimeMillis() - beginTime));
            }
        });

        Thread.sleep(20001);

    }


    @Test
    public void requestCache() {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        long beginTime = System.currentTimeMillis();

        CommandDemo c1 = new CommandDemo("c1");
        String r1 = c1.execute();
        System.out.println("result = " + r1 + ", speed = " + (System.currentTimeMillis() - beginTime));

        CommandDemo c2 = new CommandDemo("c2");
        String r2 = c2.execute();
        System.out.println("result = " + r2 + ", speed = " + (System.currentTimeMillis() - beginTime));

        CommandDemo c3 = new CommandDemo("c1");
        String r3 = c3.execute();
        System.out.println("result = " + r3 + ", speed = " + (System.currentTimeMillis() - beginTime));

        context.close();
    }

    @Test
    public void threadTest() throws ExecutionException, InterruptedException {

        CommandDemo c1 = new CommandDemo("c1");
        CommandDemo c2 = new CommandDemo("c2");
        CommandDemo c3 = new CommandDemo("c3");
        CommandDemo c4 = new CommandDemo("c4");
        CommandDemo c5 = new CommandDemo("c5");

        Future<String> q1 = c1.queue();
        Future<String> q2 = c2.queue();
        Future<String> q3 = c3.queue();
        Future<String> q4 = c4.queue();
        Future<String> q5 = c5.queue();

        String r1 = q1.get();
        String r2 = q2.get();
        String r3 = q3.get();
        String r4 = q4.get();
        String r5 = q5.get();

        System.out.println("r1 = " + r1 + ", r2 = " + r2 + " , r3 = " + r3 + " , r4 = " + r4 + " , r5 = " + r5);
    }

    @Test
    public void SemTest() throws InterruptedException {
        MyThread t1 = new MyThread("t1");
        MyThread t2 = new MyThread("t2");
        MyThread t3 = new MyThread("t3");
        MyThread t4 = new MyThread("t4");
        MyThread t5 = new MyThread("t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        Thread.sleep(10000);
    }

}

class MyThread extends Thread {

    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        CommandDemo c = new CommandDemo(name);
        System.out.println("result = " + c.execute());
    }

}
