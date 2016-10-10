package cn.edu.zust.controller;

import cn.edu.zust.info.UserType;
import cn.edu.zust.model.JsonResult;
import cn.edu.zust.model.User;
import cn.edu.zust.service.UserInfoServiceI;
import cn.edu.zust.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by King on 2016/9/19 0019.
 */
@Controller
@RequestMapping("")
public class LogController extends BaseController {
    @Autowired
    UserServiceI userService;
    @Autowired
    UserInfoServiceI userInfoService;

    @RequestMapping(value="login",method=RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping("register")
    @ResponseBody
    public JsonResult register(User user, String pwdcheck, HttpSession session) {
        if(user.getType() == null || user.getType() < 4) {
            user.setType(UserType.STUDENT);
        } else {
            user.setType(UserType.UNPASS_TEACHER);
        }
        JsonResult result = userService.register(user, pwdcheck);
        return result;
    }

    @RequestMapping(value="login",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult login(User user, HttpSession session) {
        JsonResult result =  userService.login(user);
        if(result.getSuccess()) {
            session.setAttribute("user", result.getData());
        }
        return result;
    }

    @RequestMapping("/home")
    public String homePage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user.getType() == UserType.STUDENT) {
            return "redirect:study/home.html";
        } else if(user.getType() == UserType.TEACHER) {
            return "redirect:teacher/home.html";
        } else if(user.getType() == UserType.UNPASS_TEACHER) {
            return "redirect:teacher/apply.html";
        } else {
            return "redirect:login.html";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:index.html";
    }
}
