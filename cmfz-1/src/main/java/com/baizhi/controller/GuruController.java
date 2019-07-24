package com.baizhi.controller;

import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("guru")
public class GuruController {
    @Autowired
    private GuruService guruService;
    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page, Integer rows){
        System.out.println(page+" "+rows);
        Map<String, Object> map = guruService.queryAll(page, rows);
        return map;
}

}
