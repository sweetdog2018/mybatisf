package com.example.mybatisf.model;

import java.util.Date;
import java.util.List;

public class SysUser {
    private Long id;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userInfo;
    private byte[] headImg;
    private Date createTime;
    private List<SysRole> roleList;


    public void setId(Long id){this.id=id;}
    public Long getId(){return this.id;}

    public void setUserName(String userName){this.userName=userName;}
    public String getUserName(){return this.userName;}

    public void setUserPassword(String userPassword){this.userPassword=userPassword;}
    public String getUserPassword(){return this.userPassword;}

    public void setUserEmail(String userEmail){
        this.userEmail=userEmail;
    }
    public String getUserEmail(){return this.userEmail;}

    public void setUserInfo(String userInfo){this.userInfo=userInfo;}
    public String getUserInfo(){return this.userInfo;}

    public void setHeadImg(byte[] headImg){this.headImg=headImg;}
    public byte[] getHeadImg(){return this.headImg;}

    public void setCreateTime(Date createTime){this.createTime=createTime;}
    public Date getCreateTime(){return this.createTime;}

    public void setRoleList(List<SysRole> roleList){this.roleList=roleList;}
    public List<SysRole> getRoleList(){return this.roleList;}
}
