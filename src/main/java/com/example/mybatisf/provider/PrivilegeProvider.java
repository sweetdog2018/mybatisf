package com.example.mybatisf.provider;

import com.example.mybatisf.model.SysPrivilege;
import org.apache.ibatis.jdbc.SQL;

public class PrivilegeProvider {
    public String selectById(final Long id){
        return new SQL(){
            {
                SELECT("id,privilege_name,privilege_url");
                FROM("sys_privilege");
                WHERE("id=#{id}");
            }
        }.toString();
    }

    public String selectByPrivilege(final SysPrivilege privilege){
        return new SQL(){
            {
                SELECT("id,privilege_name,privilege_url");
                FROM("sys_privilege");
                if(privilege!=null){
                    if(privilege.getId()!=null){
                        WHERE("id=#{id}");
                    }
                    if(privilege.getPrivilegeName()!=null&&privilege.getPrivilegeName().length()>0){
                        WHERE("privilege_name like concat('%',#{privilegeName},'%')");
                    }
                    if(privilege.getPrivilegeUrl()!=null&&privilege.getPrivilegeUrl().length()>0){
                        WHERE("privilege_url like '%"+privilege.getPrivilegeUrl()+"%'");
                    }
                }
            }
        }.toString();
    }

    public String selectAll(){
        return "select * from sys_privilege";
    }

    public String insert(final SysPrivilege privilege){
        return new SQL(){
            {
                INSERT_INTO("sys_privilege");
                VALUES("privilege_name","#{privilegeName}");
                VALUES("privilege_url","#{privilegeUrl}");
            }
        }.toString();
    }

    public String updateById(final SysPrivilege privilege){
        return new SQL(){
            {
                UPDATE("sys_privilege");
                SET("privilege_name=#{privilegeName}");
                SET("privilege_url=#{privilegeUrl}");
                WHERE("id=#{id}");
            }
        }.toString();
    }

    public String deleteById(final Long id){
        return new SQL(){
            {
                DELETE_FROM("sys_privilege");
                WHERE("id=#{id}");
            }
        }.toString();
    }
}
