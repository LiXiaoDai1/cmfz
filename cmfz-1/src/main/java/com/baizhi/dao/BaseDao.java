package com.baizhi.dao;

import java.util.List;

//所有模块都需要查询写一个查询方法让其余的继承
public interface BaseDao<T> {
    //要做分页begin是起始下标 rows是每页显示的条数
    public List<T> selectAll(Integer begin,Integer rows);
}
