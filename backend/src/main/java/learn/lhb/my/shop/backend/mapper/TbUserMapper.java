package learn.lhb.my.shop.backend.mapper;

import learn.lhb.my.shop.commons.dto.BaseResult;
import learn.lhb.my.shop.commons.dto.PageParams;
import learn.lhb.my.shop.commons.persistence.BaseMapper;
import learn.lhb.my.shop.domain.rbac.TbUser;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author 梁鸿斌
 * @date 2020/3/12.
 * @time 13:11
 */
@Repository
@MapperScan
public interface TbUserMapper extends BaseMapper<TbUser> {

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

    /**
     * 修改时，校验用户名除了修改之前的用户名外，其余是否重复
     *
     * @param username
     * @param userId
     * @return
     */
    TbUser findUsernameNotId(String username,String userId);

    /**
     * 修改时，校验用户名除了修改之前的手机号码外，其余是否重复
     * @param phone
     * @param userId
     * @return
     */
    TbUser findPhoneNotId(String phone,String userId);

    /**
     * 修改时，校验用户名除了修改之前的邮箱外，其余是否重复
     * @param email
     * @param userId
     * @return
     */
    TbUser findEmailNotId(String email,String userId);

}
