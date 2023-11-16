package com.zust.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zust.domain.Book;


import java.util.List;


public interface Bookservice {
    boolean save(Book book);
    boolean update(Book book);
    boolean delete(Integer id);
    Book getById(Integer id);
    List<Book> getAll();
    IPage<Book> getByPage(Integer current, Integer size);
    IPage<Book> getByPage(Integer current, Integer size, Book book);
}
