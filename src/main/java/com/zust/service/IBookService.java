package com.zust.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zust.domain.Book;

public interface IBookService extends IService<Book> {
    public IPage<Book> getPage(Integer current,Integer size);
    public IPage<Book> getPage(Integer current, Integer size,Book book);
}
