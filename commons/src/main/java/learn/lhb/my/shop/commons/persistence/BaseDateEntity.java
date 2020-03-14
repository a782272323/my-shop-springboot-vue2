package learn.lhb.my.shop.commons.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类
 * 创建时间和更新时间
 *
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 17:31
 */
public class BaseDateEntity implements Serializable {

    private static final long serialVersionUID = -52L;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-ss HH:mm:ss")
    private Date created;
    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-ss HH:mm:ss")
    private Date updated;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
