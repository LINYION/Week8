package com.example.serviceuser.mapper;

import com.example.serviceuser.entity.Follow;
import com.example.serviceuser.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FollowerDao {
    @Select("select followed_id,follower_id,status from follow where followed_id = #{followed_id} and follower_id = #{follower_id}")
    Follow getFollowList(Follow follow);

    @Update("update follow set status = #{status} where followed_id = #{followed_id} and follower_id = #{follower_id}")
    void updateFollow(Follow follows);

    @Insert("insert into follow (followed_id,follower_id,status) values (#{followed_id},#{follower_id},#{status})")
    void saveFollow(Follow follow);

    @Select("select followed_id,folCount from user where followed_id = #{followed_id}")
    User getUserData(Long id) ;

    @Update("update user set folCount = #{folCount} where followed_id = #{followed_id}")
    void updateUser(User userData);

    @Insert("insert into user (followed_id,folCount) values (#{followed_id},#{folCount})")
    void saveUser(User user);
}
