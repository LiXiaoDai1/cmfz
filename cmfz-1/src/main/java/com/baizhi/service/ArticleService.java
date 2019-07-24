package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {
    //查询所有
    public Map<String,Object> queryAll(Integer page, Integer rows);
    //删除
    public  void removArticle(String id);
    //添加
    public String addArticle(Article article);
    //修改
    public String modifyArticle(Article article);

    public void updatePath(Article article);
}
