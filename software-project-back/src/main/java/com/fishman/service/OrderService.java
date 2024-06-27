package com.fishman.service;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fishman.mapper.OrderMapper;
import com.fishman.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: OrderService <br/>
 *
 * @author fishman
 * @date 2024/6/7 0007
 * 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService {
    private final OrderMapper orderMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    /**
     * 查询所有订单
     * @return
     */
    public List<Order> queryAll(){
        return orderMapper.selectList(null);
    }

    /**
     * 依据商品ID查询商品信息
     * @param gId
     * @return
     */
    public List<Order> queryAllByGid(int gId){
        QueryWrapper<Order> wp = new QueryWrapper<>();
        wp.eq("gId",gId);
        return orderMapper.selectList(wp);
    }


    /**
     * 依据卖家ID查询对应的订单
     * @param bId
     * @return
     */
    public List<Order> queryAllByBid(int bId){
        QueryWrapper<Order> wp = new QueryWrapper<>();
        wp.eq("bId",bId);
        return orderMapper.selectList(wp);
    }

    public IPage<Order> search(Order order, int offset, int limit){
        Page<Order> orderPage = new Page<>(offset, limit);
        QueryWrapper<Order> wp = new QueryWrapper<>();
        if(order.getDStatue() != null){
            wp.eq("dStatue",order.getDStatue());
        }
        wp.eq("bId",order.getBId());
        wp.like("dId",order.getDId());
        return orderMapper.selectPage(orderPage,wp);
    }

    // 已支付的所有订单信息

    public List<Order> search(Order order){

        QueryWrapper<Order> wp = new QueryWrapper<>();
        wp.eq("dStatue",order.getDStatue());
        return orderMapper.selectList(wp);
    }

    // 修改订单状态
    public int updatePay(Order order){
        UpdateWrapper<Order> wp = new UpdateWrapper<>();
        wp.eq("dId",order.getDId());
        return orderMapper.updateById(order);
    }

    // 依据订单ID查找订单
    public Order query(int dId){
        QueryWrapper<Order> wp = new QueryWrapper<>();
        wp.eq("dId",dId);
        return orderMapper.selectOne(wp);
    }

    public int add(Order order){
        return orderMapper.insert(order);
    }





}
