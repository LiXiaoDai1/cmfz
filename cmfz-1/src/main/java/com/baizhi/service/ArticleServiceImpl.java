package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
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
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleDao articleDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryAll(Integer page, Integer rows) {
      Map<String,Object> map =  new HashMap<String,Object>();
        //总条数
        Integer recode = articleDao.selectCount();
        //总页码
        Integer total=recode%rows== 0 ?recode/rows : recode/rows+1;
        //起始页
        Integer begin=(page-1)*rows;
        List<Article> list = articleDao.selectAll(begin, rows);
        map.put("recode",recode);
        map.put("total",total);
        map.put("rows",list);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removArticle(String id) {
        articleDao.deleteArticle(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String addArticle(Article article) {
        String id= UUID.randomUUID().toString();
        article.setId(id);
        articleDao.insertArticle(article);
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String modifyArticle(Article article) {
        articleDao.updateArticle(article);
        return article.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePath(Article article) {
        articleDao.updatePath(article);
    }
}
