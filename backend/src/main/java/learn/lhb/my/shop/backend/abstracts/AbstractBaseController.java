package learn.lhb.my.shop.backend.abstracts;

import com.github.pagehelper.PageHelper;
import learn.lhb.my.shop.commons.constant.DtoParamsConstant;
import learn.lhb.my.shop.commons.constant.HttpCodeConstant;
import learn.lhb.my.shop.commons.dto.BaseResult;
import learn.lhb.my.shop.commons.dto.PageParams;
import learn.lhb.my.shop.commons.persistence.BaseDateEntity;
import learn.lhb.my.shop.commons.persistence.BaseMapper;
import learn.lhb.my.shop.commons.persistence.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

/**
 * 所有Controller的抽象类
 * 查询全部数据（分页，排序）,模糊查询数据（分页，排序），新增，删除，修改，根据ID查询信息（一整条），批量删除，查询结果的总笔数
 *
 * @author 梁鸿斌
 * @date 2020/3/21.
 * @time 17:41
 */
public class AbstractBaseController<T extends BaseDateEntity, S extends BaseService<T>> {

    /**
     * 注入业务逻辑层
     */
    @Autowired
    protected S service;

    /**
     * 显示用户全部信息详情
     * 管理员只能看的自己的信息，和普通用户的信息
     *
     * @param authentication 令牌
     * @param pageParams     分页参数实体类
     * @return
     */
    @GetMapping("list")
    public BaseResult selectAll(Authentication authentication, PageParams pageParams, T entity) {
        System.out.println("查询用户信息");
        System.out.println(pageParams.toString());

        PageHelper.startPage(pageParams.getPageIndex(), pageParams.getPageSize());
        Map<String, Object> map = new HashMap<>();
        map.put(DtoParamsConstant.DEFAULT_LIST, service.selectAll(pageParams));
        map.put(DtoParamsConstant.COUNT1, service.count(entity));
        return BaseResult.ok().put(HttpCodeConstant.OK, HttpCodeConstant.DEFAULT_OK, DtoParamsConstant.DEFAULT_DATA, map);
    }

    /**
     * 模糊查询数据（分页，排序）
     *
     * @param authentication
     * @param pageParams
     * @param entity
     * @return
     */
    @GetMapping("lists")
    public BaseResult queryAll(Authentication authentication, PageParams pageParams, T entity) {
        System.out.println("模糊查询");
        System.out.println(pageParams.toString());
        System.out.println(entity.toString());

        PageHelper.startPage(pageParams.getPageIndex(), pageParams.getPageSize());
        Map<String, Object> map = new HashMap<>();
        map.put(DtoParamsConstant.DEFAULT_LIST, service.queryAll(pageParams, entity));
        map.put(DtoParamsConstant.COUNT1, service.count(entity));
        return BaseResult.ok().put(HttpCodeConstant.OK, HttpCodeConstant.DEFAULT_OK, DtoParamsConstant.DEFAULT_DATA, map);
    }

    /**
     * 根据ID查询信息（一整条）
     *
     * @param authentication
     * @param pageParams
     * @param userId
     * @return
     */
    @GetMapping("list/{userId}")
    public BaseResult selectOne(Authentication authentication, PageParams pageParams, @PathVariable String userId) {
        System.out.println("根据ID查询信息（一整条）");
        System.out.println(pageParams.toString());
        System.out.println("userId = " + userId);
        Map<String, Object> map = new HashMap<>();
        map.put(DtoParamsConstant.DEFAULT_LIST, service.selectOne(userId));
        return BaseResult.ok().put(HttpCodeConstant.OK, HttpCodeConstant.DEFAULT_OK, DtoParamsConstant.DEFAULT_DATA, map);
    }

    /**
     * 删除用户
     * @param authentication
     * @param userId
     * @return
     */
    @DeleteMapping("list/{userId}")
    public BaseResult delete(Authentication authentication, @PathVariable String userId) {
        System.out.println("删除单个用户");
        System.out.println("userId = "+userId);
        if (service.delete(userId) > 0) {
            return BaseResult.ok();
        } else {
            return BaseResult.error(HttpCodeConstant.ERROR);
        }
    }


    /**
     * 批量删除
     * @param authentication
     * @param userIds
     * @return
     */
    @DeleteMapping("list")
    public BaseResult deletes(Authentication authentication, String userIds) {
        System.out.println("批量删除");
        if (StringUtils.isNoneBlank(userIds)) {
            String[] idArray = userIds.split(",");
            service.deleteMulti(idArray);
            return BaseResult.ok();
        } else {
            return BaseResult.error();
        }

    }
}
