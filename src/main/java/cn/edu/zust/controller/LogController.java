package cn.edu.zust.controller;

import cn.edu.zust.info.UserType;
import cn.edu.zust.model.JsonResult;
import cn.edu.zust.model.User;
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

    @RequestMapping(value="login",method=RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping("register")
    @ResponseBody
    public JsonResult register(User user, String pwdcheck) {
        if(user.getType() == null || user.getType() < 3) {
            user.setType(UserType.STUDENT);
        } else {
            user.setType(UserType.UNVERIFY_TEACHER);
        }
        return userService.register(user, pwdcheck);
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
}
