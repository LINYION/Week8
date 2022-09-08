package com.example.servicecomment.mapper;

import com.example.common.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentMapper {
    @Insert(value = "insert into comment(answer_id,content,from_uid) values (#{answer_id},#{content},#{from_uid})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void add(Comment comment);

    @Select(value = "select * from comment c where c.id=#{id}")
    Comment findComById(Long id);

    @Delete(value = "delete from comment where id = #{id}")
    void delete(Long id);

    @Update(value = "update comment set content = #{content} where id = #{id} ")
    void update(Comment comment);
    @Select(value = "select * from comment c where c.from_uid=#{from_uid}")
    Comment findComByFrom(String from_uid);
}

