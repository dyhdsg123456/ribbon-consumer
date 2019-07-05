package com.example.ribbonconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


@RestController
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
   @Autowired
   private HelloService service;
    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
//        return restTemplate.getForEntity("http://HELLO-SERVICE/hello3",String.class).getBody();
        return service.helloService();
    }

    @RequestMapping(value = "/getuser",method = RequestMethod.GET)
    @ResponseBody
    public User getuser(){
        HashMap<String, String> param = new HashMap<>();
        param.put("name","dyh4");
//        return restTemplate.getForEntity("http://HELLO-SERVICE/hello4?name={name}",User.class,param).getBody();
        User user = restTemplate.getForObject("http://HELLO-SERVICE/hello4?name={name}", User.class, param);
        return user;
    }
}
