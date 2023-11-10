package com.zust.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zust.domain.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

}
