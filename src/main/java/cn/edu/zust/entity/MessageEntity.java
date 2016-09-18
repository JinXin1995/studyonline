package cn.edu.zust.entity;

import javax.persistence.*;

/**
 * Created by King on 2016/8/18 0018.
 */
@Entity
@Table(name = "message", schema = "studyonline", catalog = "")
public class MessageEntity {
    private int id;
    private int senderId;
    private int reciverId;
    private String content;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sender_id")
    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int sender_id) {
        this.senderId = sender_id;
    }

    @Basic
    @Column(name = "reciver_id")
    public int getReciverId() {
        return reciverId;
    }

    public void setReciverId(int reciver_id) {
        this.reciverId = reciver_id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (id != that.id) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
