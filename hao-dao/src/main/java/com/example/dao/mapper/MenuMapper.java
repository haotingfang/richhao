package com.example.dao.mapper;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    public List<String> selectMenuPermsByUserId(Long userId);
}
