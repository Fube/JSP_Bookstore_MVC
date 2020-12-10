package com.nariman.bookstore.book;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "BOOKS")
@Data
@Entity
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    @NonNull
    private String author;

    @NonNull
    private String publisher;



}
