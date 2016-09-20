package cn.edu.zust.model;

import java.io.Serializable;

/**
 * Created by King on 2016/9/19 0019.
 */
public class JsonResult implements Serializable {
    private Boolean success = true;
    private String message;
    private Object data;

    public JsonResult(Boolean success) {
        this.success = success;
    }

    public JsonResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public JsonResult(Boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
