package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.UserInfoEntity;
import cn.edu.zust.model.UserInfo;
import cn.edu.zust.service.UserInfoServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by King on 2016/9/19 0019.
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoServiceI {
    @Autowired
    BaseDaoI<UserInfoEntity> userInfoDao;

    @Override
    public Integer save(UserInfo userInfo) {
        return (Integer) userInfoDao.save(toEntity(userInfo));
    }

    @Override
    public void saveOrUpdate(UserInfo userInfo) {
        if(userInfo.getId() == null) {
            userInfo.setId(0);
        }
        userInfoDao.saveOrUpdate(toEntity(userInfo));
    }

    @Override
    public boolean exists(Integer userId) {
        if(getEntityByUser(userId) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public UserInfo getByUser(Integer userId) {
        return toModel(getEntityByUser(userId));
    }

    private UserInfoEntity getEntityByUser(Integer userId) {
        String hql = "from UserInfoEntity where userId=:userId";
        Map<String, Object> value = new HashMap<>();
        value.put("userId", userId);
        List<UserInfoEntity> result = userInfoDao.find(hql, value);
        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    private UserInfoEntity toEntity(UserInfo model) {
        UserInfoEntity entity;
        if(model.getId() == null || model.getId() == 0) {
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
