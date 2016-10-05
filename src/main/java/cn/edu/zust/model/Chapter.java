package cn.edu.zust.model;

/**
 * Created by King on 2016/9/19 0019.
 */
public class Chapter {
    private Integer id;
    private Integer number;
    private String name;
    private String content;
    private String videoPath;
    private Byte status;
    private Course course;
    private Integer courseId;
    private String stauszhuangtai;//前端显示状态
    private Integer study;//学习人数

    public void setStudy(Integer study) {
        this.study = study;
    }

    public Integer getStudy() {
        return study;
    }
    public String getStauszhuangtai() {
        return stauszhuangtai;
    }

    public void setStauszhuangtai(String stauszhuangtai) {
        this.stauszhuangtai = stauszhuangtai;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
