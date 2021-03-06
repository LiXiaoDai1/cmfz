package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page,Integer rows,String albumId){
        Map<String, Object> map = chapterService.queryAll(page, rows, albumId);
        return map;
    }
    @RequestMapping("download")
    public void download(String downPath, HttpServletRequest request, HttpServletResponse response) throws Exception{
        //指定路径
        String path=request.getServletContext().getRealPath("music");
        //获取当前文件
        File file=new File(path,downPath);
        String substring = downPath.substring(downPath.lastIndexOf("."));
        //设置响应类型
        response.setContentType(request.getSession().getServletContext().getMimeType(substring));
        //设置响应头
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(downPath,"UTF-8"));
        //将文件响应到浏览器
        FileCopyUtils.copy(new FileInputStream(file),response.getOutputStream());
    }
    @RequestMapping("edit")
    public String edit(Chapter chapter){
        String s = chapterService.addChapter(chapter);
        return s;
    }

    @RequestMapping("upload")
    public void uploadPath(String id , MultipartFile downPath, HttpServletRequest request , HttpServletResponse response) throws Exception{
        //文件上传
        String originalFilename = downPath.getOriginalFilename();

        String path = request.getSession().getServletContext().getRealPath("music");
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        try {
            downPath.transferTo(new File(path+"/"+originalFilename));
            //修改数据库的路径
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setDownPath(originalFilename);
            chapterService.updatePath(chapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
