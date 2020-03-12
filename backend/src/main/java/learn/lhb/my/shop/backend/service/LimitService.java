package learn.lhb.my.shop.backend.service;

import learn.lhb.my.shop.domain.rbac.TbPermission;
import learn.lhb.my.shop.domain.rbac.TbUser;

import java.util.List;

/**
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 18:17
 */
public interface LimitService {

//    /**
//     * 根据用户名获取用户拥有的权限
//     * @param username
//     * @return
//     */
//    List<String> getUrls(String username);

    /**
     * 根据用户名从数据库获取用户名和密码
     * @param username
     * @return
     */
    TbUser findUsernameAndPassword(String username);
}
