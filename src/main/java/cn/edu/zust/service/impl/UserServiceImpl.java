package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.UserEntity;
import cn.edu.zust.model.User;
import cn.edu.zust.service.UserServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by King on 2016/9/19 0019.
 */
public class UserServiceImpl implements UserServiceI {
    @Autowired
    BaseDaoI<UserEntity> userDao;

    private UserEntity toEntity(User model) {
        UserEntity entity;
        if(model.getId() == null) {
            entity = new UserEntity();
        } else {
            entity = userDao.get(UserEntity.class, model.getId());
        }
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    private User toModel(UserEntity entity) {
        User model = new User();
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}
