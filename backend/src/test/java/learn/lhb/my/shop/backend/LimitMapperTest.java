package learn.lhb.my.shop.backend;

import learn.lhb.my.shop.backend.mapper.LimitMapper;
import learn.lhb.my.shop.backend.mapper.TbUserMapper;
import learn.lhb.my.shop.backend.service.LimitService;
import learn.lhb.my.shop.commons.dto.BaseResult;
import learn.lhb.my.shop.domain.rbac.TbPermission;
import learn.lhb.my.shop.domain.rbac.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 17:44
 */
@SpringBootTest("BackendApplication.class")
@RunWith(SpringRunner.class)
public class LimitMapperTest {

    @Autowired
    private LimitMapper limitMapper;

    @Resource
    private LimitService limitService;

    @Autowired
    private TbUserMapper tbUserMapper;

//    /**
//     * 根据角色名，查询角色ID
//     */
//    @Test
//    public void findRoleIdByRoleNameTest() {
//        System.out.println(limitMapper.findRoleIdByRoleName("普通用户"));
//    }

    /**
     * 根据用户名查询用户对应的角色名
     */
    @Test
    public void findRoleNameByUsernameTest() {
        String username = "admin";
        String roleName = limitMapper.findRoleNameByUsername(username);
        System.out.println(roleName);
    }

    /**
     * 根据角色名查询角色拥有的权限
     */
    @Test
    public void getUrlsByRoleNameTest() {
        List<TbPermission> tbPermissions = limitMapper.getUrlsByRoleName(limitMapper.findRoleNameByUsername("admin"));
//        for (TbPermission tbPermissions : tbPermission) {
//            System.out.println(tbPermissions.getUrl());
//        }
        String[] strings = new String[tbPermissions.size()];
        for (int i=0;i<tbPermissions.size();i++) {
            strings[i] = tbPermissions.get(i).getUrl();
        }
        System.out.println(Arrays.asList(strings));

    }

    /**
     * 查看用密码编译器加密的密码
     */
    @Test
    public void findUsernameAndPasswordTest() {
        TbUser tbUser = limitService.findUsernameAndPassword("admin");
        System.out.println("用户名为： "+tbUser.getUsername()+"，加密的密码为: "+tbUser.getPassword());

    }

    @Test
    public void selectAllTest() {
//        System.out.println(BaseResult.ok().put(20000,"请求成功","data",tbUserMapper.selectAll()));
        List<TbUser> tbUsers = tbUserMapper.selectAll();
        for (TbUser tbUser : tbUsers) {
            System.out.println("----------------");
            System.out.println(tbUser.getUserId());
            System.out.println(tbUser.getUsername());
            System.out.println(tbUser.getEmail());
            System.out.println(tbUser.getPhone());
            System.out.println(tbUser.getCreated());
            System.out.println(tbUser.getUpdated());
        }
    }

}
