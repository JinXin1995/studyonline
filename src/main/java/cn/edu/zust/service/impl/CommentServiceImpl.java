package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.CommentEntity;
import cn.edu.zust.model.Comment;
import cn.edu.zust.service.CommentServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by King on 2016/9/19 0019.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentServiceI {
    @Autowired
    BaseDaoI<CommentEntity> commentDao;

    private CommentEntity toEntity(Comment model) {
        CommentEntity entity;
        if(model.getId() == null || model.getId() == 0) {
            entity = new CommentEntity();
        } else {
            entity = commentDao.get(CommentEntity.class, model.getId());
        }
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    private Comment toModel(CommentEntity entity) {
        Comment model = new Comment();
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}
