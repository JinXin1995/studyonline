package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.UserEntity;
import cn.edu.zust.service.TestServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by King on 2016/9/18 0018.
 */
@Service
@Transactional
public class TestServiceImpl implements TestServiceI {
    @Autowired
    private BaseDaoI<UserEntity> userDao;

    @Override
    public void addUser(String name) {
        UserEntity user = new UserEntity();
        user.setUsername(name);
        userDao.save(user);
    }
}
