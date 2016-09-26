package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.UserEntity;
import cn.edu.zust.info.UserType;
import cn.edu.zust.model.JsonResult;
import cn.edu.zust.model.User;
import cn.edu.zust.service.UserServiceI;
import cn.edu.zust.util.PasswdKit;
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
public class UserServiceImpl implements UserServiceI {
    @Autowired
    BaseDaoI<UserEntity> userDao;

    public Integer save(User user) {
        return (Integer) userDao.save(toEntity(user));
    }

    public boolean usernameUsed(String username) {
        if(getEntityByName(username) == null) {
            return false;
        } else {
            return true;
        }
    }

    public JsonResult register(User user, String pwdcheck) {
        if(user.getPassword() == null || pwdcheck == null || user.getUsername() == null
                 || "".equals(user.getPassword()) || "".equals(pwdcheck) || "".equals(user.getUsername())) {
            return new JsonResult(false, "信息填写不完整！");
        } else if(!user.getPassword().equals(pwdcheck)) {
            return new JsonResult(false, "两次填写的密码不一致！");
        } else if(usernameUsed(user.getUsername())) {
            return new JsonResult(false, "该用户名已注册！");
        }
        user.setPassword(PasswdKit.entryptPassword(user.getPassword()));
        save(user);
        return new JsonResult(true);
    }

    @Override
    public JsonResult login(User user) {
        if(user.getPassword() == null || user.getUsername() == null
                 || "".equals(user.getPassword()) || "".equals(user.getUsername())) {
            return new JsonResult(false, "信息填写不完整！");
        }
        UserEntity userEntity = getEntityByName(user.getUsername());
        if(userEntity != null) {
            if (PasswdKit.validatePassword(user.getPassword(), userEntity.getPassword())) {
                return new JsonResult(true, "", toModel(userEntity));
            }
        }

        return new JsonResult(false, "账号密码不匹配！");
    }

    private UserEntity getEntityByName(String username) {
        String hql = "from UserEntity where username=:username";
        Map<String, Object> value = new HashMap<>();
        value.put("username", username);
        List<UserEntity> result = userDao.find(hql, value);
        if(result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

    private UserEntity toEntity(User model) {
        UserEntity entity;
        if(model.getId() == null || model.getId() == 0) {
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
