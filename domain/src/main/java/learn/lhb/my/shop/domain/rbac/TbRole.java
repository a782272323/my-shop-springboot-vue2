package learn.lhb.my.shop.domain.rbac;

import learn.lhb.my.shop.commons.persistence.BaseDateEntity;

/**
 * 角色表
 *
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 17:34
 */
public class TbRole extends BaseDateEntity {

    private static final long serialVersionUID = -77L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名字
     */
    private String roleName;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     */
    private String status;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
