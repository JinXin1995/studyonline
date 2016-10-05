package cn.edu.zust.controller;

import cn.edu.zust.model.Chapter;
import cn.edu.zust.model.Course;
import cn.edu.zust.model.User;
import cn.edu.zust.model.UserInfo;
import cn.edu.zust.service.AdministratorI;
import cn.edu.zust.service.CourseServiceI;
import cn.edu.zust.service.TypeServiceI;
import cn.edu.zust.service.UserInfoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2016/10/2 0002.
 */
@Controller
@RequestMapping("/administrator")
public class AdministratorController extends BaseController {

    @Autowired
    private AdministratorI administrator;

    @RequestMapping(value = "/a-login",method=RequestMethod.GET)
    public String loginPage() {
        return "administrator/a-login";
    }

    @RequestMapping(value = "/a-test",method=RequestMethod.GET)
    public String test() {
        return "administrator/a-test";
    }

    @RequestMapping(value = "/a-index",method=RequestMethod.POST)
    public ModelAndView Alogin(String username, String password, HttpSession httpSession) {
        ModelAndView mav=new ModelAndView();
        List<User>list=administrator.alogin(username,password);
        if (list!=null){
            List<UserInfo> userInfos=administrator.students();
            int i=0;
            if(userInfos==null){
                i=0;
            }else{
                i=userInfos.size();
                int k=i%5;
                if(k==0){
                    i=i/5;
                }else{
                    i=i/5+1;
                }
            }
            int pages[]=new int[i];
            if(pages.length==1){
                pages[0]=1;
            }else {
                int s = 0;
                while (s < i) {
                    pages[s] = s + 1;
                    s = s + 1;
                }
            }
            int page=0;
            if(userInfos==null){
                page=0;
            }else{
                page=1;
            }
            List<UserInfo> students=administrator.userpage(page);
            mav.addObject("now",page);
            mav.addObject("pages",pages);
            mav.addObject("userinfos",students);
            mav.setViewName("administrator/a-index");
            return mav;

        }else {
            mav.setViewName("administrator/a-login");
            return mav;
        }
    }

    @RequestMapping(value = "/a-index",method=RequestMethod.GET)
    public ModelAndView Alogin2(HttpSession httpSession,int page,int leixing) {
        ModelAndView mav=new ModelAndView();
        List<UserInfo> userInfos=administrator.students();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        if(leixing==2){
            if(page<pages.length){
                page=page+1;
            }
        }else if(leixing==3){
            if(page>1){
                page=page-1;
            }
        }
        int page1=0;
        if(userInfos==null){
            page=0;
            page1=0;
        }else{
            page1=page;
            page=page-1;
        }
        List<UserInfo> students=administrator.userpage(page+1);
        mav.addObject("now",page1);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-index");
        return mav;
    }

    @RequestMapping(value = "/a-unicepage",method=RequestMethod.GET)
    public ModelAndView Auserdetail(HttpSession httpSession) {
        ModelAndView mav=new ModelAndView();
        List<UserInfo> userInfos=administrator.iceuser();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<UserInfo> students=administrator.icepage(page);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-unice");
        return mav;
    }

    @RequestMapping(value = "/a-ice")
    public ModelAndView  Aice(int id) {
        ModelAndView mav=new ModelAndView();
        administrator.ice(id);
        List<UserInfo> userInfos=administrator.students();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<UserInfo> students=administrator.userpage(page);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-index");
        return mav;

    }

    @RequestMapping(value = "/a-unice")
    public ModelAndView Aunice(HttpSession httpSession,int page,int leixing) {
        ModelAndView mav=new ModelAndView();
        List<UserInfo> userInfos=administrator.iceuser();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        if(leixing==2){
            if(page<pages.length){
                page=page+1;
            }
        }else if(leixing==3){
            if(page>1){
                page=page-1;
            }
        }
        int page1=0;
        if(userInfos==null){
            page=0;
            page1=0;
        }else{
            page1=page;
            page=page-1;
        }
        List<UserInfo> students=administrator.icepage(page+1);
        mav.addObject("now",page1);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-unice");
        return mav;
    }

    @RequestMapping(value = "/a-uniceuser")
    public ModelAndView  Auniceuser(int id) {
        ModelAndView mav=new ModelAndView();
        administrator.uniceuser(id);
        List<UserInfo> userInfos=administrator.iceuser();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<UserInfo> students=administrator.icepage(page);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-unice");
        return mav;
    }

    @RequestMapping(value = "/a-teacher")
    public ModelAndView ateacher(){
        ModelAndView mav=new ModelAndView();
        List<UserInfo> userInfos=administrator.teacher();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<UserInfo> students=administrator.teacherpage(page);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-teacher");
        return mav;
    }

    @RequestMapping(value = "/a-teacherpaper")
    public ModelAndView ateacherpaper(int page,int leixing){
        ModelAndView mav=new ModelAndView();
        List<UserInfo> userInfos=administrator.teacher();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        if(leixing==2){
            if(page<pages.length){
                page=page+1;
            }
        }else if(leixing==3){
            if(page>1){
                page=page-1;
            }
        }
        int page1=0;
        if(userInfos==null){
            page=0;
            page1=0;
        }else{
            page1=page;
            page=page-1;
        }
        List<UserInfo> students=administrator.teacherpage(page+1);
        mav.addObject("now",page1);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-teacher");
        return mav;
    }

    @RequestMapping(value = "/a-teacherpass")
    public ModelAndView ateacherpass(int pass,int id){
        if(pass==1){
            administrator.teacherpass(id);
        }else if(pass==0){
            administrator.teacherunpass(id);
        }
        ModelAndView mav=new ModelAndView();
        List<UserInfo> userInfos=administrator.teacher();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<UserInfo> students=administrator.teacherpage(page);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-teacher");
        return mav;

    }

    @RequestMapping(value = "/a-iceteacher")
    public ModelAndView ateacherice(int id){
        administrator.ice(id);
        ModelAndView mav=new ModelAndView();
        List<UserInfo> userInfos=administrator.teacher();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<UserInfo> students=administrator.teacherpage(page);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-teacher");
        return mav;

    }

    @RequestMapping(value = "/a-class")
    public ModelAndView  aclass(){
        ModelAndView mav=new ModelAndView();
        List<Course> userInfos=administrator.course();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<Course> students=administrator.coursepage(page);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-class");
        return mav;

    }

    @RequestMapping(value = "/a-classpage")
    public ModelAndView  aclasspage(int leixing ,int page){
        ModelAndView mav=new ModelAndView();
        List<Course> userInfos=administrator.course();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        if(leixing==2){
            if(page<pages.length){
                page=page+1;
            }
        }else if(leixing==3){
            if(page>1){
                page=page-1;
            }
        }
        int page1=0;
        if(userInfos==null){
            page=0;
            page1=0;
        }else{
            page1=page;
            page=page-1;
        }
        List<Course> students=administrator.coursepage(page+1);
        mav.addObject("now",page1);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-class");
        return mav;
    }
    @RequestMapping(value = "/a-classpass")
    public ModelAndView aclasspass(int pass,int id){
        if(pass==1){
            administrator.coursepass(id);
        }else if(pass==0){
            administrator.courseunpass(id);
        }
        ModelAndView mav=new ModelAndView();
        List<Course> userInfos=administrator.course();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<Course> students=administrator.coursepage(page);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-class");
        return mav;
    }
    @RequestMapping(value = "/a-chapter")
    public ModelAndView  achapter(int id){
        ModelAndView mav=new ModelAndView();
        List<Chapter> userInfos=administrator.chapter(id);
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<Chapter> students=administrator.chapterpage(page,id);
        String ke=administrator.classname(id);
        mav.addObject("classid",id);
        mav.addObject("ke",ke);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-chapter");
        return mav;

    }

    @RequestMapping(value = "/a-chapterpage")
    public ModelAndView  achapterpage(int leixing ,int page,int id){
        ModelAndView mav=new ModelAndView();
        List<Chapter> userInfos=administrator.chapter(id);
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        if(leixing==2){
            if(page<pages.length){
                page=page+1;
            }
        }else if(leixing==3){
            if(page>1){
                page=page-1;
            }
        }
        int page1=0;
        if(userInfos==null){
            page=0;
            page1=0;
        }else{
            page1=page;
            page=page-1;
        }
        List<Chapter> students=administrator.chapterpage(page+1,id);
        String ke=administrator.classname(id);
        mav.addObject("classid",id);
        mav.addObject("ke",ke);
        mav.addObject("now",page1);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-chapter");
        return mav;
    }

    @RequestMapping(value = "/a-chapterpass")
    public ModelAndView  achapterpass(int cid,int pass,int id){
        if(pass==1){
            administrator.chapterpass(id);
        }else if(pass==0){
            administrator.chapterunpass(id);
        }
        ModelAndView mav=new ModelAndView();
        List<Chapter> userInfos=administrator.chapter(cid);
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<Chapter> students=administrator.chapterpage(page,cid);
        String ke=administrator.classname(cid);
        mav.addObject("classid",cid);
        mav.addObject("ke",ke);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-chapter");
        return mav;

    }

    @RequestMapping(value = "/a-deuser")
    public ModelAndView  adeuser(int id) {
        ModelAndView mav = new ModelAndView();
        System.out.println(id);
        administrator.deleteuser(id);
        List<UserInfo> userInfos = administrator.students();
        int i = 0;
        if (userInfos == null) {
            i = 0;
        } else {
            i = userInfos.size();
            int k = i % 5;
            if (k == 0) {
                i = i / 5;
            } else {
                i = i / 5 + 1;
            }
        }
        int pages[] = new int[i];
        if (pages.length == 1) {
            pages[0] = 1;
        } else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page = 0;
        if (userInfos == null) {
            page = 0;
        } else {
            page = 1;
        }
        List<UserInfo> students = administrator.userpage(page);
        mav.addObject("now", page);
        mav.addObject("pages", pages);
        mav.addObject("userinfos", students);
        mav.setViewName("administrator/a-index");
        return mav;
    }

    @RequestMapping(value = "/a-deteacher")
    public ModelAndView ateacherde(int id){
        administrator.deleteuser(id);
        ModelAndView mav=new ModelAndView();
        List<UserInfo> userInfos=administrator.teacher();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<UserInfo> students=administrator.teacherpage(page);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-teacher");
        return mav;

    }
    @RequestMapping(value = "/a-decourse")
    public ModelAndView acoursede(int id){
        administrator.deletecourse(id);
        ModelAndView mav=new ModelAndView();
        List<Course> userInfos=administrator.course();
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<Course> students=administrator.coursepage(page);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-class");
        return mav;
    }

    @RequestMapping(value = "/a-dechapter")
    public ModelAndView acoursede(int id,int cid){
        administrator.deletechapter(id);
        ModelAndView mav=new ModelAndView();
        List<Chapter> userInfos=administrator.chapter(cid);
        int i=0;
        if(userInfos==null){
            i=0;
        }else{
            i=userInfos.size();
            int k=i%5;
            if(k==0){
                i=i/5;
            }else{
                i=i/5+1;
            }
        }
        int pages[]=new int[i];
        if(pages.length==1){
            pages[0]=1;
        }else {
            int s = 0;
            while (s < i) {
                pages[s] = s + 1;
                s = s + 1;
            }
        }
        int page=0;
        if(userInfos==null){
            page=0;
        }else{
            page=1;
        }
        List<Chapter> students=administrator.chapterpage(page,cid);
        String ke=administrator.classname(cid);
        mav.addObject("classid",cid);
        mav.addObject("ke",ke);
        mav.addObject("now",page);
        mav.addObject("pages",pages);
        mav.addObject("userinfos",students);
        mav.setViewName("administrator/a-chapter");
        return mav;
    }

    @RequestMapping(value = "/a-loginout",method=RequestMethod.GET)
    public String loginoutPage() {
        return "administrator/a-login";
    }
}
