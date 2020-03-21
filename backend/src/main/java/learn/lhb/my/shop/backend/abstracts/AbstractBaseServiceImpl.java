package learn.lhb.my.shop.backend.abstracts;

import com.github.pagehelper.PageHelper;
import learn.lhb.my.shop.commons.dto.PageParams;
import learn.lhb.my.shop.commons.persistence.BaseDateEntity;
import learn.lhb.my.shop.commons.persistence.BaseMapper;
import learn.lhb.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 所有业务逻辑层的实现类的抽象基类(ServiceImpl)
 * 查询全部数据（分页，排序）,模糊查询数据（分页，排序），删除，修改，根据ID查询信息（一整条），批量删除，查询结果的总笔数
 * 若数据访问层是Dao则使用D，是Mapper则使用M
 *
 * @author 梁鸿斌
 * @date 2020/3/21.
 * @time 17:14
 */
public class AbstractBaseServiceImpl<T extends BaseDateEntity,M extends BaseMapper<T>> implements BaseService<T> {

    /**
     * 注入Dao
     */
    @Autowired
    protected M mapper;

    /**
     * 查询全部数据（分页，排序)
     * @param pageParams 分页参数的实体类
     * @return
     */
    @Override
    public List<Map<String, T>> selectAll(PageParams pageParams) {
        return mapper.selectAll(pageParams);
    }

    /**
     * 查询结果的总笔数
     * @param entity
     * @return
     */
    @Override
    public int count(T entity) {
        return mapper.count(entity);
    }

    /**
     * 模糊查询数据（分页，排序）
     * @param pageParams
     * @param entity
     * @return
     */
    @Override
    public List<Map<String, T>> queryAll(PageParams pageParams, T entity) {
        return mapper.queryAll(pageParams,entity);
    }

    /**
     * 根据ID查询信息（一整条）
     * @param id
     * @return
     */
    @Override
    public T selectOne(String id) {
        return mapper.selectOne(id);
    }

    /**
     * 删除单个
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {
        return mapper.delete(id);
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteMulti(String[] ids) {
        mapper.deleteMulti(ids);
    }

}
