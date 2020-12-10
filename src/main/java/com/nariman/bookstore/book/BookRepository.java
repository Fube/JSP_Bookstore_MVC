package com.nariman.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("FROM Book WHERE author LIKE %?1% OR publisher LIKE %?1%")
    List<Book> findByAny(String query);
}
