package learn.lhb.my.shop.backend.controller;

import learn.lhb.my.shop.commons.dto.BaseResult;
import learn.lhb.my.shop.commons.dto.LoginInfo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 10:48
 */
@RestController
@RequestMapping("v1/user")
public class LoginController {

    @GetMapping("info")
    public BaseResult info(Authentication authentication) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(authentication.getName());
        loginInfo.setAvatar("static/images/xiaomai01.ico");
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
