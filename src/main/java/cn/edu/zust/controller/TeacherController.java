package cn.edu.zust.controller;

import cn.edu.zust.model.*;
import cn.edu.zust.service.ChapterServiceI;
import cn.edu.zust.service.CourseServiceI;
import cn.edu.zust.service.TypeServiceI;
import cn.edu.zust.service.UserInfoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by King on 2016/9/26 0026.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {
    @Autowired
    private UserInfoServiceI userInfoService;
    @Autowired
    private TypeServiceI typeService;
    @Autowired
    private CourseServiceI courseService;
    @Autowired
    private ChapterServiceI chapterService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("teacher/home");
        mav.addObject("courses", courseService.getByTeacher(user.getId()));
        return mav;
    }

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    public String applyPage() {
        return "teacher/apply";
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult apply(UserInfo userInfo, HttpSession session) {
        userInfoService.saveOrUpdate(userInfo);
        session.setAttribute("userInfo", userInfoService.getByUser(userInfo.getUserId()));
        return new JsonResult(true, "保存成功");
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public ModelAndView addCoursePage(@RequestParam(defaultValue = "0") Integer courseId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("teacher/add-course");
        mav.addObject("course", courseService.get(courseId));
        mav.addObject("types", typeService.getTypes());
        return mav;
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult addCourse(Course course, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        course.setTeacherId(currentUser.getId());
        courseService.save(course);
        return new JsonResult(true, "保存成功");
    }

    @RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteCourse(Integer courseId, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        return courseService.delete(courseId, currentUser.getId());
    }

    @RequestMapping(value = "/addChapter", method = RequestMethod.GET)
    public ModelAndView addChapterPage(@RequestParam Integer courseId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("teacher/add-chapter");
        mav.addObject("course", courseService.get(courseId));
        mav.addObject("chapters", chapterService.getChapters(courseId));
        return mav;
    }

    @RequestMapping(value = "/saveChapter", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult saveChapter(Chapter chapter) {
        chapterService.save(chapter);
        return new JsonResult(true, "保存成功");
    }

    @RequestMapping(value = "/deleteChapter", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteChapter(int id) {
        chapterService.delete(id);
        return new JsonResult(true, "删除成功");
    }
}
