package com.zust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zust.dao.BookMapper;
import com.zust.domain.Book;
import com.zust.service.IBookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceimpl1 extends ServiceImpl<BookMapper, Book> implements IBookService {
    @Autowired
    BookMapper bookMapper;
    @Override
    public IPage<Book> getPage(Integer current, Integer size) {
        Page<Book> page = new Page<>(current, size);
        return bookMapper.selectPage(page,null);
    }
    @Override
    public IPage<Book> getPage(Integer current, Integer size,Book book) {
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(book.getType()),Book::getType,book.getType());
        queryWrapper.like(Strings.isNotEmpty(book.getName()),Book::getName,book.getName());
        queryWrapper.like(Strings.isNotEmpty(book.getDescription()),Book::getDescription,book.getDescription());
        Page<Book> page = new Page<>(current, size);

        return bookMapper.selectPage(page,queryWrapper);
    }
}
