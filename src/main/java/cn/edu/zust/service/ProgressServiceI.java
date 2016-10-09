package cn.edu.zust.service;

import cn.edu.zust.model.JsonResult;
import cn.edu.zust.model.Progress;

import java.util.List;

/**
 * Created by King on 2016/9/19 0019.
 */
public interface ProgressServiceI {

    void save(Progress progress);

    JsonResult delete(Integer userId, Integer progressId);

    /**
     * 根据课程与用户获取进度，若无记录，则返回id为0，courseId与userId为给定值的Progress对象
     * @param courseId
     * @param userId
     * @return
     */
    Progress getByCourse(int courseId, int userId);

    /**
     * 获取用户所有的课程进度
     * @param userId
     * @return
     */
    List<Progress> getProgressesByUser(int userId);
}
