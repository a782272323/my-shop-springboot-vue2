package learn.lhb.my.shop.backend.mapper;

import learn.lhb.my.shop.domain.rbac.TbUser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * @return
     */
    List<TbUser> selectAll();
}
