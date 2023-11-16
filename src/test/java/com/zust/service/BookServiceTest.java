package com.zust.service;

import com.zust.domain.Book;
import com.zust.service.impl.BookServiceimpl2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    BookServiceimpl2 bookService;

    @Test
    void testSave(){
        Book book = new Book();
        book.setType("测试123");
        book.setName("测试123");
        book.setDescription("测试123");
        bookService.save(book);
    }
    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(7);
        book.setType("测试abc");
        book.setName("测试123");
        book.setDescription("测试123");
        bookService.update(book);
    }
    @Test
    void testDelete(){
        bookService.delete(8);
    }
    @Test
    void testGetAll(){
        List<Book> books = bookService.getAll();
        System.out.println(books);

    }
    @Test
    void testGetById(){
        Book book = bookService.getById(1);
        System.out.println(book);
    }

}
