package cn.edu.zust.service;

import cn.edu.zust.model.Chapter;
import cn.edu.zust.model.Course;
import cn.edu.zust.model.User;
import cn.edu.zust.model.UserInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/10/2 0002.
 */
public interface AdministratorI {
    List<User> alogin(String username, String password);
    List<UserInfo> userpage(int page);
    List<UserInfo> students();
    void ice(int id);
    List<UserInfo> icepage(int page);
    List<UserInfo> iceuser();
    void uniceuser(int id);
    List<UserInfo> teacherpage(int page);
    List<UserInfo> teacher();
    void teacherpass(int id);
    void teacherunpass(int id);
    List<Course> coursepage(int page);
    List<Course> course();
    void coursepass(int id);
    void courseunpass(int id);
    List<Chapter> chapterpage(int page,int cid);
    List<Chapter> chapter(int cid);
    void chapterpass(int id);
    void chapterunpass(int id);
    String classname(int id);
    void deleteuser(int id);
    void deletecourse(int id);
//    void deleteteacher(int id);
    void deletechapter(int id);

}
