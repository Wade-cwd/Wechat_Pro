package com.cwd.Service.LostAndFound;

import com.cwd.Entity.Lost;
import com.cwd.Mapper.LostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*失物招领业务层
* */
@Service
public class LostService {
    @Autowired
    private LostMapper lostMapper;



    //处理失物招领列表业务
    public List<Lost> getLostList(){
        return lostMapper.getLostList();
    }
    //添加一条失物招领列表业务
    public void addLostItem(Lost lost){
        lostMapper.addLostItem(lost);
    }

}
