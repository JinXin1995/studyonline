package cn.edu.zust.controller;

import cn.edu.zust.model.JsonResult;
import cn.edu.zust.service.UploadServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by King on 2016/9/26 0026.
 */
@Controller
@RequestMapping("up")
public class UploadController extends BaseController {
    @Autowired
    private UploadServiceI uploadService;

    @RequestMapping(value = "pic", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadPic(@RequestParam MultipartFile file) {
        return uploadService.uploadPic(file);
    }

    @RequestMapping(value = "video", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadVideo(@RequestParam MultipartFile file) {
        return uploadService.uploadVideo(file);
    }
}
