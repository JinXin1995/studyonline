package cn.edu.zust.service;

import cn.edu.zust.model.JsonResult;
import cn.edu.zust.model.User;

/**
 * Created by King on 2016/9/19 0019.
 */
public interface UserServiceI {
    /**
     * 保存用户
     * @param user
     * @return 用户id
     */
    Integer save(User user);

    User get(int id);

    /**
     * 检查用户名是否已被使用
     * @param username
     * @return true:已被使用 false：未使用，可用
     */
    boolean usernameUsed(String username);

    /**
     * 用户注册
     * @param user
     * @param pwdcheck
     * @return
     */
    JsonResult register(User user, String pwdcheck);

    /**
     * 用户登录
     * @param user
     * @return
     */
    JsonResult login(User user);

    /**
     * 更新昵称和简介
     * @param userId
     * @param nickname
     * @param intro
     */
    void updateInfo(Integer userId, String nickname, String intro);

    /**
     * 更新密码
     * @param userId
     * @param old
     * @param newPWD
     * @param repeat
     * @return
     */
    JsonResult updatePWD(Integer userId, String old, String newPWD, String repeat);
}
