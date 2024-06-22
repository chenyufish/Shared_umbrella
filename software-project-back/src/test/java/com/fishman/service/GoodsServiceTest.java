package com.fishman.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fishman.model.entity.Goods;
import com.fishman.mapper.GoodsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: GoodsServiceTest <br/>
 *
 * @author fishman
 * @date 2023/6/10 0010
 * 
 */
@SpringBootTest
public class GoodsServiceTest {
    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsMapper goodsMapper;

    @Test
    public void pageTest(){
        Goods goods = new Goods();
        goods.setGName("");
        int page = 2;
        int limit = 10;
        IPage<Goods> search = goodsService.search(goods, page, limit);
        System.out.println(search.toString());
    }

    @Test
    public void goodsPageTest(){

    }

}
