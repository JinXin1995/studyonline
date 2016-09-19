package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.UserInfoEntity;
import cn.edu.zust.model.UserInfo;
import cn.edu.zust.service.UserInfoServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by King on 2016/9/19 0019.
 */
public class UserInfoServiceImpl implements UserInfoServiceI {
    @Autowired
    BaseDaoI<UserInfoEntity> userInfoDao;

    private UserInfoEntity toEntity(UserInfo model) {
        UserInfoEntity entity;
        if(model.getId() == null) {
            entity = new UserInfoEntity();
        } else {
            entity = userInfoDao.get(UserInfoEntity.class, model.getId());
        }
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    private UserInfo toModel(UserInfoEntity entity) {
        UserInfo model = new UserInfo();
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}
