package com.fishman;

import com.fishman.model.entity.Goods;
import com.fishman.mapper.GoodsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@MapperScan("com.fishman.mapper")
class SoftwareProjectApplicationTests {

    @Autowired
    GoodsMapper goodsMapper;


    @Test
    void contextLoads() {
        List<Goods> goods = goodsMapper.selectList(null);
        goods.forEach(System.out::println);
    }

 }
