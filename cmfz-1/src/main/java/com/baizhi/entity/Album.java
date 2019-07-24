package com.baizhi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album implements Serializable {
    private String id;
    //专辑名
    private String name;
    //专辑的作者
    private String author;
    //播音员
    private String announcer;
    //介绍
    private String intro;
    //封面路径
    private String coverUrl;
    //上传时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date publish;
    //分数
    private Integer score;
    //该专辑下的总章节数
    private Integer count;

}
