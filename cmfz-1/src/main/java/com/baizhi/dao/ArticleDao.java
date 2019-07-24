package com.baizhi.dao;

import com.baizhi.entity.Article;

public interface ArticleDao extends BaseDao<Article>{
    //查询总条数
    public Integer selectCount();
    //删除
    public void deleteArticle(String id);
    //添加
    public void insertArticle(Article article);
    //修改
    public void updateArticle(Article article);
    //修改路径
   public void updatePath(Article article);
}
