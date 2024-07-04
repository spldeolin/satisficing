package com.spldeolin.satisficing.identity.app.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import com.spldeolin.satisficing.identity.app.entity.DepartmentEntity;

/**
 * 部门
 * <p>department
 * <p>
 * <p>Allison 1875 Lot No: PG1001S-4FC996B0
 *
 * @author Allison 1875 2024-06-21
 */
public interface DepartmentMapper {

    /**
     * 插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int insert(DepartmentEntity entity);

    /**
     * 批量插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int batchInsert(@Param("entities") List<DepartmentEntity> entities);

    /**
     * 批量插入，为null的属性会被作为null插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int batchInsertEvenNull(@Param("entities") List<DepartmentEntity> entities);

    /**
     * 批量根据ID更新数据
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int batchUpdate(@Param("entities") List<DepartmentEntity> entities);

    /**
     * 批量根据ID更新数据，为null对应的字段会被更新为null
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int batchUpdateEvenNull(@Param("entities") List<DepartmentEntity> entities);

    /**
     * 根据ID查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    DepartmentEntity queryById(Long id);

    /**
     * 根据ID更新数据，忽略值为null的属性
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int updateById(DepartmentEntity entity);

    /**
     * 根据ID更新数据，为null属性对应的字段会被更新为null
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int updateByIdEvenNull(DepartmentEntity entity);

    /**
     * 根据多个ID查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    List<DepartmentEntity> queryByIds(@Param("ids") List<Long> ids);

    /**
     * 根据多个ID查询，并以ID作为key映射到Map
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    @MapKey("id")
    Map<Long, DepartmentEntity> queryByIdsEachId(@Param("ids") List<Long> ids);

    /**
     * 根据「上级部门ID」查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    List<DepartmentEntity> queryByParentId(String parentId);

    /**
     * 根据「上级部门ID」删除
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int deleteByParentId(String parentId);

    /**
     * 根据多个「上级部门ID」查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    List<DepartmentEntity> queryByParentIds(@Param("parentIds") List<String> parentIds);

    /**
     * 根据实体内的属性查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    List<DepartmentEntity> queryByEntity(DepartmentEntity entity);

    /**
     * 获取全部
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    List<DepartmentEntity> listAll();

    /**
     * 尝试插入，若指定了id并存在，则更新，即INSERT ON DUPLICATE KEY UPDATE
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int insertOrUpdate(DepartmentEntity entity);

}
