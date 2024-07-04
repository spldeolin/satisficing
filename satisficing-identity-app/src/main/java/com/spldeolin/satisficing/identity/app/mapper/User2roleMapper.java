package com.spldeolin.satisficing.identity.app.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import com.spldeolin.satisficing.identity.app.entity.User2roleEntity;

/**
 * 用户与角色的关联关系
 * <p>user2role
 * <p>
 * <p>Allison 1875 Lot No: PG1001S-5474455B
 *
 * @author Allison 1875 2024-06-19
 */
public interface User2roleMapper {

    /**
     * 插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    int insert(User2roleEntity entity);

    /**
     * 批量插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    int batchInsert(@Param("entities") List<User2roleEntity> entities);

    /**
     * 批量插入，为null的属性会被作为null插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    int batchInsertEvenNull(@Param("entities") List<User2roleEntity> entities);

    /**
     * 批量根据ID更新数据
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    int batchUpdate(@Param("entities") List<User2roleEntity> entities);

    /**
     * 批量根据ID更新数据，为null对应的字段会被更新为null
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    int batchUpdateEvenNull(@Param("entities") List<User2roleEntity> entities);

    /**
     * 根据ID查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    User2roleEntity queryById(Long id);

    /**
     * 根据ID更新数据，忽略值为null的属性
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    int updateById(User2roleEntity entity);

    /**
     * 根据ID更新数据，为null属性对应的字段会被更新为null
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    int updateByIdEvenNull(User2roleEntity entity);

    /**
     * 根据多个ID查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    List<User2roleEntity> queryByIds(@Param("ids") List<Long> ids);

    /**
     * 根据多个ID查询，并以ID作为key映射到Map
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    @MapKey("id")
    Map<Long, User2roleEntity> queryByIdsEachId(@Param("ids") List<Long> ids);

    /**
     * 根据「用户ID」查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    List<User2roleEntity> queryByUserId(Long userId);

    /**
     * 根据「用户ID」删除
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    int deleteByUserId(Long userId);

    /**
     * 根据多个「用户ID」查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    List<User2roleEntity> queryByUserIds(@Param("userIds") List<Long> userIds);

    /**
     * 根据「角色ID」查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    List<User2roleEntity> queryByRoleId(Long roleId);

    /**
     * 根据「角色ID」删除
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    int deleteByRoleId(Long roleId);

    /**
     * 根据多个「角色ID」查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    List<User2roleEntity> queryByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     * 根据实体内的属性查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    List<User2roleEntity> queryByEntity(User2roleEntity entity);

    /**
     * 获取全部
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    List<User2roleEntity> listAll();

    /**
     * 尝试插入，若指定了id并存在，则更新，即INSERT ON DUPLICATE KEY UPDATE
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-5474455B
     */
    int insertOrUpdate(User2roleEntity entity);

    List<Long> queryUserIds(Long roleId);
}
