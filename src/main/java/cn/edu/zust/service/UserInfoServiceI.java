package cn.edu.zust.service;

import cn.edu.zust.model.UserInfo;

/**
 * Created by King on 2016/9/19 0019.
 */
public interface UserInfoServiceI {
    Integer save(UserInfo userInfo);

    void saveOrUpdate(UserInfo userInfo);

    /**
     * 判断用户的详细信息是否存在
     * @param userId
     * @return
     */
    boolean exists(Integer userId);

    /**
     * 获取用户的详细信息
     * @param userId
     * @return
     */
    UserInfo getByUser(Integer userId);
}
