package org.example.model.builder;

import org.example.model.Book;

import java.time.LocalDate;
import java.util.Date;

public class BookBuilder {
    private Book book;

     public BookBuilder(){
     book = new Book();
     }

    public BookBuilder setId(Long id){
        book.setId(id);
        return this;
    }

    public BookBuilder setAuthor(String author){
        book.setAuthor(author);
        return this;
    }

    public BookBuilder setTitle(String title){
        book.setTitle(title);
        return this;
    }

    public BookBuilder setPublishedDate(LocalDate publishedDate){
        book.setPublishedDate(publishedDate);
        return this;
    }

    public BookBuilder setPrice(int price) {
         book.setPrice(price);
         return this;
    }

    public BookBuilder setStock(int stock) {
         book.setStock(stock);
         return this;
    }

    public Book build(){
        return book;
    }
}
