package com.spldeolin.satisficing.identity.app.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import com.spldeolin.satisficing.identity.app.entity.UserEntity;
import com.spldeolin.satisficing.identity.app.javabean.cond.QueryUserCond;
import com.spldeolin.satisficing.identity.app.javabean.record.QueryUserExRecord;

/**
 * 用户
 * <p>user
 *
 * @author Deolin 2024-06-03
 */
public interface UserMapper {

    /**
     * 插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    int insert(UserEntity entity);

    /**
     * 批量插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    int batchInsert(@Param("entities") List<UserEntity> entities);

    /**
     * 批量插入，为null的属性会被作为null插入
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    int batchInsertEvenNull(@Param("entities") List<UserEntity> entities);

    /**
     * 批量根据ID更新数据
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    int batchUpdate(@Param("entities") List<UserEntity> entities);

    /**
     * 批量根据ID更新数据，为null对应的字段会被更新为null
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    int batchUpdateEvenNull(@Param("entities") List<UserEntity> entities);

    /**
     * 根据ID查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    UserEntity queryById(Long id);

    /**
     * 根据ID更新数据，忽略值为null的属性
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    int updateById(UserEntity entity);

    /**
     * 根据ID更新数据，为null属性对应的字段会被更新为null
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    int updateByIdEvenNull(UserEntity entity);

    /**
     * 根据多个ID查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    List<UserEntity> queryByIds(@Param("ids") List<Long> ids);

    /**
     * 根据多个ID查询，并以ID作为key映射到Map
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    @MapKey("id")
    Map<Long, UserEntity> queryByIdsEachId(@Param("ids") List<Long> ids);

    /**
     * 根据实体内的属性查询
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    List<UserEntity> queryByEntity(UserEntity entity);

    /**
     * 获取全部
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    List<UserEntity> listAll();

    /**
     * 尝试插入，若指定了id并存在，则更新，即INSERT ON DUPLICATE KEY UPDATE
     * <p>
     * <p>Any modifications may be overwritten by future code generations.
     * <p>Allison 1875 Lot No: PG1001S-F7A47F26
     */
    int insertOrUpdate(UserEntity entity);

    /**
     * QT1001S-7D40249D
     */
    UserEntity getByUsernameOrMobile(@Param("username") String username, @Param("mobile") String mobile);

    /**
     * QT1001S-CCA44D8E
     */
    UserEntity queryUser(@Param("userUuid") String userUuid);

    /**
     * QT1001S-1B2F0DC4
     */
    List<QueryUserExRecord> queryUserEx(QueryUserCond queryUserCond);

    /**
     * QT1001S-0C147D98
     */
    UserEntity queryUserEx2(@Param("userUuid") String userUuid, @Param("username") String username,
            @Param("mobile") String mobile);

}
