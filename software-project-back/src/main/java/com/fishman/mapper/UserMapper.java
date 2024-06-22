package com.fishman.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fishman.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: UserMapper <br/>
 *
 * @author fishman
 * @date 2024/6/23 
 * 
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT uId,uPhone,uIsVerify,uAddress,uLevel,uNickName,uEmail FROM user")
    @Results({
            @Result(property = "uId",column = "uId"),
            @Result(property = "uPhone",column = "uPhone"),
            @Result(property = "uIsVerify",column = "uIsVerify"),
            @Result(property = "uAddress",column = "uAddress"),
            @Result(property = "uLevel",column = "uLevel"),
            @Result(property = "uNickname",column = "uNickname"),
            @Result(property = "uEmail",column = "uEmail")
    })
    List<User> getAllUserInfo();
}
