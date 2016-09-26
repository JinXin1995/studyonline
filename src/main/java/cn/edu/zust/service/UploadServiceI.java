package cn.edu.zust.service;

import cn.edu.zust.model.JsonResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by King on 2016/9/26 0026.
 */
public interface UploadServiceI {
    /**
     * 上传图片到服务器
     * @param file
     * @return
     */
    JsonResult uploadPic(MultipartFile file);
}
