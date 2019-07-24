package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    //查询
    public Map<String,Object> queryAll(Integer page,Integer rows,String albumId);
    //添加
    public String addChapter(Chapter chapter);

   public void updatePath(Chapter chapter);
}
