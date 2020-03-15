package learn.lhb.my.shop.backend.mapper;

import learn.lhb.my.shop.commons.dto.PageParams;
import learn.lhb.my.shop.domain.rbac.TbUser;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 梁鸿斌
 * @date 2020/3/12.
 * @time 13:11
 */
@Repository
@MapperScan
public interface TbUserMapper {

    /**
     * 查询全部用户信息
     * @param pageParams
     * @return
     */
    List<Map<String,TbUser>> selectAll(PageParams pageParams);

    /**
     * 查询全部用户信息的返回结果数量
     * @return
     */
    int getTotalAll();

    /**
     * 根据条件查询全部用户信息
     * @param pageParams
     * @param tbUser
     * @return
     */
    List<Map<String, TbUser>> queryAll(@Param("pageParams")PageParams pageParams,@Param("tbUser") TbUser tbUser);

    /**
     * 根据条件查询全部用户信息的返回结果数量
     * @param tbUser
     * @return
     */
    Integer queryTotalAll(@Param("tbUser") TbUser tbUser);

    /**
     * 根据ID查询单个用户信息
     *
     * @param userId
     * @return
     */
    TbUser selectOne(String userId);

    /**
     * 添加用户
     * @param tbUser
     * @return
     */
    int insert(@Param("tbUser")TbUser tbUser);

    /**
     * 修改用户
     * @param tbUser
     * @param user_id
     * @return
     */
    int update(@Param("tbUser") TbUser tbUser,String user_id);

    /**
     * 删除用户
     * @param user_id
     * @return
     */
    int delete(String user_id);

    /**
     * 校验用户名是否重复
     * @param username
     * @return
     */
    TbUser findUsername(String username);

    /**
     * 校验邮箱是否重复
     * @param email
     * @return
     */
    TbUser findEmail(String email);

    /**
     * 校验电话号码是否重复
     * @param phone
     * @return
     */
    TbUser findPhone(String phone);


}
