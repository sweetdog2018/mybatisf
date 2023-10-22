package com.example.mybatisf.mapper;

import com.example.mybatisf.model.SysPrivilege;
import com.example.mybatisf.model.SysRole;
import com.example.mybatisf.type.Enabled;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession=getSqlSession();
        try{
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole=roleMapper.selectById(1l);
            Assert.assertNotNull(sysRole);
            Assert.assertEquals("管理员",sysRole.getRoleName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById2(){
        SqlSession sqlSession=getSqlSession();
        try{
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole=roleMapper.selectById2(1l);
            Assert.assertNotNull(sysRole);
            Assert.assertEquals("管理员",sysRole.getRoleName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession=getSqlSession();
        try{
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            List<SysRole> list=roleMapper.selectAll();
            Assert.assertNotNull(list);
            Assert.assertTrue(list.size()>0);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession=getSqlSession();
        try{
            SysRole sysRole=new SysRole();
            sysRole.setRoleName("role1");
            //sysRole.setEnabled(1);
            sysRole.setCreateBy(1l);
            sysRole.setCreateTime(new Date());
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            int result=roleMapper.insert(sysRole);
            Assert.assertEquals(1,result);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2(){
        SqlSession sqlSession=getSqlSession();
        try{
            SysRole sysRole=new SysRole();
            sysRole.setRoleName("role1");
            //sysRole.setEnabled(1);
            sysRole.setCreateBy(1l);
            sysRole.setCreateTime(new Date());
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            int result=roleMapper.insert2(sysRole);
            Assert.assertEquals(1,result);
            Assert.assertNotNull(sysRole.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3(){
        SqlSession sqlSession=getSqlSession();
        try{
            SysRole sysRole=new SysRole();
            sysRole.setRoleName("role1");
            //sysRole.setEnabled(1);
            sysRole.setCreateBy(1l);
            sysRole.setCreateTime(new Date());
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            int result=roleMapper.insert3(sysRole);
            Assert.assertEquals(1,result);
            Assert.assertNotNull(sysRole.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession=getSqlSession();
        try{
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole role=roleMapper.selectById(1l);
            role.setRoleName("管理员2");
            roleMapper.updateById(role);
            role=roleMapper.selectById(1l);
            Assert.assertEquals("管理员2",role.getRoleName());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDelete(){
        SqlSession sqlSession=getSqlSession();
        try{
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            int result=roleMapper.deleteById(1l);
            Assert.assertEquals(1,result);
            SysRole role=roleMapper.selectById(1l);
            Assert.assertNull(role);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllRoleAndPrivileges(){
        SqlSession sqlSession=getSqlSession();
        try{
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList=roleMapper.selectAllRoleAndPrivileges();
            for(SysRole role:roleList){
                System.out.println("角色名："+role.getRoleName());
                for(SysPrivilege privilege:role.getPrivilegeList())
                    System.out.println("权限名："+privilege.getPrivilegeName());
            }
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserIdChoose(){
        SqlSession sqlSession=getSqlSession();
        try{
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole role=roleMapper.selectById(2L);
            //role.setEnabled(0);
            roleMapper.updateById(role);
            List<SysRole> roleList=roleMapper.selectRoleByUserIdChoose(1L);
            for(SysRole r:roleList){
                System.out.println("角色名："+r.getRoleName());
                if(r.getId().equals(1L))
                    Assert.assertNotNull(r.getPrivilegeList());
                if(r.getId().equals(2L)){
                    Assert.assertNull(r.getPrivilegeList());
                    continue;
                }
                for(SysPrivilege privilege:r.getPrivilegeList())
                    System.out.println("权限名："+privilege.getPrivilegeName());
            }
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession=getSqlSession();
        try{
            RoleMapper roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole role=roleMapper.selectById(2L);
            Assert.assertEquals(Enabled.enabled,role.getEnabled());
            role.setEnabled(Enabled.disabled);
            roleMapper.updateById(role);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
