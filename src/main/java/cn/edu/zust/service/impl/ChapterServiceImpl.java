package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.ChapterEntity;
import cn.edu.zust.model.Chapter;
import cn.edu.zust.service.ChapterServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by King on 2016/9/19 0019.
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterServiceI {
    @Autowired
    BaseDaoI<ChapterEntity> chapterDao;

    @Override
    public void save(Chapter chapter) {
        if(chapter.getId() == null) {
            chapter.setId(0);
        }
        chapter.setStatus((byte)0);
        if (chapter.getNumber() == null || chapter.getNumber() == 0) {  //分配章节号
            chapter.setNumber(getChapterNum(chapter.getCourseId()) + 1);
        } else if(chapter.getSubNum() == null || chapter.getSubNum() == 0) {    //分配小节号
            chapter.setSubNum(getSubChapterNum(chapter.getCourseId(), chapter.getNumber()) + 1);
        }
        chapterDao.saveOrUpdate(toEntity(chapter));
    }

    @Override
    public List<Chapter> getChapters(Integer courseId) {
        String hql = "from ChapterEntity where courseId=:courseId and subNum=0 order by number";    //获取章节
        Map<String, Object> value = new HashMap<>();
        value.put("courseId", courseId);
        List<ChapterEntity> chapterEntities = chapterDao.find(hql, value);
        List<Chapter> chapters = new ArrayList<>();
        for (ChapterEntity entity : chapterEntities) {
            Chapter chapter = toModel(entity);
            String hql2 = "from ChapterEntity where courseId=:courseId and number=:number and subNum!=0 order by subNum";    //获取章节下的小节
            Map<String, Object> value2 = new HashMap<>();
            value2.put("courseId", courseId);
            value2.put("number", chapter.getNumber());
            List<ChapterEntity> chapterEntities2 = chapterDao.find(hql2, value2);
            List<Chapter> chapters2 = new ArrayList<>();
            for(ChapterEntity entity2 : chapterEntities2) {
                Chapter chapter2 = toModel(entity2);
                chapters2.add(chapter2);
            }
            chapter.setSubchapter(chapters2);
            chapters.add(chapter);
        }
        return chapters;
    }

    @Override
    public void delete(int id) {
        ChapterEntity entity = chapterDao.get(ChapterEntity.class, id);
        int number = entity.getNumber();
        if(entity.getSubNum() == 0) {   //章节的删除
            String delete = "delete from chapter where number = :num";
            String update = "update chapter set number=number-1 where number>:num";
            Map<String, Object> value = new HashMap<>();
            value.put("num", number);
            chapterDao.executeSql(delete, value);
            chapterDao.executeSql(update, value);
        } else {
            int subNum = entity.getSubNum();
            chapterDao.delete(entity);
            String update = "update chapter set sub_num=sub_num-1 where number=:num and sub_num>:sub";
            Map<String, Object> value = new HashMap<>();
            value.put("num", number);
            value.put("sub", subNum);
            chapterDao.executeSql(update, value);
        }
    }

    @Override
    public Integer getCourseId(Integer chapterId) {
        ChapterEntity chapterEntity = chapterDao.get(ChapterEntity.class, chapterId);
        return chapterEntity.getCourseId();
    }

    @Override
    public Integer getChapterNum(Integer courseId) {
        String hql = "from ChapterEntity where courseId=:courseId and subNum=0";    //获取章节
        Map<String, Object> value = new HashMap<>();
        value.put("courseId", courseId);
        List<ChapterEntity> chapterEntities = chapterDao.find(hql, value);
        return chapterEntities.size();
    }

    @Override
    public Integer getSubChapterNum(Integer courseId, Integer number) {
        String hql = "from ChapterEntity where courseId=:courseId and number=:number and subNum!=0";    //获取章节
        Map<String, Object> value = new HashMap<>();
        value.put("courseId", courseId);
        value.put("number", number);
        List<ChapterEntity> chapterEntities = chapterDao.find(hql, value);
        return chapterEntities.size();
    }

    @Override
    public Chapter get(Integer chapterId) {
        return toModel(chapterDao.get(ChapterEntity.class, chapterId));
    }

    private ChapterEntity toEntity(Chapter model) {
        ChapterEntity entity;
        if(model.getId() == null || model.getId() == 0) {
            entity = new ChapterEntity();
        } else {
            entity = chapterDao.get(ChapterEntity.class, model.getId());
        }
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    private Chapter toModel(ChapterEntity entity) {
        Chapter model = new Chapter();
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}
