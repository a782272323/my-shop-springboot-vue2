package learn.lhb.my.shop.backend.controller;

import learn.lhb.my.shop.backend.mapper.TbUserMapper;
import learn.lhb.my.shop.commons.dto.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 梁鸿斌
 * @date 2020/3/12.
 * @time 09:34
 */
@RestController
@RequestMapping("v1/account")
public class TbUserController {

    @Resource
    private TbUserMapper tbUserMapper;

    /**
     * 显示用户全部信息详情
     * 管理员只能看的自己的信息，和普通用户的信息
     * @return
     */
    @GetMapping("list")
    public BaseResult selectAll() {
        return BaseResult.ok().put(20000,"请求成功","data",tbUserMapper.selectAll());
    }
}
