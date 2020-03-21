package learn.lhb.my.shop.backend.controller;

import com.github.pagehelper.PageHelper;
import learn.lhb.my.shop.backend.abstracts.AbstractBaseController;
import learn.lhb.my.shop.backend.mapper.TbUserMapper;
import learn.lhb.my.shop.backend.service.TbUserService;
import learn.lhb.my.shop.commons.dto.BaseResult;
import learn.lhb.my.shop.commons.dto.PageParams;
import learn.lhb.my.shop.domain.rbac.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 梁鸿斌
 * @date 2020/3/12.
 * @time 09:34
 */
@RestController
@RequestMapping("v1/account")
@CrossOrigin
public class TbUserController extends AbstractBaseController<TbUser, TbUserService> {

    private static final Logger logger = LoggerFactory.getLogger(TbUserController.class);

    @Resource
    private TbUserMapper tbUserMapper;

    @Resource
    private TbUserService tbUserService;

    /**
     * 添加用户
     * @param authentication
     * @param tbUser
     * @return
     */
    @PostMapping("list")
    public BaseResult insert(Authentication authentication,@RequestBody TbUser tbUser) {
        System.out.println("添加用户");
        return tbUserService.insert(tbUser);
    }

    /**
     * 修改用户
     * @param authentication
     * @param tbUser
     * @return
     */
    @PutMapping("list/{user_id}")
    public BaseResult update(Authentication authentication,@PathVariable String user_id,@RequestBody TbUser tbUser) {
        System.out.println("修改用户");
        return tbUserService.update(tbUser,user_id);
    }
}
