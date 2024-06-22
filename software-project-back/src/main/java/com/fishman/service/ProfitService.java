package com.fishman.service;

import com.fishman.model.entity.Profit;
import com.fishman.mapper.ProfitMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: ProfitService <br/>
 *
 * @author fishman
 * @date 2023/6/11 0011
 * 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProfitService {
    final
    ProfitMapper profitMapper;

    public ProfitService(ProfitMapper profitMapper) {
        this.profitMapper = profitMapper;
    }
    // 增加收益记录
    public int addProfit(Profit profit){
        return profitMapper.insert(profit);
    }

    public List<Profit> queryAll(){
        return profitMapper.selectList(null);
    }
}
