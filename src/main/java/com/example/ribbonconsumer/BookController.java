package com.example.ribbonconsumer;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Author: dyh
 * Date:   2019/7/10
 * Description:
 */
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/test7")
    @ResponseBody
    public void test7() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        BookCollapseCommand bc1 = new BookCollapseCommand(bookService, 1L);
        BookCollapseCommand bc2 = new BookCollapseCommand(bookService, 2L);
        BookCollapseCommand bc3 = new BookCollapseCommand(bookService, 3L);
        BookCollapseCommand bc4 = new BookCollapseCommand(bookService, 4L);
        Future<Book> q1 = bc1.queue();
        Future<Book> q2 = bc2.queue();
        Future<Book> q3 = bc3.queue();
        Book book1 = q1.get();
        Book book2 = q2.get();
        Book book3 = q3.get();
        Thread.sleep(3000);
        Future<Book> q4 = bc4.queue();
        Book book4 = q4.get();
        System.out.println("book1>>>"+book1);
        System.out.println("book2>>>"+book2);
        System.out.println("book3>>>"+book3);
        System.out.println("book4>>>"+book4);
        context.close();
    }
    @RequestMapping("/test8")
    @ResponseBody
    public void test8() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<Book> f1 = bookService.test10(1L);
        Future<Book> f2 = bookService.test10(2L);
        Future<Book> f3 = bookService.test10(3L);
        Book b1 = f1.get();
        Book b2 = f2.get();
        Book b3 = f3.get();
        Thread.sleep(3000);
        Future<Book> f4 = bookService.test10(4L);
        Book b4 = f4.get();
        System.out.println("b1>>>"+b1);
        System.out.println("b2>>>"+b2);
        System.out.println("b3>>>"+b3);
        System.out.println("b4>>>"+b4);
        context.close();
    }
}
