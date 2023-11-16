package com.zust.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zust.service.impl.BookServiceimpl2;
import com.zust.utils.R;
import com.zust.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController2 {
    @Autowired
    BookServiceimpl2 bookService;

    /**
     * 新增图书
     * @param book
     * @return
     */
    @PostMapping
    public R save(@RequestBody Book book){
        return new R(bookService.save(book));
    }

    /**
     *修改图书信息
     * @param book
     * @return
     */
    @PutMapping
    public R update(@RequestBody Book book){
        boolean flag = bookService.update(book);
        return new R(flag,flag?"修改成功":"修改失败");
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(bookService.delete(id));
    }

    /**
     * 查询单条数据
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){

        return new R(true,bookService.getById(id));
    }

    /**
     * 查询全部
     * @return
     */
    @GetMapping
    public R getAll(){
        return new R(true,bookService.getAll());
    }

    /**
     * 分页查询
     * @param current
     * @param size
     * @param book
     * @return
     */
    @GetMapping("{current}/{size}")
    public R getByPage(@PathVariable Integer current, @PathVariable Integer size,Book book){
        System.out.println(book);
        IPage<Book> page = bookService.getByPage(current, size,book);
        if(current>page.getPages()){
            page = bookService.getByPage((int) page.getPages(), size,book);
        }
        return new R(null != page,page);
    }

}
