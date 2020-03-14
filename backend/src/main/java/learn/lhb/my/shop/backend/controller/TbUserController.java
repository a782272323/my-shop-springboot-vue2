package learn.lhb.my.shop.backend.controller;

import learn.lhb.my.shop.backend.mapper.TbUserMapper;
import learn.lhb.my.shop.commons.dto.BaseResult;
import learn.lhb.my.shop.domain.rbac.TbUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class TbUserController {

    private static final Logger logger = LoggerFactory.getLogger(TbUserController.class);

    @Resource
    private TbUserMapper tbUserMapper;

    /**
     * 显示用户全部信息详情
     * 管理员只能看的自己的信息，和普通用户的信息
     *
     * @return
     */
    @GetMapping("list")
    public BaseResult selectAll() {
        System.out.println("查询用户信息");
        logger.debug("查询用户信息");
//        Map<Object, List<TbUser>> map = new HashMap<>();
//        map.put("getAccountList", tbUserMapper.selectAll());
        return BaseResult.ok().put(20000, "请求成功", "getAccountList", tbUserMapper.selectAll());
    }

}
