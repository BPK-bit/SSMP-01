package com.zust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zust.dao.BookMapper;
import com.zust.domain.Entities.Book;
import com.zust.service.Bookservice;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceimpl2 implements Bookservice {
    @Autowired
    BookMapper bookMapper;

    @Override
    public boolean save(Book book) {
        return bookMapper.insert(book) > 0;
    }

    @Override
    public boolean update(Book book) {
        return bookMapper.updateById(book) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return bookMapper.deleteById(id)>0;
    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.selectById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookMapper.selectList(null);
    }

    @Override
    public IPage<Book> getByPage(Integer current, Integer size) {
        Page<Book> page = new Page<>(current, size);
        return bookMapper.selectPage(page,null);
    }
    @Override
    public IPage<Book> getByPage(Integer current, Integer size, Book book) {
        IPage<Book> page = new Page<>(current,size);

        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(book.getType()),Book::getType,book.getType());
        queryWrapper.like(Strings.isNotEmpty(book.getName()),Book::getName,book.getName());
        queryWrapper.like(Strings.isNotEmpty(book.getDescription()),Book::getDescription,book.getDescription());

        return bookMapper.selectPage(page,queryWrapper);
    }

}
