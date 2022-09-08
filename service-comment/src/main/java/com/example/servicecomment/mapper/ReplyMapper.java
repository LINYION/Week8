package com.example.servicecomment.mapper;

import com.example.servicecomment.entity.Reply;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReplyMapper {
    @Insert(value = "insert into reply(comment_id,reply_id,reply_type,content,from_uid,to_uid) values " +
            "(#{comment_id},#{reply_id},#{reply_type},#{content},#{from_uid},#{to_uid})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void add(Reply reply);

    @Select(value = "select * from reply r where r.id=#{id}")
    Reply findReplyById(Long id);

    @Delete(value = "delete from reply where id = #{id}")
    void delete(Long id);

    @Update(value = "update reply set content = #{content} where id = #{id} ")
    void update(Reply reply);
}
