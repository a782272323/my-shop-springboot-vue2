package learn.lhb.my.shop.backend;

import com.github.pagehelper.PageHelper;
import learn.lhb.my.shop.backend.mapper.LimitMapper;
import learn.lhb.my.shop.backend.mapper.TbUserMapper;
import learn.lhb.my.shop.backend.service.LimitService;
import learn.lhb.my.shop.commons.dto.BaseResult;
import learn.lhb.my.shop.commons.dto.PageParams;
import learn.lhb.my.shop.domain.rbac.TbPermission;
import learn.lhb.my.shop.domain.rbac.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        PageParams pageParams = new PageParams();
        pageParams.setPageIndex(1);
        pageParams.setPageSize(2);
        pageParams.setSortName("user_id");
        pageParams.setSortType("DESC");
        PageHelper.startPage(1, 2);
        List<Map<String,TbUser>> tbUsers = tbUserMapper.selectAll(pageParams);

        System.out.println(tbUsers);
    }

    /**
     * 测试用户名，邮箱，手机号码是否存在
     */
    @Test
    public void checkIsNullTest() {
        System.out.println("测试存在的，也就是说重复了");
        System.out.println("用户名 = "+tbUserMapper.findUsername("admin"));
        System.out.println("邮箱 = "+tbUserMapper.findEmail("123@qq.com"));
        System.out.println("手机号码 = "+tbUserMapper.findPhone("13724725169"));
        System.out.println("测试不存在的，也就是说不重复");
        System.out.println("用户名 = "+tbUserMapper.findUsername("123"));
        System.out.println("邮箱 = "+tbUserMapper.findEmail("admin"));
        System.out.println("手机号码 = "+tbUserMapper.findPhone("admin"));
    }

    /**
     * 测试创建本地时间
     */
    @Test
    public void dateTest() {
        System.out.println(new Date());
        BaseResult baseResult = BaseResult.ok();
        baseResult.setCode(20);
        baseResult.setMessage("成功了");
        System.out.println(baseResult.getCode());
        System.out.println(baseResult.getMessage());
    }

}
