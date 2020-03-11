package learn.lhb.my.shop.domain.rbac;

import learn.lhb.my.shop.commons.persistence.BaseDateEntity;

/**
 * 用户角色表
 *
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 17:39
 */
public class TbUserRole extends BaseDateEntity {


    private static final long serialVersionUID = -1752479L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;
}
