package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
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
public class AlbumServiceImpl implements AlbumService{
    @Autowired
    private AlbumDao albumDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String,Object> map=new HashMap<>();
        //获得总条数recode
        Integer recode = albumDao.selectCount();
        //计算总页数和起始数
        Integer total=recode % rows == 0 ? recode/rows : recode/rows+1;
        Integer begin=(page-1)*rows;
        List<Album> list = albumDao.selectAll(begin, rows);
        map.put("recode",recode);
        map.put("page",page);
        map.put("total",total);
        map.put("rows",list);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String addAlbum(Album album) {
       String id= UUID.randomUUID().toString();
        album.setId(id);
        albumDao.insertAlbum(album);
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateAlbum(Album album) {
        albumDao.updateAlbum(album);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeAlbum(String id) {
        albumDao.deleteAlbum(id);
    }

    @Override
    public void updateCover(Album album) {
        albumDao.updateCover(album);
    }
}
