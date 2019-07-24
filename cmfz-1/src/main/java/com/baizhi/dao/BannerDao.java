package com.baizhi.dao;

import com.baizhi.entity.Banner;

public interface BannerDao extends BaseDao<Banner> {
    //根据id删除
    public void deleteBannerById(String id);
    //添加数据
    public void insertBanner(Banner banner);
    //查询总条数
    public Integer selectCount();
    //修改图片路径
    public void updateBannerPath(Banner banner);
    //修改banner
    public void updateBanner(Banner banner);
}
