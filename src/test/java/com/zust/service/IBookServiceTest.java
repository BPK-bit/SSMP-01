package com.zust.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.domain.Book;
import com.zust.service.impl.BookServiceimpl1;
import com.zust.service.impl.BookServiceimpl2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class IBookServiceTest {
    @Autowired
    BookServiceimpl1 bookService;

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
        bookService.updateById(book);
    }
    @Test
    void testDelete(){
        bookService.removeById(8);
    }
    @Test
    void testGetAll(){
        List<Book> books = bookService.list();
        System.out.println(books);

    }
    @Test
    void testGetById(){
        Book book = bookService.getById(1);
        System.out.println(book);
    }
    @Test
    void testGetByPage(){
        Page<Book> page = new Page<>(1, 5);
        Page<Book> page1 = bookService.page(page);
        System.out.println(page1.getRecords());

    }
}
