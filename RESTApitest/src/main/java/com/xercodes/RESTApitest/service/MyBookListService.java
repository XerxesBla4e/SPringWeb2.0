package com.xercodes.RESTApitest.service;

import com.xercodes.RESTApitest.entity.MyBookList;
import com.xercodes.RESTApitest.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {
    @Autowired
    private MyBookRepository myBookRepository;

    public void saveMyBook(MyBookList book) {
        myBookRepository.save(book);
    }

    public List<MyBookList> getAllMyBooks() {
        return myBookRepository.findAll();
    }

    public void deleteById(int id) {
        myBookRepository.findById(id).ifPresent(existing -> myBookRepository.deleteById(id));
    }
}
