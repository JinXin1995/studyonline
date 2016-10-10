package cn.edu.zust.controller;

import cn.edu.zust.service.TypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by King on 2016/9/19 0019.
 */
@Controller
@RequestMapping("")
public class IndexController extends BaseController {
    @Autowired
    TypeServiceI typeService;
    @RequestMapping("/index")
    public ModelAndView indexPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("types", typeService.getTypes());
        return mav;
    }
}
