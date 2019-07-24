package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String, Object> map = articleService.queryAll(page,rows);
        return map;
    }

    @RequestMapping("edit")
    public String edit(Article article,String oper,String[] id){
        if("edit".equals(oper)){
            //执行修改代码
            String s = articleService.modifyArticle(article);
            return s;
        }else if("add".equals(oper)){
            //执行添加代码
            String s = articleService.addArticle(article);
            return s;
        }else{
            //执行删除
            for (String id1 : id) {
                articleService.removArticle(id1);
            }
        }
        return null;
    }
    @RequestMapping("upload")
    public void uploadPath(String id , MultipartFile content_url, HttpServletRequest request , HttpServletResponse response) throws Exception{
        //文件上传
        String originalFilename = content_url.getOriginalFilename();

        String path = request.getSession().getServletContext().getRealPath("article");
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        try {
            content_url.transferTo(new File(path+"/"+originalFilename));
            //修改数据库的路径
            Article article = new Article();
            article.setId(id);
            article.setContentUrl(originalFilename);
            articleService.updatePath(article);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
