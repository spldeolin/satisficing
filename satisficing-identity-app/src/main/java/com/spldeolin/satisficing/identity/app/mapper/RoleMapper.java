package com.spldeolin.satisficing.identity.app.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import com.spldeolin.satisficing.identity.app.entity.RoleEntity;

/**
 * 角色
 * <p>role
 * <p>
 * <p>Allison 1875 Lot No: PG1001S-9E7A9F37
 *
 * @author Allison 1875 2024-06-19
 */
public interface RoleMapper {

    /**
     * 插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    int insert(RoleEntity entity);

    /**
     * 批量插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    int batchInsert(@Param("entities") List<RoleEntity> entities);

    /**
     * 批量插入，为null的属性会被作为null插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    int batchInsertEvenNull(@Param("entities") List<RoleEntity> entities);

    /**
     * 批量根据ID更新数据
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    int batchUpdate(@Param("entities") List<RoleEntity> entities);

    /**
     * 批量根据ID更新数据，为null对应的字段会被更新为null
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    int batchUpdateEvenNull(@Param("entities") List<RoleEntity> entities);

    /**
     * 根据ID查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    RoleEntity queryById(Long id);

    /**
     * 根据ID更新数据，忽略值为null的属性
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    int updateById(RoleEntity entity);

    /**
     * 根据ID更新数据，为null属性对应的字段会被更新为null
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    int updateByIdEvenNull(RoleEntity entity);

    /**
     * 根据多个ID查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    List<RoleEntity> queryByIds(@Param("ids") List<Long> ids);

    /**
     * 根据多个ID查询，并以ID作为key映射到Map
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    @MapKey("id")
    Map<Long, RoleEntity> queryByIdsEachId(@Param("ids") List<Long> ids);

    /**
     * 根据实体内的属性查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    List<RoleEntity> queryByEntity(RoleEntity entity);

    /**
     * 获取全部
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    List<RoleEntity> listAll();

    /**
     * 尝试插入，若指定了id并存在，则更新，即INSERT ON DUPLICATE KEY UPDATE
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-74859B7D
     */
    int insertOrUpdate(RoleEntity entity);

    /**
     * QT1001S-39F9E3E5
     */
    List<RoleEntity> queryRole(@Param("roleName") String roleName, @Param("description") String description);

    /**
     * QT1001S-A9C5418E
     */
    RoleEntity queryRoleEx(@Param("roleName") String roleName, @Param("id") Long id);

    /**
     * QT1001S-ABBD8070
     */
    int dropRole(@Param("id") Long id);

}
