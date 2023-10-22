package com.example.mybatisf.model;

public class SysRolePrivilege {
    private Long roleId;
    private Long privilegeId;

    public void setRoleId(Long roleId){this.roleId=roleId;}
    public Long getRoleId(){return this.roleId;}

    public void setPrivilegeId(Long privilegeId){this.privilegeId=privilegeId;}
    public Long getPrivilegeId(){return this.privilegeId;}
}
