package com.example.ribbonconsumer;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

/**
 * Author: dyh
 * Date:   2019/7/8
 * Description:
 */
public class UserPostCommand extends HystrixCommand<User>{

    private             RestTemplate      restTemplate;
    private             User              user;

    public UserPostCommand(RestTemplate restTemplate, User user) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet")));
        this.restTemplate=restTemplate;
        this.user=user;
    }

    @Override
    protected User run() throws Exception {
        User user1 = new User();
        user1=user;
        User user = restTemplate.postForObject("http://HELLO-SERVICE/users", user1, User.class);
        UserGetCommand.flushCache(user.getId());
        return user;
    }


}
