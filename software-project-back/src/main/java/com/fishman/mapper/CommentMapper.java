package com.fishman.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fishman.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: CommentMapper <br/>
 *
 * @author fishman
 * @date 2024/6/7 0007
 * 
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
