package cn.edu.zust.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by King on 2016/8/18 0018.
 */
@Entity
@Table(name = "progress", schema = "studyonline", catalog = "")
public class ProgressEntity {
    private int id;
    private Timestamp updateTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgressEntity that = (ProgressEntity) o;

        if (id != that.id) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
