package learn.lhb.my.shop.backend.service.impl;

import learn.lhb.my.shop.backend.security.filter.JwtLoginFilter;
import learn.lhb.my.shop.backend.security.pojo.JwtUser;
import learn.lhb.my.shop.backend.service.LimitService;
import learn.lhb.my.shop.domain.rbac.TbUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * UserDetailsService的实现
 *
 * JwtAuthenticationProvider在进行登录信息校验是就会通过它查询用户信息
 */
@Component
public class JwtUserDetailServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;


    private JwtLoginFilter jwtLoginFilter;

    @Resource
    private LimitService limitService;


    /**日志**/
    private static final Logger LOG = LoggerFactory.getLogger(JwtUserDetailServiceImpl.class);

    @Lazy(true)
    @Autowired
    public JwtUserDetailServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 数据库查询
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库查询用户的信息
        TbUser tbUser = limitService.findUsernameAndPassword(username);
        if (tbUser != null) {
            return new JwtUser(tbUser.getUsername(), tbUser.getPassword());
        }
        return null;

    }
}