package cn.edu.zust.service;

import cn.edu.zust.model.Course;

/**
 * Created by King on 2016/9/19 0019.
 */
public interface CourseServiceI {
    Integer save(Course course);

    /**
     * 根据id获取课程的model
     * @param id
     * @return
     */
    Course get(Integer id);
}
