package learn.lhb.my.shop.backend.service;

import learn.lhb.my.shop.commons.dto.BaseResult;
import learn.lhb.my.shop.domain.rbac.TbUser;

/**
 *
 *
 * @author 梁鸿斌
 * @date 2020/3/15.
 * @time 11:08
 */
public interface TbUserService {

    /**
     * 添加用户
     *
     * @param tbUser
     * @return
     */
    BaseResult insert(TbUser tbUser);

    /**
     * 修改用户
     * @param tbUser
     * @param user_id
     * @return
     */
    BaseResult update(TbUser tbUser,String user_id);

    /**
     * 删除用户
     * @param user_id
     * @return
     */
    int delete(String user_id);

    /**
     * 批量删除
     * @param userIds
     * @return
     */
    void deleteMulti(String[] userIds);
}
