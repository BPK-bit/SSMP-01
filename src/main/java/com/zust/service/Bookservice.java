package com.zust.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zust.domain.Book;


import java.util.List;


public interface Bookservice {
    public boolean save(Book book);
    public boolean update(Book book);
    public boolean delete(Integer id);
    public Book getById(Integer id);
    public List<Book> getAll();

    public List<Book> getByPage(Integer current,Integer size);


    public List<Book> getBy();

}
