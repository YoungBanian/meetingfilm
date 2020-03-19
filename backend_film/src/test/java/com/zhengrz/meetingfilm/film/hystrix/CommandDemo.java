package com.zhengrz.meetingfilm.film.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class CommandDemo extends HystrixCommand<String> {

    private String name;

    public CommandDemo(String name){
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorld"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.defaultSetter()
                                        .withRequestCacheEnabled(false)
                                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(3)
                        )
//                .andThreadPoolPropertiesDefaults(
//                        HystrixThreadPoolProperties.defaultSetter()
//                            .withCoreSize(2)
//                            .withMaximumSize(3)
//                            .withAllowMaximumSizeToDivergeFromCoreSize(true)
//                            .withMaxQueueSize(2)
//                )
        );
        this.name = name;
    }


    @Override
    protected String getFallback() {
        return "fallback name = " + name;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(name);
    }

    @Override
    protected String run() throws Exception {

        String result = "CommandHelloWorld name: " + name;

        System.err.println(result + " , currentThread-" + Thread.currentThread().getName());

        return result;
    }
}
