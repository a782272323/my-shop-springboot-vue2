package learn.lhb.my.shop.domain.rbac;

import learn.lhb.my.shop.commons.persistence.BaseDateEntity;

/**
 * 权限表
 *
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 17:35
 */
public class TbPermission extends BaseDateEntity {


    private static final long serialVersionUID = -24289L;

    /**
     * 权限ID
     */
    private Long permissionId;
    /**
     * 权限标识符
     */
    private String code;
    /**
     * 描述
     */
    private String description;
    /**
     * 请求地址
     */
    private String url;

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
