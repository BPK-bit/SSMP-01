package com.zust.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zust.controller.utils.R;
import com.zust.domain.Book;
import com.zust.service.impl.BookServiceimpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController2 {
    @Autowired
    BookServiceimpl1 bookService;

    @PostMapping
    public R save(@RequestBody Book book){
        return new R(bookService.save(book));
    }
    @PutMapping
    public R update(@RequestBody Book book){
        boolean flag = bookService.updateById(book);
        return new R(flag,flag?"添加成功":"添加失败");
    }
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){

        return new R(bookService.removeById(id));
    }
    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){

        return new R(true,bookService.getById(id));
    }
    @GetMapping
    public R getAll(){

        return new R(true,bookService.list());
    }
    @GetMapping("{current}/{size}")
    public R getByPage(@PathVariable Integer current, @PathVariable Integer size,Book book){
        System.out.println(book);
        IPage<Book> page = bookService.getPage(current, size,book);
        if(current>page.getPages()){
            page = bookService.getPage((int) page.getPages(), size,book);
        }
        return new R(null != page,page);
    }
}
