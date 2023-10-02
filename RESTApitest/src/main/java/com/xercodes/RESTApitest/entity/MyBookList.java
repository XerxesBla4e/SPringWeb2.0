package com.xercodes.RESTApitest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MyBook")
public class MyBookList {
    @Id
    @Column(name = "book_id")
    private int id;
    private String author;
    private String name;
    private String price;

    public  MyBookList() {
        super();
    }
    public MyBookList(int id, String author, String name, String price) {
        super();
        this.id = id;
        this.author = author;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
