package cn.edu.zust.entity;

import javax.persistence.*;

/**
 * Created by King on 2016/8/18 0018.
 */
@Entity
@Table(name = "user_info")
public class UserInfoEntity {
    private Integer id;
    private String realname;
    private String cardId;
    private String cardPic;
    private String email;
    private String phone;
    private String education;
    private String company;
    private Integer userId;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "realname")
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Basic
    @Column(name = "card_id")
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Basic
    @Column(name = "card_pic")
    public String getCardPic() {
        return cardPic;
    }

    public void setCardPic(String cardPic) {
        this.cardPic = cardPic;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Basic
    @Column(name = "company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfoEntity that = (UserInfoEntity) o;

        if (id != that.id) return false;
        if (realname != null ? !realname.equals(that.realname) : that.realname != null) return false;
        if (cardId != null ? !cardId.equals(that.cardId) : that.cardId != null) return false;
        if (cardPic != null ? !cardPic.equals(that.cardPic) : that.cardPic != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (education != null ? !education.equals(that.education) : that.education != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (realname != null ? realname.hashCode() : 0);
        result = 31 * result + (cardId != null ? cardId.hashCode() : 0);
        result = 31 * result + (cardPic != null ? cardPic.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }
}
