package org.frivap.firstwebapp.controller;

import org.springframework.ui.Model;
import org.frivap.firstwebapp.entity.MyBookList;
import org.frivap.firstwebapp.service.BookService;
import org.frivap.firstwebapp.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.frivap.firstwebapp.entity.Book;
import org.springframework.web.servlet.ModelAndView;


import java.util.*;


@Controller
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private MyBookListService myBookService;

    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/book_register")
    public String bookRegister() {
        return "bookRegister";
    }
//    old code only for show page
//    @GetMapping(value = "/available_books")
//    public String getAllBooks(){
//        return "bookList";
//    }
    @GetMapping(value = "/available_books")
    public ModelAndView getAllBooks() {
        List<Book> list = service.getAllbooks();
        // two options for writing code
        //1.
        //
//        ModelAndView m= new ModelAndView();
//        m.setViewName("bookList");
//        m.addObject("book",list);
//        return m;
        //2.
        return new ModelAndView("bookList","book",list);
    }

    @PostMapping(value = "/save")
    public String addBook(@ModelAttribute Book b){
        service.save(b);
        return "redirect:/available_books";
    }
    @GetMapping(value = "/my_books")
    public String getMyBooks(Model model)
    {
        List<MyBookList>list=myBookService.getAllMyBooks();
        model.addAttribute("book",list);
        return "myBooks";
    }


    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book b=service.getBookById(id);
        MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookService.saveMyBooks(mb);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id,Model model) {
        Book b=service.getBookById(id);
        model.addAttribute("book",b);
        return "bookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id")int id) {
        service.deleteById(id);
        myBookService.deleteById(id);
        return "redirect:/available_books";
    }

}
