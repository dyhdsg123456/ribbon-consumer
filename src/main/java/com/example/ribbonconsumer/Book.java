package com.example.ribbonconsumer;

/**
 * Author: dyh
 * Date:   2019/7/10
 * Description:
 */
public class Book {
    public Book(){

    }

    private String name;
    private Integer price;
    private String writer;
    private String desc;

    public Book(String name, Integer price, String writer, String desc) {
        this.name = name;
        this.price = price;
        this.writer = writer;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", writer='" + writer + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
