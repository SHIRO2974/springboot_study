package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.study.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    int insert(Post post);
    Post selectById(@Param("postId") int postId);
    List<Post> selectAllByKeyword(
            @Param("startIndex") int startIndex,
            @Param("LimitCount") int limitCount,
            @Param("keyword") String keyword);



}
