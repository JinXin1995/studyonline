package cn.edu.zust.service;

import cn.edu.zust.model.Course;
import cn.edu.zust.model.JsonResult;
import cn.edu.zust.model.Page;

import java.util.List;

/**
 * Created by King on 2016/9/19 0019.
 */
public interface CourseServiceI {
    void save(Course course);

    /**
     * 根据id获取课程的model,若id为0，则返回一个id为0的空model
     * @param id
     * @return
     */
    Course get(Integer id);

    /**
     * 若指定用户为指定课程的创建者，则删除课程
     * @param id
     * @param userId
     * @return
     */
    JsonResult delete(Integer id, Integer userId);

    /**
     * 返回指定教师创建的课程
     * @param teacherId
     * @return
     */
    List<Course> getByTeacher(int teacherId);

    Page<Course> getByType(int typeId, int page);
}
