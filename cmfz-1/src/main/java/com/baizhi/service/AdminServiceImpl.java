package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;
    @Override
    public Map<String, Object> login(Admin admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        Admin admin1 = adminDao.selectAdminByName(admin.getName());
        if(admin1==null){
            map.put("code",300);
            map.put("message","用户名错误");
        }else{
            if(admin1.getPassword().equals(admin.getPassword())){
                map.put("code",200);
                map.put("message","登陆成功");
            }else{
                map.put("code",400);
                map.put("message","密码错误");
            }
        }
        return map;
    }
}
