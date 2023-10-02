package com.xercodes.RESTApitest.controler;

import com.xercodes.RESTApitest.entity.Book;
import com.xercodes.RESTApitest.entity.MyBookList;
import com.xercodes.RESTApitest.service.BookService;
import com.xercodes.RESTApitest.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(path = "/api/v1/book")
public class BookControler {
    @Autowired
    private BookService bookService;
    @Autowired
    private MyBookListService myBookListService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAllBooks() {
        List<Book> list = bookService.getAllBooks();
        //ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("/api/v1/book/bookList");
        //modelAndView.addObject("book",list);
        return new ModelAndView("bookList", "Book", list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book, RedirectAttributes ra) {
        bookService.save(book);
        ra.addFlashAttribute("message", "Book added successfully");
        return "redirect:/api/v1/book/available_books";
    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model) {
        List<MyBookList> list = myBookListService.getAllMyBooks();
        model.addAttribute("Book", list);
        return "myBooks";
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book book = bookService.getBookById(id);
        if (!Objects.equals(book, null)) {
            MyBookList myBookList = new MyBookList(book.getId(), book.getAuthor(), book.getName(), book.getPrice());
            myBookListService.saveMyBook(myBookList);
            return "redirect:/api/v1/book/my_books";
        }
        return "error/404";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        Book b = bookService.getBookById(id);
        model.addAttribute("Book", b);

        return "BookEdit";
    }

    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute Book updatedBook, @RequestParam("id") int bookId, RedirectAttributes ra) {
        // Fetch the existing book by ID
        Book existingBook = bookService.getBookById(bookId);

        if (existingBook != null) {
            // Update the existing book's fields
            existingBook.setName(updatedBook.getName());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPrice(updatedBook.getPrice());

            // Save the updated book
            bookService.save(existingBook);

            ra.addFlashAttribute("message", "Book updated successfully");
        } else {
            ra.addFlashAttribute("message", "Book not found");
        }

        return "redirect:/api/v1/book/available_books";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        bookService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Deleted Successfully");
        return "redirect:/api/v1/book/available_books";
    }
}
