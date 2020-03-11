package learn.lhb.my.shop.domain.rbac;

import learn.lhb.my.shop.commons.persistence.BaseDateEntity;

import java.io.Serializable;

/**
 * 角色权限表
 *
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 17:38
 */
public class TbRolePermission implements Serializable {


    private static final long serialVersionUID = -102L;

    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 权限ID
     */
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
