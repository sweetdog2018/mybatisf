package com.example.mybatisf.mapper;

import com.example.mybatisf.model.SysPrivilege;
import com.example.mybatisf.provider.PrivilegeProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@CacheNamespace
public interface PrivilegeMapper {
    @SelectProvider(type = PrivilegeProvider.class,method = "selectById")
    SysPrivilege selectById(final Long id);

    @SelectProvider(type = PrivilegeProvider.class,method = "selectByPrivilege")
    List<SysPrivilege> selectByPrivilege(SysPrivilege privilege);

    @SelectProvider(type = PrivilegeProvider.class,method = "selectAll")
    List<SysPrivilege> selectAll();

    List<SysPrivilege> selectAll(RowBounds rowBounds);

    @InsertProvider(type = PrivilegeProvider.class,method = "insert")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(final SysPrivilege privilege);

    @InsertProvider(type = PrivilegeProvider.class,method = "insert")
    @SelectKey(statement = "select_last_insert_id()",keyProperty = "id",resultType = Long.class,before = false)
    int insert2(final SysPrivilege privilege);

    @UpdateProvider(type = PrivilegeProvider.class,method = "updateById")
    int updateById(final SysPrivilege privilege);

    @DeleteProvider(type = PrivilegeProvider.class,method = "deleteById")
    int deleteById(final Long id);
}
