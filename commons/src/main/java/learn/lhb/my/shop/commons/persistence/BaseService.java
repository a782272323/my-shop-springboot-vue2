package learn.lhb.my.shop.commons.persistence;

import learn.lhb.my.shop.commons.dto.PageParams;

import java.util.List;
import java.util.Map;

/**
 * 所有业务逻辑层的基类（Service）
 * 查询全部数据（分页，排序）,模糊查询数据（分页，排序），删除，修改，根据ID查询信息（一整条），批量删除，查询结果的总笔数
 * @author 梁鸿斌
 * @date 2020/3/21.
 * @time 17:10
 */
public interface BaseService<T extends BaseDateEntity> {

    /**
     * 查询全部数据（分页，排序）
     * @param pageParams 分页参数的实体类
     * @return
     */
    public List<Map<String,T>> selectAll(PageParams pageParams);

    /**
     * 查询结果的总笔数
     * @param entity
     * @return
     */
    public int count(T entity);

    /**
     * 模糊查询数据（分页，排序）
     * @param pageParams
     * @param entity
     * @return
     */
    public List<Map<String, T>> queryAll(PageParams pageParams, T entity);

    /**
     * 根据ID查询信息（一整条）
     * @param id
     * @return
     */
    public T selectOne(String id);

    /**
     * 删除单个操作
     * @param id
     * @return
     */
    Integer delete(String id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    void deleteMulti(String[] ids);
}
