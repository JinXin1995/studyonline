package cn.edu.zust.controller;

import cn.edu.zust.model.Chapter;
import cn.edu.zust.model.JsonResult;
import cn.edu.zust.model.Progress;
import cn.edu.zust.model.User;
import cn.edu.zust.service.ChapterServiceI;
import cn.edu.zust.service.CourseServiceI;
import cn.edu.zust.service.ProgressServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by King on 2016/10/8 0008.
 */
@Controller
@RequestMapping("study")
public class StudentController extends BaseController {
    @Autowired
    CourseServiceI courseService;
    @Autowired
    ChapterServiceI chapterService;
    @Autowired
    ProgressServiceI progressService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("student/person-show");
        mav.addObject("progresses", progressService.getProgressesByUser(user.getId()));
        return mav;
    }

    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public ModelAndView courseContent(Integer id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("student/course-content");
        mav.addObject("course", courseService.get(id));
        mav.addObject("chapters", chapterService.getChapters(id));
        return mav;
    }

    @RequestMapping(value = "learning", method = RequestMethod.GET)
    public ModelAndView learning(Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Progress progress = progressService.getByCourse(chapterService.getCourseId(id), user.getId());
        progress.setChapterId(id);
        progressService.save(progress);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("student/course-learn-on");
        Chapter currentChapter = chapterService.get(id);
        mav.addObject("current", currentChapter);
        mav.addObject("chapters", chapterService.getChapters(currentChapter.getCourseId()));
        mav.addObject("course", courseService.get(currentChapter.getCourseId()));
        return mav;
    }

    @RequestMapping(value = "/deleteProgress", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult deleteProgress(Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return progressService.delete(user.getId(), id);
    }
}
