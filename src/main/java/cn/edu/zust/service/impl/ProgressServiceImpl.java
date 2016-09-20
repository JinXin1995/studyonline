package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.ProgressEntity;
import cn.edu.zust.model.Progress;
import cn.edu.zust.service.ProgressServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by King on 2016/9/19 0019.
 */
@Service
@Transactional
public class ProgressServiceImpl implements ProgressServiceI {
    @Autowired
    BaseDaoI<ProgressEntity> progressDao;

    private ProgressEntity toEntity(Progress model) {
        ProgressEntity entity;
        if(model.getId() == null) {
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
        return model;
    }
}
