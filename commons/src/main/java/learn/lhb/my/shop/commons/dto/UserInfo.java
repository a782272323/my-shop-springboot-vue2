package learn.lhb.my.shop.commons.dto;

import learn.lhb.my.shop.commons.persistence.BaseDateEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 和前端交互的数据传输对象
 * 领域模型
 *
 * @author 梁鸿斌
 * @date 2020/3/15.
 * @time 15:45
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 9177375817500135379L;

    /**
     * 新用户名
     */
    private String newUsername;
    /**
     * 旧用户名
     */
    private String username;
    private String email;
    private String phone;
    private Date updated;

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
