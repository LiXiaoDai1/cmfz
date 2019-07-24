package com.baizhi.service;

import com.baizhi.entity.Banner;

;
import java.util.Map;

public interface BannerService {
    //查询
    public Map<String,Object> queryAll(Integer page,Integer rows);
    //添加
    public String addBanner(Banner banner);
    //修改图片路径
    public void updatePath(Banner banner);
    //删除banner
    public void deleteBanner(String id);
    //修改banner
    public void updateBanner(Banner banner);
}
