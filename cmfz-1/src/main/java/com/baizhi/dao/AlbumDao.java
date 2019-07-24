package com.baizhi.dao;

import com.baizhi.entity.Album;

public interface AlbumDao extends BaseDao<Album>{
    //查询总条数Album
    public Integer selectCount();
    //添加
    public void insertAlbum(Album album);
    //删除
    public void deleteAlbum(String id);
    //修改
    public void updateAlbum(Album album);
    //修改封面路径
    public void updateCover(Album album);
}

