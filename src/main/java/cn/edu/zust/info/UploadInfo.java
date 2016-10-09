package cn.edu.zust.info;

import java.io.File;

/**
 * Created by King on 2016/9/26 0026.
 */
public class UploadInfo {
    /**
     * 所有文件的根路径
     */
    public final static String BASE_PATH = System.getProperty("studyonline.root") + "/upload/";

    /**
     * 图片根路径
     */
    public final static String PIC_PATH = "pic/";

    /**
     * 头像根路径
     */
    public final static String DP_PATH = "dp/";

    /**
     * 视频根路径
     */
    public final static String VIDEO_PATH = "video/";

    /**
     * 允许上传的图片类型
     */
    public final static String[] ALLOW_PIC_TYPE = {".jpg", ".jpeg", ".bmp", ".gif", ".png"};

    /**
     * 允许上传的视频类型
     */
    public final static String[] ALLOW_VIDEO_TYPE = {".mp4"};
}
