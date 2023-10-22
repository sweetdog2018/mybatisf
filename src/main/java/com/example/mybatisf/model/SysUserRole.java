package com.example.mybatisf.model;

public class SysUserRole {
    private Long userId;
    private Long roleId;

    public void setUserId(Long userId){this.userId=userId;}
    public Long getUserId(){return this.userId;}

    public void setRoleId(Long roleId){this.roleId=roleId;}
    public Long getRoleId(){return this.roleId;}
}
