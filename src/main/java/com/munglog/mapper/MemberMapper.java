package com.munglog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.munglog.entity.Member;

@Mapper
public interface MemberMapper {
    List<Member> findAll();
}
