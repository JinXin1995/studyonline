package cn.edu.zust.controller;

import cn.edu.zust.model.Course;
import cn.edu.zust.model.JsonResult;
import cn.edu.zust.model.User;
import cn.edu.zust.model.UserInfo;
import cn.edu.zust.service.CourseServiceI;
import cn.edu.zust.service.TypeServiceI;
import cn.edu.zust.service.UserInfoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "addCourse", method = RequestMethod.GET)
    public ModelAndView addCoursePage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("teacher/add-course");
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
}
