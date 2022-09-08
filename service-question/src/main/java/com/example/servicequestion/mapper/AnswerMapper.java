package com.example.servicequestion.mapper;

import com.example.common.entity.Answer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AnswerMapper {
    @Insert(value = "insert into answer(question_id,from_uid,content,LikeCount) values (#{question_id},#{from_uid},#{content},#{LikeCount})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void add(Answer answer);
    @Select(value = "select * from answer a where a.id=#{answer_id}")
    Answer findAnsById(Long answer_id);
    @Delete(value = "delete from answer where id = #{answer_id}")
    void delete(Long answer_id);
    @Update(value = "update answer set content = #{content} where id = #{id}")
    void update(Answer answer);
    @Select(value = "select * from answer a where a.from_uid=#{from_uid}")
    Answer findAnsByFrom(String from_uid);
}
