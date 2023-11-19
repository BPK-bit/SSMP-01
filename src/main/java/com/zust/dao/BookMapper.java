package com.zust.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zust.domain.Entities.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book>{

    @Insert("insert into tbl_book (type,name,description) values (#{type},#{name},#{description})")
    int insert(Book book);
    @Update("update tbl_book set type=#{type},name=#{name},description=#{description} where id=#{id}")
    int updateById(Book book);

    @Delete("delete from tbl_book where id=#{id}")
    int deleteById(Integer id);
    @Select("select * from tbl_book where id=#{id}")
    Book selectById(Integer id);
    @Select("select * from tbl_book")
    List<Book> selectList(Object o);

}
