package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String, Object> map = bannerService.queryAll(page, rows);
        return map;
    }
    @RequestMapping("edit")
    public String edit(Banner banner, String oper , String[] id){
       if("edit".equals(oper)){
           //执行修改代码
            bannerService.updateBanner(banner);
           String s = banner.getId();
           return s;
       }else if("add".equals(oper)){
           //执行添加操作
           String s = bannerService.addBanner(banner);
                return s;
       }else{
           //执行删除操作
           for ( String id1: id) {
            bannerService.deleteBanner(id1);
           }
       }
        return null;
    }
    @RequestMapping("upload")
    public void addBanner(String id , MultipartFile path, HttpServletRequest request , HttpServletResponse response){
        //文件上传
        String originalFilename = path.getOriginalFilename();

        String path1 = request.getSession().getServletContext().getRealPath("photo");
        File file = new File(path1);
       //判断该文件存不存在
        if (!file.exists()){
            //创建文件
            file.mkdir();
        }
        try {
            path.transferTo(new File(path1, originalFilename));
            //修改数据库的路径
            Banner banner = new Banner();
            banner.setId(id);
            banner.setPath(originalFilename);

            bannerService.updatePath(banner);
            }catch(Exception e){
                e.printStackTrace();
            }
    }
}
