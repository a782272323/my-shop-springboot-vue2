package learn.lhb.my.shop.commons.dto;

import java.io.Serializable;

/**
 * 登录参数
 * 数据传输对象
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 10:55
 */
public class LoginInfo implements Serializable {

    private String name;
    private String avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
