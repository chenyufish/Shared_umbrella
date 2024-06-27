package com.fishman.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fishman.model.entity.Goods;
import com.fishman.model.entity.Order;
import com.fishman.model.entity.Profit;
import com.fishman.model.entity.User;
import com.fishman.service.CurrentUserService;
import com.fishman.service.GoodsService;
import com.fishman.service.OrderService;
import com.fishman.service.ProfitService;
import com.fishman.utils.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

/**
 * ClassName: OrderController <br/>
 *
 * @author fishman
 * @date 2024/6/7 0007
 * 
 */
@RestController
@RequestMapping("/order")
@Api(value = "订单管理",tags = "订单管理操作的相关接口")
public class OrderController {

    private final OrderService orderService;

    private final CurrentUserService currentUserService;

    private final GoodsService goodsService;

    private final ProfitService profitService;

    public OrderController(OrderService orderService, CurrentUserService currentUserService, GoodsService goodsService, ProfitService profitService) {
        this.orderService = orderService;
        this.currentUserService = currentUserService;
        this.goodsService = goodsService;
        this.profitService = profitService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "订单查询接口")
    @CrossOrigin
    @ApiImplicitParam(name = "token",value = "用户Token值")
    public Map<String,Object> queryAll(
            @RequestParam(value = "token",defaultValue = "null") String token,
            @ModelAttribute Order order,
            @RequestParam(value="page" , defaultValue="1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ){
        try{
            if(currentUserService.verityToken(token)){
                IPage<Order> search = orderService.search(order, page, limit);
                return Message.queryMessage(search);
            }else{
                return Message.getMessage("身份校验未通过");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }

    @GetMapping("/allByGoods")
    @ApiOperation(value = "查询雨伞订单")
    @CrossOrigin
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token",value = "用户Token值"),
        @ApiImplicitParam(name = "goods",value = "雨伞对象，可以只传gId")
    })
    public Map<String,Object> queryAll(
            @RequestParam(value = "token",defaultValue = "null") String token,
            @ModelAttribute Goods goods
            ){
        try {
            if(currentUserService.verityToken(token)){
                List<Order> data = orderService.queryAllByGid(goods.getGId());
                return Message.queryMessage(data,data.size());
            }else{
                return Message.getMessage("身份校验未通过");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }

    @GetMapping("/allByUser")
    @ApiOperation(value = "查询用户对应的订单")
    @CrossOrigin
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "用户Token值"),
            @ApiImplicitParam(name = "user", value = "用户对象，可以只传uId")
    })
    public Map<String,Object> queryAll(
            @RequestParam(value = "token",defaultValue = "null") String token,
            @ModelAttribute User user
            ){
        try{
            if(currentUserService.verityToken(token)){
                List<Order> ordersByBuyer = orderService.queryAllByBid(user.getUId());
                HashMap<String,Object> mp = new HashMap<>();
                mp.put("buyer",ordersByBuyer);
                mp.put("buyerSize",ordersByBuyer.size());
                return Message.getMessage(mp);
            }else{
                return Message.getMessage("身份校验未通过");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }

    @GetMapping("/queryData")
    @CrossOrigin
    public Map<String,Object> queryData(
            @RequestParam(value = "token",defaultValue = "null") String token,
            @ModelAttribute Order order
    ){
        try{
            if(currentUserService.verityToken(token)){
                List<Order> search = orderService.search(order);
                return Message.queryMessage(search,search.size());
            }else{
                return Message.getMessage("身份校验失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }

    @PostMapping("/updatePay")
    @CrossOrigin
    public Map<String,Object> updatePay(
            @RequestParam String token,
            @RequestParam(value = "dId") int dId
    ){
        try{
            if (currentUserService.verityToken(token)){
                Order query = orderService.query(dId);
                query.setDStatue(true);
                int i = orderService.updatePay(query);
                return Message.getMessage("支付成功！");
            }else{
                return Message.getMessage("身份校验失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }

    /**
     * 生成订单，根据传入的位置glevel，随机选择对应的商品
     * @param token
     * @param order
     * @param glevel
     * @return
     */
    @ApiOperation(value = "添加订单", notes = "添加一条订单记录")
    @PostMapping("/add")
    @CrossOrigin
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份验证token", required = true),
            @ApiImplicitParam(name = "order", value = "订单对象，由前端传入对象的对应属性", required = true),
            @ApiImplicitParam(name = "glevel", value = "商品位置", required = true)
    })
    public Map<String, Object> addOrder(
            @RequestParam String token,
            @ModelAttribute Order order,
            @RequestParam Integer glevel // 新增参数 glevel
    ) {
        try {
            // 根据 glevel 获取符合条件的商品ID列表
            List<Integer> goodsIdsByLevel = goodsService.getGoodsIdsByLevel(glevel);

            // 如果列表不为空，则随机选择一个商品ID赋给订单对象
            if (!goodsIdsByLevel.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(goodsIdsByLevel.size());
                Integer randomGoodsId = goodsIdsByLevel.get(randomIndex);

                // 设置订单的商品ID
                order.setGId(randomGoodsId);

                // 根据商品ID查询商品信息并更新
                Goods query = goodsService.query(randomGoodsId);
                if (query != null) {
                    order.setDAmount(query.getGPrice()); // 设置商品价格
                    // 其他需要设置的商品信息
                } else {
                    return Message.getMessage("未找到符合条件的商品信息");
                }
            } else {
                return Message.getMessage("没有符合条件的商品");
            }

            // 设置订单的生成时间
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            order.setDDate(timestamp);
            order.setDStatue(false);
            // 更新商品信息（这里假设更新商品的某些信息）
            Goods updatedGoods = new Goods();
            updatedGoods.setGId(order.getGId());
            updatedGoods.setGLevel(0); // 假设需要更新商品的某些属性
            goodsService.update(updatedGoods);



            orderService.add(order);
            return Message.getMessage("订单生成成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }


    @PostMapping("/profit")
    @CrossOrigin
    public Map<String,Object> profit(
            @RequestParam(defaultValue = "token") String token,
            @RequestParam(value="dId") Integer dId
    ){
        try{
            if(currentUserService.verityToken(token) && "admin".equals(currentUserService.queryToken(token).getUNickname())){
                // 获取订单收益
                Order order = orderService.query(dId);
                Double dAmount = order.getDAmount();
                // 创建
                Profit profit = new Profit();
                profit.setDId(dId);
                profit.setProfit(dAmount*0.02);
                int i = profitService.addProfit(profit);
                return Message.getMessage("成功获取收益");

            }else{
                return Message.getMessage("身份校验失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常！");
        }
    }

    @GetMapping("/profitAll")
    @CrossOrigin
    public Map<String,Object> profit(
            @RequestParam(defaultValue = "token") String token
    ){
        try{
            if(currentUserService.verityToken(token) && "admin".equals(currentUserService.queryToken(token).getUNickname())){
                List<Profit> profits = profitService.queryAll();
                List<Integer> gId = new ArrayList<>();
                List<Double> prts = new ArrayList<>();
                for(Profit profit: profits){
                    Integer dId = profit.getDId();
                    Integer gId1 = orderService.query(dId).getGId();
                    gId.add(gId1);
                    prts.add(profit.getProfit());
                }
                HashMap<String, Object> mp = new HashMap<>();
                mp.put("gIds",gId);
                mp.put("prts",prts);
                return Message.getMessage(mp);
            }else{
                return Message.getMessage("身份校验失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常！");
        }
    }

}
