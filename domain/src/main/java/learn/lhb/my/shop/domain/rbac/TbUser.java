package learn.lhb.my.shop.domain.rbac;

import learn.lhb.my.shop.commons.persistence.BaseDateEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 17:26
 */
public class TbUser extends BaseDateEntity {

    private static final long serialVersionUID = 4527222499603997417L;

    /**
     * 用户表ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户手机
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
