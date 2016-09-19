package cn.edu.zust.model;

/**
 * Created by King on 2016/9/19 0019.
 */
public class Message {
    private Integer id;
    private User sender;
    private Integer senderId;
    private User reciver;
    private Integer reciverId;
    private String content;

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReciverId() {
        return reciverId;
    }

    public void setReciverId(Integer reciverId) {
        this.reciverId = reciverId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciver() {
        return reciver;
    }

    public void setReciver(User reciver) {
        this.reciver = reciver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
