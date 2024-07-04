package com.spldeolin.satisficing.identity.app.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import com.spldeolin.satisficing.identity.app.entity.User2departmentEntity;

/**
 * 用户与部门的关联关系
 * <p>user2department
 * <p>
 * <p>Allison 1875 Lot No: PG1001S-94AED10B
 *
 * @author Allison 1875 2024-06-21
 */
public interface User2departmentMapper {

    /**
     * 插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int insert(User2departmentEntity entity);

    /**
     * 批量插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int batchInsert(@Param("entities") List<User2departmentEntity> entities);

    /**
     * 批量插入，为null的属性会被作为null插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int batchInsertEvenNull(@Param("entities") List<User2departmentEntity> entities);

    /**
     * 批量根据ID更新数据
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int batchUpdate(@Param("entities") List<User2departmentEntity> entities);

    /**
     * 批量根据ID更新数据，为null对应的字段会被更新为null
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int batchUpdateEvenNull(@Param("entities") List<User2departmentEntity> entities);

    /**
     * 根据ID查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    User2departmentEntity queryById(Long id);

    /**
     * 根据ID更新数据，忽略值为null的属性
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int updateById(User2departmentEntity entity);

    /**
     * 根据ID更新数据，为null属性对应的字段会被更新为null
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int updateByIdEvenNull(User2departmentEntity entity);

    /**
     * 根据多个ID查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    List<User2departmentEntity> queryByIds(@Param("ids") List<Long> ids);

    /**
     * 根据多个ID查询，并以ID作为key映射到Map
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    @MapKey("id")
    Map<Long, User2departmentEntity> queryByIdsEachId(@Param("ids") List<Long> ids);

    /**
     * 根据「用户ID」查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    List<User2departmentEntity> queryByUserId(Long userId);

    /**
     * 根据「用户ID」删除
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int deleteByUserId(Long userId);

    /**
     * 根据多个「用户ID」查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    List<User2departmentEntity> queryByUserIds(@Param("userIds") List<Long> userIds);

    /**
     * 根据「部门ID」查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    List<User2departmentEntity> queryByDepartmentId(Long departmentId);

    /**
     * 根据「部门ID」删除
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int deleteByDepartmentId(Long departmentId);

    /**
     * 根据多个「部门ID」查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    List<User2departmentEntity> queryByDepartmentIds(@Param("departmentIds") List<Long> departmentIds);

    /**
     * 根据实体内的属性查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    List<User2departmentEntity> queryByEntity(User2departmentEntity entity);

    /**
     * 获取全部
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    List<User2departmentEntity> listAll();

    /**
     * 尝试插入，若指定了id并存在，则更新，即INSERT ON DUPLICATE KEY UPDATE
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     */
    int insertOrUpdate(User2departmentEntity entity);

}
