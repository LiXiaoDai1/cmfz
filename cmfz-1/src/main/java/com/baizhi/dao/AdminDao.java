package com.baizhi.dao;


import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDao {
    //根据姓名查询
    public Admin selectAdminByName(@Param("name") String name);

}
