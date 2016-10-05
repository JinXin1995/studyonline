package cn.edu.zust.model;

import java.util.Date;

/**
 * Created by King on 2016/9/19 0019.
 */
public class Course {
    private Integer id;
    private String name;
    private Double star;
    private Short difficulty;
    private String introduction;
    private Date updateTime;
    private Date createTime;
    private Integer chapterNum;
    private String coverPic;
    private Short status;
    private Type type;
    private Integer typeId;
    private Integer teacherId;
    private Integer chapter;//总的章节数
    private String statuszhuangtai;//前端显示状态
    private String teacher;//讲师用户名
    private Integer study;//学习人数
    private String typename;//前端显示类型

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getStudy() {
        return study;
    }

    public void setStudy(Integer study) {
        this.study = study;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getChapter() {
        return chapter;
    }

    public String getStatuszhuangtai() {
        return statuszhuangtai;
    }

    public void setChapter(Integer chapter) {
        this.chapter = chapter;
    }

    public void setStatuszhuangtai(String statuszhuangtai) {
        this.statuszhuangtai = statuszhuangtai;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public Short getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Short difficulty) {
        this.difficulty = difficulty;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(Integer chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
