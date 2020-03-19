package com.zhengrz.meetingfilm.film.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import lombok.Data;
import rx.Observable;
import rx.schedulers.Schedulers;

@Data
public class ObserveCommandDemo extends HystrixObservableCommand<String> {

    private String name;

    public ObserveCommandDemo(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ObserveCommandDemo")));
        this.name = name;
    }


    @Override
    protected Observable<String> construct() {

        System.err.println("current Thread: " + Thread.currentThread().getName());

        return Observable.create((Observable.OnSubscribe<String>) subscriber -> {

            subscriber.onNext("action 1, name = " + name);
            subscriber.onNext("action 2, name = " + name);
            subscriber.onNext("action 3, name = " + name);

            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
    }
}
