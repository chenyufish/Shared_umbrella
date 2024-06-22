package com.fishman.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fishman.model.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: OrderMapper <br/>
 *
 * @author fishman
 * @date 2024/6/23 
 * 
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
