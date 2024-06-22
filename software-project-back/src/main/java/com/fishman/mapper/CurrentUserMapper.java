package com.fishman.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fishman.model.entity.CurrentUser;
import com.fishman.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName: CurrentUserMapper <br/>
 *
 * @author fishman
 * @date 2024/6/23 
 * 
 */
@Mapper
public interface CurrentUserMapper extends BaseMapper<CurrentUser> {

    @Select(value="{CALL get_user_by_token(#{token})}")
    User getUserByToken(@Param("token") String token);

}
