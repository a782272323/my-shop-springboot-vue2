package learn.lhb.my.shop.backend.controller;

import com.github.pagehelper.PageHelper;
import learn.lhb.my.shop.backend.mapper.TbUserMapper;
import learn.lhb.my.shop.commons.dto.BaseResult;
import learn.lhb.my.shop.commons.dto.PageParams;
import learn.lhb.my.shop.domain.rbac.TbUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    public BaseResult selectAll(Authentication authentication,PageParams pageParams) {
        System.out.println("查询用户信息");
        System.out.println("pageIndex = "+pageParams.getPageIndex());
        System.out.println("pageSize = "+pageParams.getPageSize());
        System.out.println("sortName = "+pageParams.getSortName());
        System.out.println("sortType = "+pageParams.getSortType());
        PageHelper.startPage(pageParams.getPageIndex(), pageParams.getPageSize());
        Map<String,Object> map = new HashMap<>();
        map.put("getAccountList", tbUserMapper.selectAll(pageParams));
        map.put("total", tbUserMapper.getTotalAll());
        return BaseResult.ok().put(20000, "请求成功", "data", map);
    }

    @GetMapping("lists")
    public BaseResult queryAll(Authentication authentication, PageParams pageParams, TbUser tbUser) {
        System.out.println("模糊查询");
        System.out.println("pageIndex = "+pageParams.getPageIndex());
        System.out.println("pageSize = "+pageParams.getPageSize());
        System.out.println("sortName = "+pageParams.getSortName());
        System.out.println("sortType = "+pageParams.getSortType());
        PageHelper.startPage(pageParams.getPageIndex(), pageParams.getPageSize());
        Map<String, Object> map = new HashMap<>();
        map.put("getAccountList", tbUserMapper.queryAll(pageParams, tbUser));
        map.put("total", tbUserMapper.queryTotalAll(tbUser));
        return BaseResult.ok().put(BaseResult.CodeStatus.OK,"请求成功","data",map);
    }

}
