package com.example.ribbonconsumer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

/**
 * Author: dyh
 * Date:   2019/7/8
 * Description:
 */
public class UserGetCommand extends HystrixCommand<User> {
    public static final HystrixCommandKey GET_KEY=HystrixCommandKey.Factory.asKey("Commandkey");
    private RestTemplate restTemplate;
    private Long id;

    protected UserGetCommand(RestTemplate restTemplate,Long id,Setter setter) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet")).andCommandKey(GET_KEY));
        this.restTemplate=restTemplate;
        this.id=id;
    }

    @Override
    protected User run() throws Exception {
        User user = restTemplate.getForObject("http://HELLO-SERVICE/users/{1}", User.class, id);
        return user;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }
    public static void flushCache(Long id){
        //刷新缓存
        HystrixRequestCache.getInstance(GET_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
    }
}
