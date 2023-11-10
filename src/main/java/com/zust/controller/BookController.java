package com.zust.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zust.domain.Book;
import com.zust.service.impl.BookServiceimpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/books")
public class BookController {
    @Autowired
    BookServiceimpl1 bookService;

    @PostMapping
    public boolean save(@RequestBody Book book){
        return bookService.save(book);
    }
    @PutMapping
    public boolean update(@RequestBody Book book){
        return bookService.updateById(book);
    }
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id){
        return bookService.removeById(id);
    }
    @GetMapping("{id}")
    public Book getById(@PathVariable Integer id){
        return bookService.getById(id);
    }
    @GetMapping
    public List<Book> getAll(){
        return bookService.list();
    }
    @GetMapping("{current}/{size}")
    public IPage<Book> getByPage(@PathVariable Integer current, @PathVariable Integer size){
        return bookService.getPage(current,size);
    }
}
