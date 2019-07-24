package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService{
    @Autowired
     private ChapterDao chapterDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryAll(Integer page, Integer rows,String albumId) {
        Map<String,Object> map=new HashMap<>();
        //总条数
        Integer recode = chapterDao.selectCount();
        //总页码数
        Integer total=recode%rows==0 ? recode/rows : recode/rows + 1;
        //起始下标
        Integer begin=(page-1) * rows;
        //查询到的集合
        List<Chapter> list = chapterDao.selectAll(begin, rows, albumId);
       map.put("page",page);
        map.put("recode",recode);
        map.put("total",total);
        map.put("rows",list);
        return map;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String addChapter(Chapter chapter) {
        String id= UUID.randomUUID().toString();
        chapter.setId(id);
        chapterDao.insertChapter(chapter);
        return id;

    }

    @Override
    public void updatePath(Chapter chapter) {

        chapterDao.updatePath(chapter);
    }
}
