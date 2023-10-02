package com.xercodes.RESTApitest.repository;

import com.xercodes.RESTApitest.entity.Book;
import com.xercodes.RESTApitest.entity.MyBookList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBookRepository extends JpaRepository<MyBookList,Integer> {
}
