package learn.lhb.my.shop.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 梁鸿斌
 * @date 2020/3/11.
 * @time 13:14
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleTest {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void passwordTest() {
        System.out.println(passwordEncoder.encode("123456"));
    }
}
