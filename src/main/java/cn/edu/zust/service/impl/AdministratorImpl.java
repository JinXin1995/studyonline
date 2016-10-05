package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.*;
import cn.edu.zust.model.Chapter;
import cn.edu.zust.model.Course;
import cn.edu.zust.model.User;
import cn.edu.zust.model.UserInfo;
import cn.edu.zust.service.AdministratorI;
import org.codehaus.jackson.map.deser.ValueInstantiators;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static cn.edu.zust.info.UserType.*;

/**
 * Created by Administrator on 2016/10/2 0002.
 */
@Service
@Transactional
public class AdministratorImpl implements AdministratorI {
    @Autowired
    BaseDaoI<UserEntity> userDao;
    @Autowired
    BaseDaoI<UserInfoEntity> userinfoDao;
    @Autowired
    BaseDaoI<CourseEntity> courseDao;
    @Autowired
    BaseDaoI<ChapterEntity> chapterEntityDao;
    @Autowired
    BaseDaoI<TypeEntity> typedao;

    @Override
    public List<User> alogin(String username, String password){
        List<UserEntity> adminie=userDao.find("from UserEntity as t where t.password='"+password+"' and t.username='"+username+"' and t.type="+ADMIN);
        List<User> adminies=new ArrayList<User>();
        if(adminie.size()==0){
            return null;
        }
        for(UserEntity userEntity:adminie){
            User admin = new User();
            BeanUtils.copyProperties(userEntity, admin);
            adminies.add(admin);
        }
        return adminies;
    }


    @Override
    public List<UserInfo> userpage(int page){
        List<UserInfoEntity> studentinfoentitys=userinfoDao.find("select t from UserInfoEntity as t,UserEntity as s where t.userId=s.id and type="+STUDENT,page,5);
        List<UserInfo> studentinfos=new ArrayList<UserInfo>();
        if(studentinfoentitys.size()==0){
            return null;
        }
        for(UserInfoEntity studentinfoentity:studentinfoentitys) {
            UserInfo studentinfo = new UserInfo();
            BeanUtils.copyProperties(studentinfoentity, studentinfo);
            List<UserEntity> students=userDao.find("from UserEntity as t where t.id="+studentinfoentity.getUserId());
            studentinfo.setUsername(students.get(0).getUsername());
            studentinfos.add(studentinfo);
        }

        return studentinfos;
    }

    @Override
    public List<UserInfo> students(){
        List<UserInfoEntity> studentinfoentitys=userinfoDao.find("select t from UserInfoEntity as t,UserEntity as s where t.userId=s.id and type="+STUDENT);
        List<UserInfo> studentinfos=new ArrayList<UserInfo>();
        if(studentinfoentitys.size()==0){
            return null;
        }
        for(UserInfoEntity studentinfoentity:studentinfoentitys) {
            UserInfo studentinfo = new UserInfo();
            BeanUtils.copyProperties(studentinfoentity, studentinfo);
            List<UserEntity> students=userDao.find("from UserEntity as t where t.id="+studentinfoentity.getUserId());
            studentinfo.setUsername(students.get(0).getUsername());
            studentinfos.add(studentinfo);
        }

        return studentinfos;
    }

    @Override
    public void ice(int id){

        List<UserEntity> userEntities=userDao.find("from UserEntity as t where t.id="+id);
        int type=userEntities.get(0).getType();
        System.out.print("被冻结的类型是："+type);
        if (type==STUDENT){
            userDao.executeHql("update UserEntity as t set t.type="+FREEZY_STUDENT+" where t.id="+id);
        }else if (type==TEACHER){
            userDao.executeHql("update UserEntity as t set t.type="+FREEZY_TEACHER+" where t.id="+id);
        }
    }

    @Override
    public List<UserInfo> icepage(int page){
        List<UserInfoEntity> studentinfoentitys=userinfoDao.find("select t from UserInfoEntity as t,UserEntity as s where t.userId=s.id and (s.type="+FREEZY_STUDENT+" or s.type="+FREEZY_TEACHER+")",page,5);
        List<UserInfo> studentinfos=new ArrayList<UserInfo>();
        if(studentinfoentitys.size()==0){
            return null;
        }
        for(UserInfoEntity studentinfoentity:studentinfoentitys) {
            UserInfo studentinfo = new UserInfo();
            BeanUtils.copyProperties(studentinfoentity, studentinfo);
            List<UserEntity> students=userDao.find("from UserEntity as t where t.id="+studentinfoentity.getUserId());
            studentinfo.setUsername(students.get(0).getUsername());
            studentinfos.add(studentinfo);
        }

        return studentinfos;

    }

    @Override
    public List<UserInfo> iceuser(){
        List<UserInfoEntity> studentinfoentitys=userinfoDao.find("select t from UserInfoEntity as t,UserEntity as s where t.userId=s.id and (s.type="+FREEZY_STUDENT+" or s.type="+FREEZY_TEACHER+")");
        List<UserInfo> studentinfos=new ArrayList<UserInfo>();
        if(studentinfoentitys.size()==0){
            return null;
        }
        for(UserInfoEntity studentinfoentity:studentinfoentitys) {
            UserInfo studentinfo = new UserInfo();
            BeanUtils.copyProperties(studentinfoentity, studentinfo);
            List<UserEntity> students=userDao.find("from UserEntity as t where t.id="+studentinfoentity.getUserId());
            studentinfo.setUsername(students.get(0).getUsername());
            studentinfos.add(studentinfo);
        }

        return studentinfos;
    }

    @Override
    public void uniceuser(int id){
        List<UserEntity> userEntities=userDao.find("from UserEntity as t where t.id="+id);
        int type=userEntities.get(0).getType();
        if (type==FREEZY_STUDENT){
            userDao.executeHql("update UserEntity as t set t.type="+STUDENT+" where t.id="+id);
        }else if (type==FREEZY_TEACHER){
            userDao.executeHql("update UserEntity as t set t.type="+UNVERIFY_TEACHER+" where t.id="+id);
        }
    }

    @Override
    public List<UserInfo> teacherpage(int page){
        List<UserInfoEntity> studentinfoentitys=userinfoDao.find("select t from UserInfoEntity as t,UserEntity as s where t.userId=s.id and (s.type="+TEACHER+" or s.type="+UNPASS_TEACHER+" or s.type="+UNVERIFY_TEACHER+")",page,5);
        List<UserInfo> studentinfos=new ArrayList<UserInfo>();
        if(studentinfoentitys.size()==0){
            return null;
        }
        for(UserInfoEntity studentinfoentity:studentinfoentitys) {
            UserInfo studentinfo = new UserInfo();
            BeanUtils.copyProperties(studentinfoentity, studentinfo);
            List<UserEntity> students=userDao.find("from UserEntity as t where t.id="+studentinfoentity.getUserId());
            String shenhezhuangtai="无状态";
            int a=students.get(0).getType();
            if (a==TEACHER){
                shenhezhuangtai="通过";
            }else if(a==UNPASS_TEACHER){
                shenhezhuangtai="未通过";
            }else if(a==UNVERIFY_TEACHER){
                shenhezhuangtai="未审核";
            }
            long course=userDao.count("select count(*) from CourseEntity as t where t.teacherId="+studentinfoentity.getUserId());
            int courses=(int)course;

            studentinfo.setShenhe(a);
            studentinfo.setShenhezhuangtai(shenhezhuangtai);
            studentinfo.setCourse(courses);
            studentinfo.setUsername(students.get(0).getUsername());
            studentinfos.add(studentinfo);
        }

        return studentinfos;

    }

    @Override
    public List<UserInfo> teacher(){
        List<UserInfoEntity> studentinfoentitys=userinfoDao.find("select t from UserInfoEntity as t,UserEntity as s where t.userId=s.id and (s.type="+TEACHER+" or s.type="+UNPASS_TEACHER+" or s.type="+UNVERIFY_TEACHER+")");
        List<UserInfo> studentinfos=new ArrayList<UserInfo>();
        if(studentinfoentitys.size()==0){
            return null;
        }
        for(UserInfoEntity studentinfoentity:studentinfoentitys) {
            UserInfo studentinfo = new UserInfo();
            BeanUtils.copyProperties(studentinfoentity, studentinfo);
            List<UserEntity> students=userDao.find("from UserEntity as t where t.id="+studentinfoentity.getUserId());
            String shenhezhuangtai="无状态";
            int a=students.get(0).getType();
            if (a==TEACHER){
                shenhezhuangtai="通过";
            }else if(a==UNPASS_TEACHER){
                shenhezhuangtai="未通过";
            }else if(a==UNVERIFY_TEACHER){
                shenhezhuangtai="未审核";
            }
            long course=userDao.count("select count(*) from CourseEntity as t where t.teacherId="+studentinfoentity.getUserId());
            int courses=(int)course;

            studentinfo.setShenhe(a);
            studentinfo.setCourse(courses);
            studentinfo.setShenhezhuangtai(shenhezhuangtai);
            studentinfo.setUsername(students.get(0).getUsername());
            studentinfos.add(studentinfo);
        }

        return studentinfos;
    }

    @Override
    public void teacherpass(int id){
        userDao.executeHql("update UserEntity as t set t.type="+TEACHER+" where t.id="+id);
    }

    @Override
    public void teacherunpass(int id){
        userDao.executeHql("update UserEntity as t set t.type="+UNPASS_TEACHER+" where t.id="+id);
    }

    @Override
    public List<Course> course(){
        List<CourseEntity> studentinfoentitys=courseDao.find("from CourseEntity as t where t.status>=0");
        List<Course> studentinfos=new ArrayList<Course>();
        if(studentinfoentitys.size()==0){
            return null;
        }
        for(CourseEntity studentinfoentity:studentinfoentitys) {
            Course studentinfo = new Course();
            BeanUtils.copyProperties(studentinfoentity, studentinfo);

            List<CourseEntity> students=courseDao.find("from CourseEntity as t where t.id="+studentinfoentity.getId());
            String shenhezhuangtai="无状态";
            int a=students.get(0).getStatus();
            if (a==0){
                shenhezhuangtai="未审核";
            }else if(a==1){
                shenhezhuangtai="通过";
            }else if(a==2){
                shenhezhuangtai="未通过";
            }
            byte b=(byte)0;
            long course=courseDao.count("select count(*) from ChapterEntity as t where  t.status>=0 and t.courseId="+studentinfoentity.getId());
            int courses=(int)course;
            List<UserEntity> teacher=userDao.find("from UserEntity as t where t.id="+studentinfoentity.getTeacherId());

            studentinfo.setTeacher(teacher.get(0).getUsername());
            studentinfo.setChapter(courses);
            studentinfo.setStatuszhuangtai(shenhezhuangtai);
            studentinfos.add(studentinfo);
        }

        return studentinfos;

    }

    @Override
    public List<Course> coursepage(int page){
        List<CourseEntity> studentinfoentitys=courseDao.find("from CourseEntity as t where t.status>=0",page,5);

        List<Course> studentinfos=new ArrayList<Course>();
        if(studentinfoentitys.size()==0){
            return null;
        }
        for(CourseEntity studentinfoentity:studentinfoentitys) {
            Course studentinfo = new Course();
            BeanUtils.copyProperties(studentinfoentity, studentinfo);

            List<CourseEntity> students=courseDao.find("from CourseEntity as t where t.id="+studentinfoentity.getId());
            String shenhezhuangtai="无状态";
            int a=students.get(0).getStatus();
            if (a==0){
                shenhezhuangtai="未审核";
            }else if(a==1){
                shenhezhuangtai="通过";
            }else if(a==2){
                shenhezhuangtai="未通过";
            }
            long course=courseDao.count("select count(*) from ChapterEntity as t where  t.status>=0 and t.courseId="+studentinfoentity.getId());
            int courses=(int)course;
            List<UserEntity> teacher=userDao.find("from UserEntity as t where t.id="+studentinfoentity.getTeacherId());

            int study=0;
            List<ChapterEntity>chapterEntities=chapterEntityDao.find("from ChapterEntity as t where t.courseId="+studentinfoentity.getId());
            for(ChapterEntity chapterEntity:chapterEntities){
                long progress=chapterEntityDao.count("select count(*) from ProgressEntity as t where t.chapterId="+chapterEntity.getId());
                int progeress2=(int)progress;
                study=study+progeress2;
            }

            List<TypeEntity>typeEntities=typedao.find("from TypeEntity as t where t.id="+studentinfoentity.getTypeId());

            studentinfo.setTypename(typeEntities.get(0).getName());
            studentinfo.setStudy(study);
            studentinfo.setTeacher(teacher.get(0).getUsername());
            studentinfo.setChapter(courses);
            studentinfo.setStatuszhuangtai(shenhezhuangtai);
            studentinfos.add(studentinfo);
        }
        return studentinfos;

    }

    @Override
    public void coursepass(int id){
        userDao.executeHql("update CourseEntity as t set t.status=1 where t.id="+id);
    }

    @Override
    public void courseunpass(int id){
        userDao.executeHql("update CourseEntity as t set t.status=2 where t.id="+id);
    }

    @Override
    public List<Chapter>chapter(int cid){
        List<ChapterEntity> studentinfoentitys=chapterEntityDao.find("from ChapterEntity as t where  t.status>=0 and t.courseId="+cid);
        List<Chapter> studentinfos=new ArrayList<Chapter>();
        if(studentinfoentitys.size()==0){
            return null;
        }
        for(ChapterEntity studentinfoentity:studentinfoentitys) {
            Chapter studentinfo = new Chapter();
            BeanUtils.copyProperties(studentinfoentity, studentinfo);

            List<ChapterEntity> students=chapterEntityDao.find("from ChapterEntity as t where t.id="+studentinfoentity.getId());
            String shenhezhuangtai="无状态";
            int a=students.get(0).getStatus();
            if (a==0){
                shenhezhuangtai="未审核";
            }else if(a==1){
                shenhezhuangtai="通过";
            }else if(a==2){
                shenhezhuangtai="未通过";
            }
            long course=courseDao.count("select count(*) from ProgressEntity as t where t.chapterId="+studentinfoentity.getId());
            int courses=(int)course;

            studentinfo.setStudy(courses);
            studentinfo.setStauszhuangtai(shenhezhuangtai);
            studentinfos.add(studentinfo);
        }

        return studentinfos;

    }


    @Override
    public List<Chapter>chapterpage(int cid,int page){
        List<ChapterEntity> studentinfoentitys=chapterEntityDao.find("from ChapterEntity as t where  t.status>=0 and t.courseId="+cid,page,5);
        List<Chapter> studentinfos=new ArrayList<Chapter>();
        if(studentinfoentitys.size()==0){
            return null;
        }
        for(ChapterEntity studentinfoentity:studentinfoentitys) {
            Chapter studentinfo = new Chapter();
            BeanUtils.copyProperties(studentinfoentity, studentinfo);

            List<ChapterEntity> students=chapterEntityDao.find("from ChapterEntity as t where t.id="+studentinfoentity.getId());
            String shenhezhuangtai="无状态";
            int a=students.get(0).getStatus();
            if (a==0){
                shenhezhuangtai="未审核";
            }else if(a==1){
                shenhezhuangtai="通过";
            }else if(a==2){
                shenhezhuangtai="未通过";
            }
            long course=courseDao.count("select count(*) from ProgressEntity as t where t.chapterId="+studentinfoentity.getId());
            int courses=(int)course;

            studentinfo.setStudy(courses);
            studentinfo.setStauszhuangtai(shenhezhuangtai);
            studentinfos.add(studentinfo);
        }

        return studentinfos;
//        List<ChapterEntity>chapterEntities=chapterEntityDao.find("from ChapterEntity as t where t.courseId="+cid,page,5);
//        List<Chapter>chapters=new ArrayList<Chapter>();
//        if (chapterEntities.size()==0){
//            return null;
//        }else{
//        for(ChapterEntity chapterEntity:chapterEntities){
//            Chapter chapter=new Chapter();
//            BeanUtils.copyProperties(chapterEntity, chapter);
//            String shenhezhuangtai="无状态";
//            int a=chapterEntity.getStatus();
//            if (a==0){
//                shenhezhuangtai="未审核";
//            }else if(a==1){
//                shenhezhuangtai="通过";
//            }else if(a==2){
//                shenhezhuangtai="未通过";
//            }
//            long progress=chapterEntityDao.count("select count(*) from ProgressEntity as t where t.chapterId="+chapterEntity.getId());
//            int progeress2=(int)progress;
//
//            chapter.setStudy(progeress2);
//            chapter.setStauszhuangtai(shenhezhuangtai);
//            chapters.add(chapter);
//        }
//        return chapters;}
    }

    @Override
    public void chapterpass(int id) {
        userDao.executeHql("update ChapterEntity as t set t.status=1 where t.id="+id);

    }

    @Override
    public void chapterunpass(int id) {
        userDao.executeHql("update ChapterEntity as t set t.status=2 where t.id="+id);
    }

    @Override
    public String classname(int id){
        List<CourseEntity>courseEntities=courseDao.find("from CourseEntity as t where t.id="+id);
        String name=courseEntities.get(0).getName();
        return name;
    }

    @Override
    public void deleteuser(int id){
        userDao.executeHql("update  UserEntity as t set t.type=-1 where t.id="+id);
        List<CourseEntity>courseEntities=courseDao.find("from CourseEntity as t where t.teacherId="+id);
        if (courseEntities.size()!=0){
            userDao.executeHql("update  CourseEntity as t set t.status=-1 where t.teacherId="+id);
        }

    }

    @Override
    public void deletecourse(int id){
        userDao.executeHql("update  CourseEntity as t set t.status=-1 where t.id="+id);
        List<ChapterEntity>courseEntities=chapterEntityDao.find("from ChapterEntity as t where t.courseId="+id);
        if (courseEntities.size()!=0){
            userDao.executeHql("update  ChapterEntity as t set t.status=-1 where t.courseId="+id);
        }

    }

    @Override
    public void deletechapter(int id){
        userDao.executeHql("update  ChapterEntity as t set t.status=-1 where t.id="+id);
    }
}
