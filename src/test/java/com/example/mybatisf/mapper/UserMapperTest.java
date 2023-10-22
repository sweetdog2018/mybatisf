package com.example.mybatisf.mapper;

import com.example.mybatisf.model.SysPrivilege;
import com.example.mybatisf.model.SysRole;
import com.example.mybatisf.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.*;

public class UserMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=userMapper.selectById(1l);
            Assert.assertNotNull(user);
            Assert.assertEquals("admin",user.getUserName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList=userMapper.selectAll();
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size()>0);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void  testSelectRolesByUserId(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList=userMapper.selectRolesByUserId(1l);
            Assert.assertNotNull(roleList);
            Assert.assertTrue(roleList.size()>0);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            int result=userMapper.insert(user);
            Assert.assertEquals(1,result);
            Assert.assertNull(user.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            int result=userMapper.insert2(user);
            Assert.assertEquals(1,result);
            user=userMapper.selectById(user.getId());
            Assert.assertEquals("test@mybatis.tk",user.getUserEmail());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            int result=userMapper.insert3(user);
            Assert.assertEquals(1,result);
            Assert.assertNotNull(user.getId());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=userMapper.selectById(1l);
            Assert.assertEquals("admin",user.getUserName());
            user.setUserName("admin_test");
            int result=userMapper.updateById(user);
            Assert.assertEquals(1,result);
            user=userMapper.selectById(1l);
            Assert.assertEquals("admin_test",user.getUserName());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=userMapper.selectById(1L);
            Assert.assertNotNull(user);
            Assert.assertEquals(1,userMapper.deleteById(1l));
            Assert.assertNull(userMapper.selectById(1L));
            SysUser user2=userMapper.selectById(1001l);
            Assert.assertNotNull(user2);
            Assert.assertEquals(1,userMapper.deleteById(1001l));
            Assert.assertNull(userMapper.selectById(1001l));
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRole> list=userMapper.selectRolesByUserIdAndRoleEnabled(1l,1);
            Assert.assertNotNull(list);
            Assert.assertTrue(list.size()>0);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUser(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser sysUser=new SysUser();
            sysUser.setUserName("ad");
            List<SysUser> list=userMapper.selectByUser(sysUser);
            Assert.assertTrue(list.size()>0);
            sysUser=new SysUser();
            sysUser.setUserEmail("test@mybatis.tk");
            list=userMapper.selectByUser(sysUser);
            Assert.assertTrue(list.size()>0);
            sysUser=new SysUser();
            sysUser.setUserName("ad");
            sysUser.setUserEmail("test@mybatis.tk");
            list=userMapper.selectByUser(sysUser);
            Assert.assertTrue(list.size()==0);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelective(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=new SysUser();
            user.setId(1l);
            user.setUserEmail("test@mybatis.tk");
            int result=userMapper.updateByIdSelective(user);
            Assert.assertEquals(1,result);
            user=userMapper.selectById(1l);
            Assert.assertEquals("admin",user.getUserName());
            Assert.assertEquals("test@mybatis.tk",user.getUserEmail());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrUserName( ) {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUserName("admin");
            SysUser user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            query.setId(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            query.setUserName(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdList(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<Long> idList=new ArrayList<Long>();
            idList.add(1L);
            idList.add(1001L);
            List<SysUser> userList=userMapper.selectByIdList(idList);
            Assert.assertEquals(2,userList.size());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertList(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList=new ArrayList<SysUser>();
            for(int i=0;i<2;++i){
                SysUser user=new SysUser();
                user.setUserName("test"+i);
                user.setUserPassword("123456");
                userList.add(user);
            }
            int result=userMapper.insertList(userList);
            for(SysUser sysUser:userList)
                System.out.println(sysUser.getId());
            Assert.assertEquals(2,result);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("id",1L);
            map.put("user_email","test@mybatis.tk");
            map.put("user_password","12345678");
            userMapper.updateByMap(map);
            SysUser user=userMapper.selectById(1L);
            Assert.assertEquals("test@mybatis.tk",user.getUserEmail());
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    /*@Test
    public void testSelectUserAndRoleById(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=userMapper.selectUserAndRoleById2(1001L);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=userMapper.selectUserAndRoleByIdSelect(1001L);
            Assert.assertNotNull(user);
            System.out.println("调用user.equals(null)");
            user.equals(null);
            System.out.println("调用user.getRole()");
            Assert.assertNotNull(user.getRole());
        }finally {
            sqlSession.close();
        }
    }*/

    @Test
    public void testSelectAllUserAndRoles(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList=userMapper.selectAllUserAndRoles();
            System.out.println("用户数："+ userList.size());
            for(SysUser user:userList){
                System.out.println("用户名："+user.getUserName());
                for(SysRole role:user.getRoleList()){
                    System.out.println("角色名："+role.getRoleName());
                    for(SysPrivilege privilege:role.getPrivilegeList())
                        System.out.println("权限名："+privilege.getPrivilegeName());
                }
            }
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRoleSelect(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=userMapper.selectAllUserAndRoleSelect(1L);
            for(SysRole role:user.getRoleList()){
                System.out.println("角色名："+role.getRoleName());
                for(SysPrivilege privilege:role.getPrivilegeList())
                    System.out.println("权限名："+privilege.getPrivilegeName());
            }
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserById(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=new SysUser();
            user.setId(1L);
            userMapper.selectUserById(user);
            Assert.assertNotNull(user.getUserName());;
            System.out.println(user.getUserName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserPage(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("userName","ad");
            map.put("offset",0);
            map.put("limit",10);
            List<SysUser> userList=userMapper.selectUserPage(map);
            Long total=(Long)map.get("total");
            System.out.println("总数："+total);
            for(SysUser user:userList)
                System.out.println("用户名："+user.getUserName());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertAndDelete(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser user=new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("123");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1,2,3});
            userMapper.insertUserAndRoles(user,"1,2");
            Assert.assertNotNull(user.getId());
            Assert.assertNotNull(user.getCreateTime());
            userMapper.deleteUserById(user.getId());
        }finally {
            sqlSession.close();
        }
    }
}
