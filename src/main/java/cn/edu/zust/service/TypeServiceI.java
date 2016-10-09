package cn.edu.zust.service;

import cn.edu.zust.model.Type;

import java.util.List;

/**
 * Created by King on 2016/9/19 0019.
 */
public interface TypeServiceI {
    /**
     * 获得所有的课程分类
     * @return
     */
    List<Type> getTypes();

    /**
     * 根据id返回type
     * @param id
     * @return
     */
    Type get(Integer id);
}
