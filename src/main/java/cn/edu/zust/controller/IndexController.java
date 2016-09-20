package cn.edu.zust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by King on 2016/9/19 0019.
 */
@Controller
@RequestMapping("")
public class IndexController extends BaseController {
    @RequestMapping("index")
    public String indexPage() {
        return "index";
    }

    @RequestMapping("")
    public String indexPage1() {
        return indexPage();
    }
}
