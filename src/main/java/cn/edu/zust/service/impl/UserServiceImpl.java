package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.UserEntity;
import cn.edu.zust.info.UserType;
import cn.edu.zust.model.JsonResult;
import cn.edu.zust.model.User;
import cn.edu.zust.model.UserInfo;
import cn.edu.zust.service.UserInfoServiceI;
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
    @Autowired
    UserInfoServiceI userInfoService;

    public Integer save(User user) {
        return (Integer) userDao.save(toEntity(user));
    }

    @Override
    public User get(int id) {
        return toModel(userDao.get(UserEntity.class, id));
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
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(save(user));
        userInfo.setId(0);
        userInfoService.save(userInfo);
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
                if(userEntity.getType() == UserType.FREEZY_TEACHER || userEntity.getType() == UserType.FREEZY_STUDENT) {
                    return new JsonResult(false, "账号已被冻结！");
                }
                if(userEntity.getType() == UserType.UNVERIFY_TEACHER) {
                    return new JsonResult(false, "您的信息正在审核中，请耐心等待。");
                }
                if(userEntity.getType() == UserType.UNPASS_TEACHER) {
                    return new JsonResult(true, ""+UserType.UNPASS_TEACHER, toModel(userEntity));
                }
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

    @Override
    public void updateInfo(Integer userId, String nickname, String intro) {
        UserEntity user = userDao.get(UserEntity.class, userId);
        user.setNickname(nickname);
        user.setIntro(intro);
        userDao.update(user);
    }

    @Override
    public JsonResult updatePWD(Integer userId, String old, String newPWD, String repeat) {
        if(newPWD == null || "".equals(newPWD)) {
            return new JsonResult(false, "密码不能为空");
        }
        if(!newPWD.equals(repeat)) {
            return new JsonResult(false, "两次输入的密码不一致！");
        }
        UserEntity user = userDao.get(UserEntity.class, userId);
        if (PasswdKit.validatePassword(old, user.getPassword())) {
            user.setPassword(PasswdKit.entryptPassword(newPWD));
            userDao.update(user);
            return new JsonResult(true, "密码修改成功！");
        }
        return new JsonResult(false, "原密码错误！");
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
        if (model.getNickname() == null || "".equals(model.getNickname())) {
            model.setNickname(model.getUsername());
        }
        if(userInfoService.exists(model.getId())) {
            model.setInfo(userInfoService.getByUser(model.getId()));
        }
        return model;
    }
}
