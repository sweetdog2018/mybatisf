package com.example.mybatisf.model;

import java.io.Serializable;

public class SysPrivilege implements Serializable {
    private Long id;
    private String privilegeName;
    private String privilegeUrl;

    public void setId(Long id){this.id=id;}
    public Long getId(){return this.id;}

    public void setPrivilegeName(String privilegeName){this.privilegeName=privilegeName;}
    public String getPrivilegeName(){return this.privilegeName;}

    public void setPrivilegeUrl(String privilegeUrl){this.privilegeUrl=privilegeUrl;}
    public String getPrivilegeUrl(){return this.privilegeUrl;}
}
