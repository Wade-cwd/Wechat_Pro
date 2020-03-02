package com.cwd.Mapper;

import com.cwd.Entity.Lost;
import com.cwd.Entity.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketMapper {

    //添加一条消息
    @Autowired
    void addOneMarket(Market market);

    @Autowired
    List<Market> getMarketList();
}
