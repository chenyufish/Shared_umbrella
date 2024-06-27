package com.fishman.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fishman.model.entity.Goods;
import com.fishman.service.CurrentUserService;
import com.fishman.service.GoodsService;
import com.fishman.utils.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: GoodsController <br/>
 *
 * @author fishman
 * @date 2024/6/23 
 * 
 */
@RestController
@RequestMapping(value = "/goods",produces = "application/json")
@Api(value = "雨伞管理",tags = "雨伞管理接口")
public class GoodsController {

    private final GoodsService goodsService;

    private final CurrentUserService currentUserService;

    public GoodsController(GoodsService goodsService, CurrentUserService currentUserService) {
        this.goodsService = goodsService;
        this.currentUserService = currentUserService;
    }


    @ApiOperation(value = "查询所有雨伞", notes = "查询所有雨伞信息")
    @GetMapping("/all")
    @CrossOrigin
    public Map<String, Object> queryAll() {
        try {
            List<Goods> goods = goodsService.queryGoods();

            // 统计 ulevel 数量
            Map<Integer, Long> ulevelCount = goods.stream()
                    .collect(Collectors.groupingBy(Goods::getGLevel, Collectors.counting()));

            // 构建返回数据
            Map<String, Object> result = new HashMap<>();
            result.put("goods", goods);
            result.put("ulevelCount", ulevelCount);
            return Message.queryMessage(result, goods.size());
        } catch (Exception e) {
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }

    @ApiOperation(value = "雨伞查询",notes = "依据prompt对雨伞进行雨伞名的模糊查询")
    @GetMapping("/search/{prompt}")
    @ApiImplicitParam(name = "prompt",value = "依据雨伞名进行模糊搜索的关键字")
    @CrossOrigin
    public Map<String,Object> queryAll(
            @PathVariable String prompt)
    {
        try{
            List<Goods> search = goodsService.search(prompt);
            return Message.queryMessage(search,search.size());
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }

    @ApiOperation(value = "搜索雨伞",notes = "带分页进行雨伞模糊搜索")
    @CrossOrigin
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goods",value = "依据goods里的属性进行雨伞的模糊查询",dataType = "Goods(Object)"),
            @ApiImplicitParam(name = "page",value = "分页查询中要查看第几页",defaultValue = "1"),
            @ApiImplicitParam(name = "limit",value = "分页查询中每页显示的数量",defaultValue = "10" )
    })
    @GetMapping("/search")
    public Map<String,Object> queryAll(
            @ModelAttribute Goods goods,
            @RequestParam(value = "page",defaultValue = "1") int page,
            @RequestParam(value = "limit",defaultValue = "10") int limit)
    {
        try{
            IPage<Goods> search = goodsService.search(goods, page, limit);
            return Message.queryMessage(search);
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }

    @CrossOrigin
    @ApiOperation(value = "修改雨伞",notes = "修改雨伞信息")
    @ApiImplicitParam(name = "goods",value = "雨伞的实例对象，由前端传入对象的对应属性")
    @PostMapping("/update")
    public Map<String, Object> updateGoods(
            @RequestParam(value = "token",defaultValue = "null") String token,
            @ModelAttribute Goods goods)
    {
        System.out.println(token);
        try {
            if(currentUserService.verityToken(token)){
                int i = goodsService.update(goods);
                if(i > 0){
                    return Message.getMessage("修改成功");
                }else {
                    return Message.getMessage("修改失败");
                }
            }else {
                return Message.getMessage("身份校验失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }

    @ApiOperation(value = "添加雨伞",notes = "添加一条雨伞记录")
    @PostMapping("/add")
    @CrossOrigin
    @ApiImplicitParam(name = "umbrellas",value = "雨伞的实例对象，由前端传入对象的对应属性")
    public Map<String,Object> addGoods(
            @RequestParam String token,
            @ModelAttribute Goods goods)
    {
        try {
            if(currentUserService.verityToken(token)){
                int i = goodsService.addGoods(goods);
                if(i > 0){
                    return Message.getMessage("添加成功");
                }else {
                    return Message.getMessage("添加失败");
                }
            }else{
                return Message.getMessage("身份校验失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }

    @ApiOperation(value = "删除雨伞",notes = "删除一条雨伞记录")
    @PostMapping("/delete")
    @CrossOrigin
    public Map<String, Object> deleteGoods(
            @RequestParam(value = "token",defaultValue = "null") String token,
            @ModelAttribute Goods goods)
    {
        try {
            if(currentUserService.verityToken(token)){
                if(goodsService.delete(goods) > 0){
                    return Message.getMessage("删除成功");
                }else{
                    return Message.getMessage("删除失败");
                }
            }else{
                return Message.getMessage("身份校验失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }

    @PostMapping("/deleteById")
    @CrossOrigin
    public Map<String,Object> deleteGoods(
            @RequestParam(defaultValue = "null") String token,
            @RequestParam(value = "listId") List<Integer> ids
    ){
        try{
            if(currentUserService.verityToken(token)){
                int delete = goodsService.delete(ids);
                return Message.getMessage("成功删除"+delete+"条雨伞");
            }else{
                return Message.getMessage("身份校验失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.getMessage("服务器处理异常");
        }
    }


}
