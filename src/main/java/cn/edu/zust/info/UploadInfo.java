package cn.edu.zust.info;

import java.io.File;

/**
 * Created by King on 2016/9/26 0026.
 */
public class UploadInfo {
    public final static String BASE_PATH = System.getProperty("studyonline.root") + "/upload/";
    public final static String PIC_PATH = "pic/";

    /**
     * 允许上传的图片类型
     */
    public final static String[] ALLOW_PIC_TYPE = {".jpg", ".jpeg", ".bmp", ".gif", ".png"};
}
