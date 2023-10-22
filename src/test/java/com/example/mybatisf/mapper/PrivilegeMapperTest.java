package com.example.mybatisf.mapper;

import com.example.mybatisf.model.SysPrivilege;
import com.example.mybatisf.plugin.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PrivilegeMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession=getSqlSession();
        try{
            PrivilegeMapper privilegeMapper=sqlSession.getMapper(PrivilegeMapper.class);
            SysPrivilege privilege=privilegeMapper.selectById(1l);
            Assert.assertNotNull(privilege);
            Assert.assertEquals("用户管理",privilege.getPrivilegeName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByPrivilege(){
        SqlSession sqlSession=getSqlSession();
        try{
            PrivilegeMapper privilegeMapper=sqlSession.getMapper(PrivilegeMapper.class);
            SysPrivilege privilege=new SysPrivilege();
            privilege.setPrivilegeName("管理");
            List<SysPrivilege> list=privilegeMapper.selectByPrivilege(privilege);
            Assert.assertEquals(2,list.size());
            privilege=new SysPrivilege();
            privilege.setPrivilegeUrl("es");
            list=privilegeMapper.selectByPrivilege(privilege);
            Assert.assertEquals(2,list.size());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllByRowBounds(){
        SqlSession sqlSession=getSqlSession();
        try{
            PrivilegeMapper privilegeMapper=sqlSession.getMapper(PrivilegeMapper.class);
            RowBounds rowBounds=new RowBounds(0,2);
            List<SysPrivilege> list=privilegeMapper.selectAll(rowBounds);
            for(SysPrivilege privilege:list)
                System.out.println("权限名："+privilege.getPrivilegeName());
            PageRowBounds pageRowBounds=new PageRowBounds(2,2);
            list=privilegeMapper.selectAll(pageRowBounds);
            System.out.println("权限总数："+pageRowBounds.getTotal());
            for(SysPrivilege privilege:list)
                System.out.println("权限名："+privilege.getPrivilegeName());
            pageRowBounds=new PageRowBounds(4,2);
            list=privilegeMapper.selectAll(pageRowBounds);
            System.out.println("权限总数："+pageRowBounds.getTotal());
            for(SysPrivilege privilege:list)
                System.out.println("权限名："+privilege.getPrivilegeName());
        }finally {
            sqlSession.close();
        }
    }
}
