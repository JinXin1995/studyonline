package cn.edu.zust.controller;

import cn.edu.zust.model.JsonResult;
import cn.edu.zust.model.User;
import cn.edu.zust.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by King on 2016/10/9 0009.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceI userService;

    @RequestMapping("/set")
    public ModelAndView set() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/person-set");
        return mav;
    }

    @RequestMapping(value = "updateInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updateInfo(String nickname, String intro, HttpSession session) {
        if(nickname == null || "".equals(nickname)){
            return new JsonResult(false, "昵称不能为空！");
        }
        User user = (User) session.getAttribute("user");
        userService.updateInfo(user.getId(), nickname, intro);
        user.setNickname(nickname);
        user.setIntro(intro);
        session.setAttribute("user", user);
        return new JsonResult(true,"更新成功");
    }

    @RequestMapping(value = "updatePWD", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updatePWD(String old, String newPWD, String repeat, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return userService.updatePWD(user.getId(), old, newPWD, repeat);
    }
}
