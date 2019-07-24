package com.baizhi.dao;

import com.baizhi.entity.Guru;

public interface GuruDao extends BaseDao<Guru>{
    //查询总条数
    public Integer selectCount();
}

