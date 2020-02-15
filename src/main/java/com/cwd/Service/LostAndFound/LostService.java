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
    public List<Lost> getLostList(){
//        List<Lost> losts=lostMapper.getLostList();
//        System.out.println("哈哈哈"+losts.get(0).toString());
        return lostMapper.getLostList();
    }
}
