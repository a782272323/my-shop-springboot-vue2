package learn.lhb.my.shop.backend.mapper;

import learn.lhb.my.shop.domain.rbac.TbPermission;
import learn.lhb.my.shop.domain.rbac.TbRole;
import learn.lhb.my.shop.domain.rbac.TbUser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 设计到RBAC的操作都在这里
 *
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 17:43
 */
@Repository
@MapperScan
public interface LimitMapper {

//    /**
//     * 根据角色名，查询角色ID
//     * @param roleName
//     * @return
//     */
//    TbRole findRoleIdByRoleName(String roleName);

    /**
     * 根据用户名查询用户对应的角色名
     * @param username
     * @return
     */
    String findRoleNameByUsername(String username);

    /**
     * 根据角色名查询角色拥有的权限
     * @param roleName
     * @return
     */
//    List<Map<String,Object>> getUrlsByRoleName(String roleName);
    List<TbPermission> getUrlsByRoleName(String roleName);
}
