package com.cwd.Controller.LostController;


import com.cwd.Entity.Lost;
import com.cwd.Service.LostAndFound.LostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LostController {
    private final Logger logger=LoggerFactory.getLogger(LostController.class);


    @Autowired
    private LostService lostService;

    @PostMapping("/getlosts")
    public Object getLostList(){
        List<Lost> losts=lostService.getLostList();
        logger.info(losts.get(0).toString());
        return  lostService.getLostList();
    }
}
