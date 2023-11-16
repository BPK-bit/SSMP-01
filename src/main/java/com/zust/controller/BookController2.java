package com.zust.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zust.service.impl.BookServiceimpl2;
import com.zust.utils.R;
import com.zust.domain.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "图书相关接口")
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
    @ApiOperation("新增图书")
    @PostMapping
    public R save(@RequestBody Book book){
        return new R(bookService.save(book));
    }

    /**
     *修改图书信息
     * @param book
     * @return
     */
    @ApiOperation("修改图书信息")
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
    @ApiOperation("删除数据")
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(bookService.delete(id));
    }

    /**
     * 查询单条数据
     * @param id
     * @return
     */
    @ApiOperation("查询单条数据")
    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){

        return new R(true,bookService.getById(id));
    }

    /**
     * 查询全部
     * @return
     */
    @ApiOperation("查询全部")
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
    @ApiOperation("分页查询")
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
