package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.CourseEntity;
import cn.edu.zust.model.Course;
import cn.edu.zust.model.JsonResult;
import cn.edu.zust.model.Page;
import cn.edu.zust.service.CourseServiceI;
import cn.edu.zust.service.TypeServiceI;
import cn.edu.zust.service.UserServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by King on 2016/9/19 0019.
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseServiceI {
    @Autowired
    BaseDaoI<CourseEntity> courseDao;
    @Autowired
    TypeServiceI typeService;
    @Autowired
    UserServiceI userService;

    @Override
    public void save(Course course) {
        Date now = new Date();
        if(course.getId() == null) {
            course.setId(0);
            course.setCreateTime(now);
        }
        course.setUpdateTime(now);
        course.setStatus((short) 0);    //课程修改后重新进行审核
        courseDao.saveOrUpdate(toEntity(course));
    }

    @Override
    public List<Course> getByTeacher(int teacherId) {
        String hql = "from CourseEntity where teacherId=:id and status>=0";    //获取章节
        Map<String, Object> value = new HashMap<>();
        value.put("id", teacherId);
        List<CourseEntity> courseEntities = courseDao.find(hql, value);
        List<Course> result = new ArrayList<>();
        for(CourseEntity entity : courseEntities) {
            result.add(toModel(entity));
        }
        return result;
    }

    @Override
    public Page<Course> getByType(int typeId, int pageNo) {
        int size = 17;
        String hql = "from CourseEntity where typeId=:id and status=1";    //获取章节
        Map<String, Object> value = new HashMap<>();
        value.put("id", typeId);
        List<CourseEntity> courseEntities = courseDao.find(hql, value, pageNo, size);
        List<Course> result = new ArrayList<>();
        for(CourseEntity entity : courseEntities) {
            result.add(toModel(entity));
        }
        Page<Course> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(size);
        page.setResult(result);
        long count = courseDao.find(hql, value).size();
        int totalPage = (int)count/size;
        if(count%size > 0) {
            totalPage++;
        }
        page.setTotalPage(totalPage);
        return page;
    }

    @Override
    public Course get(Integer id) {
        if (id == 0) {
            Course course = new Course();
            course.setId(0);
            return course;
        }
        return toModel(courseDao.get(CourseEntity.class, id));
    }

    @Override
    public JsonResult delete(Integer id, Integer userId) {
        CourseEntity courseEntity = courseDao.get(CourseEntity.class, id);
        if(courseEntity.getTeacherId() != userId) {
            return new JsonResult(false, "您无权删除其他人的课程");
        }
        courseEntity.setStatus((short) -1);
        courseDao.update(courseEntity);
        return new JsonResult(true, "删除成功");
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
        if (entity == null) {
            return null;
        }
        Course model = new Course();
        BeanUtils.copyProperties(entity, model);
        model.setType(typeService.get(model.getTypeId()));
        model.setTeacherEntity(userService.get(model.getTeacherId()));
        return model;
    }
}
