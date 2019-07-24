package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
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
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerDao bannerDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String,Object> map=new HashMap<>();
        Integer recodes = bannerDao.selectCount();
        Integer total=recodes%rows == 0 ? recodes/rows : recodes/rows + 1;
        Integer begin=(page-1)*rows;
        List<Banner> list = bannerDao.selectAll(begin, rows);
        //当前页码
        map.put("page",page);
        //总记录数（条数）
        map.put("recodes",recodes);
        //总页数
        map.put("total",total);
        //查询出来的集合
        map.put("rows",list);
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String addBanner(Banner banner) {
        String id= UUID.randomUUID().toString();
        banner.setId(id);
        bannerDao.insertBanner(banner);
        return id;
    }

    @Override
    public void updatePath(Banner banner) {
        bannerDao.updateBannerPath(banner);
    }

    @Override
    public void deleteBanner(String id) {
        bannerDao.deleteBannerById(id);
    }

    @Override
    public void updateBanner(Banner banner) {
        bannerDao.updateBanner(banner);

    }

}
