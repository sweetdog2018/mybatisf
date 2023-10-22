package com.example.mybatisf.model;

import com.example.mybatisf.type.Enabled;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysRole implements Serializable {
    private static final long serialVersionUID=6320941908222932112L;

    private Long id;
    private String roleName;
    private Enabled enabled;
    private Long createBy;
    private Date createTime;
    private SysUser user;
    private List<SysPrivilege> privilegeList;
    private CreateInfo createInfo;

    public void setId(Long id){this.id=id;}
    public Long getId(){return this.id;}

    public void setRoleName(String roleName){this.roleName=roleName;}
    public String getRoleName(){return this.roleName;}

    public void setEnabled(Enabled enabled){this.enabled=enabled;}
    public Enabled getEnabled(){return this.enabled;}

    public void setCreateBy(Long createBy){this.createBy=createBy;}
    public Long getCreateBy(){return this.createBy;}

    public void setCreateTime(Date createTime){this.createTime=createTime;}
    public Date getCreateTime(){return this.createTime;}

    public void setUser(SysUser user){this.user=user;}
    public SysUser getUser(){return this.user;}

    public void setPrivilegeList(List<SysPrivilege> privilegeList){this.privilegeList=privilegeList;}
    public List<SysPrivilege> getPrivilegeList(){return this.privilegeList;}

    public void setCreateInfo(CreateInfo createInfo){this.createInfo=createInfo;}
    public CreateInfo getCreateInfo(){return this.createInfo;}
}
