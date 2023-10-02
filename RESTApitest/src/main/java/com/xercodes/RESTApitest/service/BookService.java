package com.xercodes.RESTApitest.service;

import com.xercodes.RESTApitest.entity.Book;
import com.xercodes.RESTApitest.exceptions.NotFoundException;
import com.xercodes.RESTApitest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void save(Book book) {
        // Add input validation logic if necessary
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.orElseThrow(() -> new NotFoundException("Book not found with ID: " + id));
    }

    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
}
