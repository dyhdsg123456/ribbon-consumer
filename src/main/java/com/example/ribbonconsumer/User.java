package com.example.ribbonconsumer;

/**
 * Author: dyh
 * Date:   2019/7/4
 * Description:
 */
public class User {
    private String name;
    private Long id;
    public User(){

    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
