package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.CourseEntity;
import cn.edu.zust.model.Course;
import cn.edu.zust.service.CourseServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by King on 2016/9/19 0019.
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseServiceI {
    @Autowired
    BaseDaoI<CourseEntity> courseDao;

    @Override
    public Integer save(Course course) {
        course.setId(0);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        course.setUpdateTime(now);
        course.setCreateTime(now);
        course.setStatus((short) 0);
        return (int) courseDao.save(toEntity(course));
    }

    private CourseEntity toEntity(Course model) {
        CourseEntity entity;
        if(model.getId() == null || model.getId() == 0) {
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
