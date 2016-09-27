package cn.edu.zust.service.impl;

import cn.edu.zust.info.UploadInfo;
import cn.edu.zust.model.JsonResult;
import cn.edu.zust.service.UploadServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by King on 2016/9/26 0026.
 */
@Service
@Transactional
public class UploadServiceImpl implements UploadServiceI {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public JsonResult uploadPic(MultipartFile file) {
        return upload(file, UploadInfo.PIC_PATH, UploadInfo.ALLOW_PIC_TYPE);
    }

    /**
     * 获取文件扩展名
     *
     * @return string
     */
    protected String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 依据原始文件名生成新文件名
     *
     * @return 不保留原名称
     */
    protected String getName(String fileName) {
        Random random = new Random();
        fileName = "" + random.nextInt(10000) + System.currentTimeMillis() + this.getFileExt(fileName);
        return fileName;
    }

    protected JsonResult upload(MultipartFile file, String subpath, String[] allowType) {
        if(!checkFileType(file.getOriginalFilename(), allowType)) {
            return new JsonResult(false, "该文件类型不不允许上传");
        }
        String pathStr = UploadInfo.BASE_PATH + subpath;
        File path = new File(pathStr);
        if(!path.exists()) {
            path.mkdirs();
        }

        //创建文件名
        String picStr;
        File pic;
        for(;;) {
            picStr = getName(file.getOriginalFilename());
            pic = new File(pathStr + picStr);
            if(!pic.exists()) {
                break;
            }
        }

        //保存文件
        try {
            file.transferTo(pic);
            logger.info("upload根目录为：" + UploadInfo.BASE_PATH);
            logger.info("成功保存文件，文件路径为：" + pic.getAbsolutePath());
        } catch (IllegalStateException e) {
            logger.error("文件上传出错， 文件名：" + picStr);
            e.printStackTrace();
            return new JsonResult(false, "文件上传出错，请重试");
        } catch (IOException e) {
            logger.error("文件上传出错， 文件名：" + picStr);
            e.printStackTrace();
            return new JsonResult(false, "文件上传出错，请重试");
        }

        JsonResult result = new JsonResult(true, "上传成功！", subpath + picStr);
        return result;
    }

    /**
     * 文件类型判断
     *
     * @param fileName
     * @return
     */
    protected boolean checkFileType(String fileName, String[] allowFiles) {
        Iterator<String> type = Arrays.asList(allowFiles).iterator();
        while (type.hasNext()) {
            String ext = type.next();
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }
}
