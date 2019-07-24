package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
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
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page,Integer rows){
        Map<String, Object> map = albumService.queryAll(page, rows);
        return map;
    }
    @RequestMapping("edit")
    public String edit(Album album, String oper, String[] id){
        if("add".equals(oper)){
            //执行添加
            String s = albumService.addAlbum(album);
            return s;
        }else if("edit".equals(oper)){
            //执行修改方法
            albumService.updateAlbum(album);
            String s=album.getId();
            return s;
        }else{
            //执行删除
            for(String id1 : id){
                albumService.removeAlbum(id1);
            }
        }
        return null;
    }
    @RequestMapping("upload")
    public void uploadCover(String id , MultipartFile coverUrl, HttpServletRequest request , HttpServletResponse response){
        //文件上传
        String originalFilename = coverUrl.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("albumPic");
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        try {
            coverUrl.transferTo(new File(path+"/"+originalFilename));
            //修改数据库的路径
            Album album = new Album();
            album.setId(id);
            album.setCoverUrl(originalFilename);
            albumService.updateCover(album);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
