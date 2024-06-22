package com.fishman.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fishman.model.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: GoodsMapper <br/>
 *
 * @author fishman
 * @date 2023/6/5 0005
 * 
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
}
