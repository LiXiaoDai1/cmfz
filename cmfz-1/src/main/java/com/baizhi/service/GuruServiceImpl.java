package com.baizhi.service;

import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class GuruServiceImpl implements GuruService{
    @Autowired
    private GuruDao guruDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String,Object> map=new HashMap<>();
        //总条数
        Integer recode = guruDao.selectCount();
        //总页码数
        Integer total=recode%rows==0 ? recode/rows : recode/rows + 1;
        //起始下标
        Integer begin=(page-1) * rows;
        //查询到的集合
        List<Guru> list = guruDao.selectAll(begin, rows);
        map.put("page",page);
        map.put("recode",recode);
        map.put("total",total);
        map.put("rows",list);
        return map;
    }
}
