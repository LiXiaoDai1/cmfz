package com.baizhi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guru implements Serializable {
    private String id;
    private String name;
    private String profile;
    private String status;
}
