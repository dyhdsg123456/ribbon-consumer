package com.example.ribbonconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Author: dyh
 * Date:   2019/7/10
 * Description:
 */
@Service
public class BookService {
    @Autowired
    RestTemplate restTemplate;
    public Book test8(Long id) {
        return restTemplate.getForObject("http://HELLO-SERVICE/getbook6/{1}", Book.class, id);
    }

    public List<Book> test9(List<Long> ids) {
        System.out.println("test9---------"+ids+"Thread.currentThread().getName():" + Thread.currentThread().getName());
        Book[] books = restTemplate.getForObject("http://HELLO-SERVICE/getbook6?ids={1}", Book[].class, StringUtils.join(ids, ","));
        return Arrays.asList(books);
    }

    @HystrixCollapser(batchMethod = "test11",collapserProperties = {@HystrixProperty(name ="timerDelayInMilliseconds",value = "100")})
    public Future<Book> test10(Long id) {
        return null;
    }

    @HystrixCommand
    public List<Book> test11(List<Long> ids) {
        System.out.println("test9---------"+ids+"Thread.currentThread().getName():" + Thread.currentThread().getName());
        Book[] books = restTemplate.getForObject("http://HELLO-SERVICE/getbook6?ids={1}", Book[].class, StringUtils.join(ids, ","));
        return Arrays.asList(books);
    }
}
