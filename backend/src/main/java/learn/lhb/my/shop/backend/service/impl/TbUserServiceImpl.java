package learn.lhb.my.shop.backend.service.impl;

import learn.lhb.my.shop.backend.mapper.TbUserMapper;
import learn.lhb.my.shop.backend.service.TbUserService;
import learn.lhb.my.shop.commons.dto.BaseResult;
import learn.lhb.my.shop.commons.utils.CheckUserInfoUtils;
import learn.lhb.my.shop.commons.utils.IsRegexpUtils;
import learn.lhb.my.shop.domain.rbac.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 梁鸿斌
 * @date 2020/3/15.
 * @time 11:08
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private TbUserMapper tbUserMapper;

    /**
     * 添加用户
     *
     * @param tbUser
     * @return
     */
    @Override
    public BaseResult insert(TbUser tbUser) {
        BaseResult baseResult = check1(tbUser);
        // 用户有效性验证
        if (baseResult.getCode() == BaseResult.CodeStatus.OK) {
            // 用户名重复
            if (tbUserMapper.findUsername(tbUser.getUsername()) != null) {
                baseResult = BaseResult.error("用户名重复");
            }
            // 手机号码重复
            else if (tbUserMapper.findPhone(tbUser.getPhone()) != null) {
                baseResult = BaseResult.error("手机号码重复");
            }
            // 邮箱重复
            else if (tbUserMapper.findEmail(tbUser.getEmail()) != null) {
                baseResult = BaseResult.error("邮箱重复");
            }
            // 添加用户
            else {
                // 初始化创建时间
                tbUser.setCreated(new Date());
                // 初始化更新时间
                tbUser.setUpdated(new Date());
                // 密码加密处理
                tbUser.setPassword(passwordEncoder.encode(tbUser.getPassword()));
                tbUserMapper.insert(tbUser);
                baseResult = BaseResult.ok("添加用户成功");
            }
        }
        return baseResult;
    }

    /**
     * 修改用户
     * @param tbUser
     * @return
     */
    @Override
    public BaseResult update(TbUser tbUser) {
        BaseResult baseResult = check2(tbUser);
        // 用户信息有效性验证
        if (baseResult.getCode() == BaseResult.CodeStatus.OK) {
            // 判断修改的用户名是不是相同的
            if (tbUser.getNewUsername().trim().equals(tbUser.getUsername()) || tbUser.getNewUsername().equals("")) {
                // 判断
            }

        }
        return null;
    }

    /**
     * 用户有效性验证
     * 用户名, 密码，邮箱，手机号码
     * @param tbUser
     * @return
     */
    private BaseResult check1(TbUser tbUser) {

        BaseResult baseResult = BaseResult.ok();
        baseResult.setCode(BaseResult.CodeStatus.OK);
        // 邮箱非空验证
        if (StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.error("邮箱不能为空，请重新输入!");
            baseResult.setCode(BaseResult.CodeStatus.ERROR);
        }
        // 邮箱格式验证
        else if (!IsRegexpUtils.checkEmail(tbUser.getEmail())) {
            baseResult = BaseResult.error("邮箱格式不正确，请重新输入!");
            baseResult.setCode(BaseResult.CodeStatus.ERROR);
        }
        // 手机号码非空验证
        else if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult = BaseResult.error("手机号码不能为空，请重新输入!");
            baseResult.setCode(BaseResult.CodeStatus.ERROR);
        }
        // 手机号码格式验证
        else if (!IsRegexpUtils.checkPhone(tbUser.getPhone())) {
            baseResult = BaseResult.error("手机号码不正确，请重新输入!");
            baseResult.setCode(BaseResult.CodeStatus.ERROR);
        }
        // 用户名非空验证
        else if (StringUtils.isBlank(tbUser.getUsername())) {
            baseResult = BaseResult.error("用户名不能为空，请重新输入!");
            baseResult.setCode(BaseResult.CodeStatus.ERROR);
        }
        // 密码非空验证
        else if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult = BaseResult.error("密码不能为空，请重新输入!");
            baseResult.setCode(BaseResult.CodeStatus.ERROR);
        }
        return baseResult;
    }

    /**
     * 用户有效性验证
     * username,email,phone
     * @param tbUser
     * @return
     */
    private BaseResult check2(TbUser tbUser) {

        BaseResult baseResult = BaseResult.ok();
        baseResult.setCode(BaseResult.CodeStatus.OK);
        // 邮箱非空验证
        if (StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.error("邮箱不能为空，请重新输入!");
            baseResult.setCode(BaseResult.CodeStatus.ERROR);
        }
        // 邮箱格式验证
        else if (!IsRegexpUtils.checkEmail(tbUser.getEmail())) {
            baseResult = BaseResult.error("邮箱格式不正确，请重新输入!");
            baseResult.setCode(BaseResult.CodeStatus.ERROR);
        }
        // 手机号码非空验证
        else if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult = BaseResult.error("手机号码不能为空，请重新输入!");
            baseResult.setCode(BaseResult.CodeStatus.ERROR);
        }
        // 手机号码格式验证
        else if (!IsRegexpUtils.checkPhone(tbUser.getPhone())) {
            baseResult = BaseResult.error("手机号码不正确，请重新输入!");
            baseResult.setCode(BaseResult.CodeStatus.ERROR);
        }
        // 用户名非空验证
        else if (StringUtils.isBlank(tbUser.getUsername())) {
            baseResult = BaseResult.error("用户名不能为空，请重新输入!");
            baseResult.setCode(BaseResult.CodeStatus.ERROR);
        }

        return baseResult;
    }

}
