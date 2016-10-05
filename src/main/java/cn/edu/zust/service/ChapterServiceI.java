package cn.edu.zust.service;

import cn.edu.zust.model.Chapter;

import java.util.List;

/**
 * Created by King on 2016/9/19 0019.
 */
public interface ChapterServiceI {
    void save(Chapter chapter);
    /**
     * 根据课程id获取所有章节
     * @param courseId
     * @return
     */
    List<Chapter> getChapters(Integer courseId);

    /**
     * 删除章节及其下的小节/删除小节
     * @param id
     */
    void delete(int id);

    /**
     * 获取课程的章节数（不包括小节）
     * @param courseId
     * @return
     */
    Integer getChapterNum(Integer courseId);

    /**
     * 获取课程指定章节的小节数
     * @param courseId
     * @param number
     * @return
     */
    Integer getSubChapterNum(Integer courseId, Integer number);
}
