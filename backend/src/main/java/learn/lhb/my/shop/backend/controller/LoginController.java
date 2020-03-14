package learn.lhb.my.shop.backend.controller;

import learn.lhb.my.shop.backend.mapper.LimitMapper;
import learn.lhb.my.shop.commons.dto.BaseResult;
import learn.lhb.my.shop.commons.dto.LoginInfo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 10:48
 */
@RestController
@RequestMapping("v1/user")
public class LoginController {

    @Resource
    private LimitMapper limitMapper;

    @GetMapping(value = "info",produces = {"application/json;charset=utf-8"})
    public BaseResult info(Authentication authentication) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(authentication.getName());
        loginInfo.setAvatar("static/images/小埋1.jpg");
        loginInfo.setRoles(limitMapper.findRoleNameByUsername(authentication.getName()));

        return BaseResult.ok().put(BaseResult.CodeStatus.OK, "请求成功", "data", loginInfo);
    }

    /**
     * 退出
     * @param authentication
     * @return
     */
    @PostMapping("logout")
    public BaseResult logout(Authentication authentication) {
        return BaseResult.ok();
    }
}
