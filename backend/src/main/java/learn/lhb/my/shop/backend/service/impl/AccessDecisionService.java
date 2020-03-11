package learn.lhb.my.shop.backend.service.impl;

import learn.lhb.my.shop.backend.mapper.LimitMapper;
import learn.lhb.my.shop.domain.rbac.TbPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 配置路径访问限制,若你的用户角色比较简单,不需要存数据库,
 * 可以在ApplicationConfigurerAdapter里配置如
 * httpSecurity
 * .authorizeRequests()
 * .antMatchers("/order").....
 *
 * @author niXueChao
 * @date 2019/4/10 10:33.
 */
@Component("accessDecisionService")
public class AccessDecisionService {

    @Autowired
    private LimitMapper limitMapper;


    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 不被拦截的路径
        for (String url : Arrays.asList("/user/login")) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                return true;
            }
        }

        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // 根据用户名查出拥有哪些权限
        List<String> urls = queryUrlByUserName(userDetails.getUsername());
        for (String url : urls) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从数据库拿权限信息
     */
    private List<String > queryUrlByUserName(String username) {
        List<TbPermission> tbPermissions = limitMapper.getUrlsByRoleName(limitMapper.findRoleNameByUsername(username));
        String[] strings = new String[tbPermissions.size()];
        for (int i = 0; i < tbPermissions.size(); i++) {
            strings[i] = tbPermissions.get(i).getUrl();
        }
        return Arrays.asList(strings);
    }
}