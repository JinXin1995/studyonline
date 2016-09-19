package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.ChapterEntity;
import cn.edu.zust.model.Chapter;
import cn.edu.zust.service.ChapterServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by King on 2016/9/19 0019.
 */
public class ChapterServiceImpl implements ChapterServiceI {
    @Autowired
    BaseDaoI<ChapterEntity> chapterDao;

    private ChapterEntity toEntity(Chapter model) {
        ChapterEntity entity;
        if(model.getId() == null) {
            entity = new ChapterEntity();
        } else {
            entity = chapterDao.get(ChapterEntity.class, model.getId());
        }
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    private Chapter toModel(ChapterEntity entity) {
        Chapter model = new Chapter();
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}
