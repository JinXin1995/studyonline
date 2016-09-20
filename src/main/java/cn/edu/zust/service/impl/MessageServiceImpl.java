package cn.edu.zust.service.impl;

import cn.edu.zust.dao.BaseDaoI;
import cn.edu.zust.entity.MessageEntity;
import cn.edu.zust.model.Message;
import cn.edu.zust.service.MessageServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by King on 2016/9/19 0019.
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageServiceI {
    @Autowired
    BaseDaoI<MessageEntity> messageDao;

    private MessageEntity toEntity(Message model) {
        MessageEntity entity;
        if(model.getId() == null) {
            entity = new MessageEntity();
        } else {
            entity = messageDao.get(MessageEntity.class, model.getId());
        }
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    private Message toModel(MessageEntity entity) {
        Message model = new Message();
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}
