package com.baizhi.entity;

import com.baizhi.annotation.StudentAnnotion;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    @StudentAnnotion(name="编号")
    private String id;
    @StudentAnnotion(name="姓名")
    private String name;
    @StudentAnnotion(name="班级")
    private String classz;
    @StudentAnnotion(name="年龄")
    private Integer age;
    @StudentAnnotion(name = "分数")
    private Double score;


}
