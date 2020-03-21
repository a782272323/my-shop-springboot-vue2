package learn.lhb.my.shop.commons.persistence;

import learn.lhb.my.shop.commons.dto.PageParams;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 所有数据访问层的基类（也可以叫BaseDao，具体看项目的数据访问层名字而定）
 * 查询全部数据（分页，排序）,模糊查询数据（分页，排序），新增，删除，修改，根据ID查询信息（一整条），批量删除，查询结果的总笔数
 * @author 梁鸿斌
 * @date 2020/3/21.
 * @time 16:53
 */
public interface BaseMapper<T extends BaseDateEntity> {

    /**
     * 查询全部（分页，排序)
     * @param pageParams 一般都是分页参数的实体类
     * @return
     */
    List<Map<String,T>> selectAll(PageParams pageParams);

    /**
     * 查询结果的总笔数
     * @param entity
     * @return
     */
    int count(T entity);

    /**
     * 模糊查询数据（分页，排序）
     * @param pageParams
     * @param entity
     * @return
     */
    List<Map<String, T>> queryAll(PageParams pageParams,@Param("entity") T entity);

    /**
     * 根据ID查询信息（一整条）
     * @param id
     * @return
     */
    T selectOne(String id);

    /**
     * 添加操作
     * @param entity
     * @return
     */
    Integer insert(@Param("entity") T entity);

    /**
     * 修改操作
     * @param entity
     * @param id
     * @return
     */
    Integer update(@Param("entity") T entity,@Param("id") String id);

    /**
     * 删除操作（删除单个且根据id）
     * @param id
     * @return
     */
    Integer delete(@Param("id") String id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    void deleteMulti(@Param("ids")String[] ids);

}
