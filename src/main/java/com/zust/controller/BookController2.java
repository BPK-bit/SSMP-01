package com.zust.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zust.service.impl.BookServiceimpl2;
import com.zust.utils.R;
import com.zust.domain.Entities.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@Api(tags = "图书相关接口")
@RestController
@RequestMapping("/books")
public class BookController2 {
    @Autowired
    BookServiceimpl2 bookService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ObjectMapper mapper;

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
    public R getById(@PathVariable Integer id){return new R(true,bookService.getById(id));}

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
    public R getByPage(@PathVariable Integer current, @PathVariable Integer size,Book book) throws JsonProcessingException {
        //动态构造key
        String name = book.getName();
        String type = book.getType();
        String description = book.getDescription();
        String key ="getByPage_"+current+"_"+size+"_"+name+"_"+type+"_"+description;

        //判断缓存中是否存在数据
        if (redisTemplate.hasKey(key)){
            System.out.println(key);
            String val = redisTemplate.opsForValue().get(key);
            Page page = mapper.readValue(val, Page.class);
            return new R(null != page,page);
        }

        //调用业务层的方法
        Page<Book> page = (Page<Book>) bookService.getByPage(current, size,book);
        //解决删除操作后出现的bug
        if(current>page.getPages()){
            page = (Page<Book>) bookService.getByPage((int) page.getPages(), size,book);
        }
        //缓存得到的数据

        String json = mapper.writeValueAsString(page);
        redisTemplate.opsForValue().set(key,json,30, TimeUnit.MINUTES);


        return new R(null != page,page);
    }

}
