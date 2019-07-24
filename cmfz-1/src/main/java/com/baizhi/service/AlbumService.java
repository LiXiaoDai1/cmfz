package com.baizhi.service;


import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    //查询所有
    public Map<String,Object> queryAll(Integer page, Integer rows);
    //添加
    public String addAlbum(Album album);
    //修改
    public void updateAlbum(Album album);
    //删除
    public void removeAlbum(String id);
    //修改封面路径
    public void updateCover(Album album);

}
