package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.ProgressEntity;
import cn.edu.zust.model.JsonResult;
import cn.edu.zust.model.Progress;
import cn.edu.zust.service.ChapterServiceI;
import cn.edu.zust.service.CourseServiceI;
import cn.edu.zust.service.ProgressServiceI;
import cn.edu.zust.service.UserServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by King on 2016/9/19 0019.
 */
@Service
@Transactional
public class ProgressServiceImpl implements ProgressServiceI {
    @Autowired
    BaseDaoI<ProgressEntity> progressDao;
    @Autowired
    CourseServiceI courseService;
    @Autowired
    ChapterServiceI chapterService;

    @Override
    public void save(Progress progress) {
        progress.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        progressDao.saveOrUpdate(toEntity(progress));
    }

    @Override
    public JsonResult delete(Integer userId, Integer progressId) {
        ProgressEntity progress = progressDao.get(ProgressEntity.class, progressId);
        if(!userId.equals(progress.getUserId())) {
            return new JsonResult(false, "无权进行此操作！");
        }
        progressDao.delete(progress);
        return new JsonResult(true, "删除完成");
    }

    @Override
    public Progress getByCourse(int courseId, int userId) {
        Progress progress;
        String hql = "from ProgressEntity where courseId=:cid and userId=:uid";
        Map<String, Object> params = new HashMap<>();
        params.put("cid", courseId);
        params.put("uid", userId);
        List<ProgressEntity> result = progressDao.find(hql, params);
        if(result.size() == 0) {
            progress = new Progress();
            progress.setId(0);
            progress.setCourseId(courseId);
            progress.setUserId(userId);
            progress.setChapterId(chapterService.getChapters(courseId).get(0).getSubchapter().get(0).getId());
        } else {
            progress = toModel(result.get(0));
        }
        return progress;
    }

    @Override
    public List<Progress> getProgressesByUser(int userId) {
        String hql = "from ProgressEntity where userId=:uid";
        Map<String, Object> params = new HashMap<>();
        params.put("uid", userId);
        List<ProgressEntity> result = progressDao.find(hql, params);
        List<Progress> progresses = new ArrayList<>();
        for (ProgressEntity entity : result) {
            progresses.add(toModel(entity));
        }
        return progresses;
    }

    private ProgressEntity toEntity(Progress model) {
        ProgressEntity entity;
        if(model.getId() == null || model.getId() == 0) {
            entity = new ProgressEntity();
        } else {
            entity = progressDao.get(ProgressEntity.class, model.getId());
        }
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    private Progress toModel(ProgressEntity entity) {
        Progress model = new Progress();
        BeanUtils.copyProperties(entity, model);
        model.setCourse(courseService.get(model.getCourseId()));
        model.setChapter(chapterService.get(model.getChapterId()));
        return model;
    }
}
