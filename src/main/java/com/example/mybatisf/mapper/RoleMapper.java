package com.example.mybatisf.mapper;

import com.example.mybatisf.model.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper {
    @Select({"select id,role_name,enabled,create_by,create_time ",
            "from sys_role ","where id=#{id}"})
    SysRole selectById(Long id);

    @Results(id="roleResultMap" ,value={
            @Result(property = "id",column = "id",id = true),
            @Result(property = "roleName",column = "role_name"),
            @Result(property = "enabled",column = "enabled"),
            @Result(property = "createBy",column = "create_by"),
            @Result(property = "createTime",column = "create_time")
    })
    @Select("select id,role_name,enabled,create_by,create_time from sys_role where id=#{id}")
    SysRole selectById2(Long id);

    @ResultMap("roleResultMap")
    @Select("select * from sys_role")
    List<SysRole> selectAll();

    @Insert({"insert into sys_role values(#{id},#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"})
    int insert(SysRole sysRole);

    @Insert({"insert into sys_role(role_name,enabled,create_by,create_time) ",
            "values(#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert2(SysRole sysRole);

    @Insert({"insert into sys_role(role_name,enabled,create_by,create_time) ",
            "values(#{roleName},#{enabled},#{createBy},#{createTime,jdbcType=TIMESTAMP})"})
    @SelectKey(statement = "select last_insert_id()",
                keyProperty = "id",
                resultType = Long.class,
                before = false)
    int insert3(SysRole sysRole);

    @Update({"update sys_role set role_name=#{roleName},enabled=#{enabled},create_by=#{createBy},create_time=#{createTime} ",
            "where id=#{id}"})
    int updateById(SysRole sysRole);

    @Delete("delete from sys_role where id=#{id}")
    int deleteById(Long id);

    List<SysRole> selectAllRoleAndPrivileges();
    List<SysRole> selectRoleByUserIdChoose(Long userId);
}
