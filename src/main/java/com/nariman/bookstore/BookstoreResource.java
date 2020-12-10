package com.nariman.bookstore;

import com.nariman.bookstore.book.Book;
import com.nariman.bookstore.book.BookService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class BookstoreResource {

    private final BookService bookService;

    @Autowired
    public BookstoreResource(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String displayIndex(Map<String, Object> model){

        model.put("books", bookService.getAll());

        return "index";
    }

    @PostMapping("/addBook")
    public RedirectView addBook(@ModelAttribute("Book") @Validated @NonNull Book book){

        bookService.saveOne(book);
        return new RedirectView("/");
    }

    @GetMapping("/addBook")
    public String displayAddBook(){
        return "add";
    }

    @GetMapping("/books/delete/{id}")
    public RedirectView deleteBook(@PathVariable Integer id){
        bookService.deleteOne(id);
        return new RedirectView("/");
    }

    @GetMapping("/books/update/{id}")
    public String displayUpdateBook(Model model, @PathVariable Integer id){

        Book book = bookService.getOne(id);

        model.addAttribute("id", id);
        model.addAttribute("author", book.getAuthor());
        model.addAttribute("publisher", book.getPublisher());
        model.addAttribute("publishDate", book.getPublishDate().toString());

        return "update";
    }

    @PostMapping("/books/update")
    public RedirectView updateBook(@ModelAttribute("Book") @Validated @NonNull Book book){
        return addBook(book);
    }

    @PostMapping("/books/query")
    public ModelAndView query(Map<String, Object> model, @ModelAttribute("query") String query){

        model.put("books", bookService.getByAny(query));

        return new ModelAndView("index", model);
    }

    @GetMapping("/books/query")
    public String displayQuery(){
        return "query";
    }

}
