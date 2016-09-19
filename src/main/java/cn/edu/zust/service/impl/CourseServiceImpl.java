package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.CourseEntity;
import cn.edu.zust.model.Course;
import cn.edu.zust.service.CourseServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by King on 2016/9/19 0019.
 */
public class CourseServiceImpl implements CourseServiceI {
    @Autowired
    BaseDaoI<CourseEntity> courseDao;

    private CourseEntity toEntity(Course model) {
        CourseEntity entity;
        if(model.getId() == null) {
            entity = new CourseEntity();
        } else {
            entity = courseDao.get(CourseEntity.class, model.getId());
        }
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    private Course toModel(CourseEntity entity) {
        Course model = new Course();
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}