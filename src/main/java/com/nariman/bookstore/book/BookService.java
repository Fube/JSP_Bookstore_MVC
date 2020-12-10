package com.nariman.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public List<Book> getByAny(String query) { return bookRepository.findByAny(query); }

    public Book getOne(Integer id){
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteOne(Integer id){
        bookRepository.deleteById(id);
    }

    public void saveOne(Book book){
        bookRepository.save(book);
    }


}
