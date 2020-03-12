package learn.lhb.my.shop.backend.service.impl;

import learn.lhb.my.shop.backend.mapper.LimitMapper;
import learn.lhb.my.shop.backend.service.LimitService;
import learn.lhb.my.shop.domain.rbac.TbPermission;
import learn.lhb.my.shop.domain.rbac.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 18:19
 */
@Service
public class LimitServiceImpl implements LimitService {

    @Autowired
    private LimitMapper limitMapper;

    /**
     * 根据用户名从数据库获取用户名和密码
     * @param username
     * @return
     */
    @Override
    public TbUser findUsernameAndPassword(String username) {
        return limitMapper.findUsernameAndPassword(username);
    }

//    /**
//     * 根据用户名获取用户拥有的权限
//     * @param username
//     * @return
//     */
//    @Override
//    public List<String> getUrls(String username) {
//        // 根据用户名获取用户的角色名
//        String roleName = limitMapper.findRoleNameByUsername(username);
//        // 根据角色名获取权限，并转化为数组
//        List<TbPermission> tbPermissions = limitMapper.getUrlsByRoleName(roleName);
//        String[] strings = new String[tbPermissions.size()];
//        for (int i=0;i<tbPermissions.size();i++) {
//            strings[i] = tbPermissions.get(i).getUrl();
//        }
//        return Arrays.asList(strings);
////        return null;
//    }
}
