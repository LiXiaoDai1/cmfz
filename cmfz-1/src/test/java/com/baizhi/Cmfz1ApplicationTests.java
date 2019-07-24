package com.baizhi;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Album;
import com.baizhi.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Cmfz1ApplicationTests {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AlbumDao albumDao;

    @Test
    public void contextLoads() {
        List<Album> list = albumDao.selectAll(0, 3);
        System.out.println(list);
    }
}