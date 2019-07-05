package com.example.ribbonconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Author: dyh
 * Date:   2019/7/5
 * Description:
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

//    @HystrixCommand(fallbackMethod = "helloFallback")
//    public String helloService(){
//
//        return restTemplate.getForEntity("http://HELLO-SERVICE/hello",String.class).getBody();
//    }
    @HystrixCommand(fallbackMethod = "fallback")
    public String helloService(){

       throw new RuntimeException("failed");
    }

    public String fallback(Throwable e){
    if("failed".equals(e.getMessage())){
        System.out.println("ansdfadfdfdf");

    }
    assert "failed".equals(e.getMessage());
    return "fail";
    }


    @HystrixCommand(fallbackMethod = "helloFallback2" ,
            ignoreExceptions = ArithmeticException.class)//忽略除0错误
    public String helloFallback(){
        int i=1/0;
        return "error";
    }

    public String helloFallback2(){
        return "error222";
    }

}
