package com.example.servicequestion.mapper;

import com.example.common.entity.Answer;
import com.example.servicequestion.entity.Likes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LikeDao {
    @Select("select answer_id,user_id,status from likes where answer_id = #{answer_id} and user_id = #{user_id}")
    Likes getLikesList(Likes likes);

    @Insert("insert into likes (answer_id,user_id,status) values (#{answer_id},#{user_id},#{status})")
    void saveLike(Likes likes);

    @Update("update likes set status = #{status} where answer_id = #{answer_id} and user_id = #{user_id}")
    void updateLike(Likes likes);

    @Select("select answer_id,Like_count from answer where answer_id = #{answer_id}")
    Answer getAnswerData(Long answer_id);

    @Insert("insert into answer (answer_id,Like_count) values (#{answer_id},#{LikeCount})")
    void saveAnswer(Answer answer);

    @Update("update answer set Like_count = #{LikeCount} where answer_id = #{answer_id}")
    void updateAnswer(Answer answer);
}
