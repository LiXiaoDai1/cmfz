package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao extends BaseDao<Chapter>{
    //查询所有
  public List<Chapter> selectAll(@Param("begin") Integer begin , @Param("rows") Integer rows , @Param("aId") String albumId);

    //查询总条数
    public Integer selectCount();
    //添加
    public void insertChapter(Chapter chapter);
    //修改路径
   public  void updatePath(Chapter chapter);
}
