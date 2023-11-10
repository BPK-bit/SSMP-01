package com.zust.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookMapperTest {
    @Autowired
    private BookMapper bookMapper;

    @Test
    void testGetById(){
        Book book = bookMapper.selectById(1);
        System.out.println(book);
    }
    @Test
    void testSave(){
        Book book = new Book();
        book.setType("测试123");
        book.setName("测试123");
        book.setDescription("测试123");
        bookMapper.insert(book);
    }
    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(7);
        book.setType("测试abc");
        book.setName("测试123");
        book.setDescription("测试123");
        bookMapper.updateById(book);
    }
    @Test
    void testDelete(){
        bookMapper.deleteById(8);
    }
    @Test
    void testGetAll(){
        List<Book> books = bookMapper.selectList(null);

    }
    @Test
    void testGetByPage(){
        IPage page = new Page(1,5);
        bookMapper.selectPage(page,null);
    }
    @Test
    void testGetBy(){
        QueryWrapper<Book> qw= new QueryWrapper<>();
        qw.like("name","a");
        bookMapper.selectList(qw);
    }

}
