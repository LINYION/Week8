package com.example.servicequestion.mapper;

import com.example.common.entity.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionMapper {
    @Insert(value = "insert into question(from_uid,title,content) values (#{from_uid},#{title},#{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void add(Question question);

    @Select(value = "select * from question q where q.id=#{id}")
    Question findQueById(Long id);

    @Delete(value = "delete from question where id = #{id}")
    void delete(Long id);

    @Update(value = "update question set content = #{content} where id = #{id} ")
    void update(Question question);
    @Select(value = "select * from question q where q.from_uid=#{from_uid}")
    Question findQueByFrom(String from_uid);
}
