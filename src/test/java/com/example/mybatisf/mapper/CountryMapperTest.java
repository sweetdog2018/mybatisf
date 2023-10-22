package com.example.mybatisf.mapper;

import com.example.mybatisf.model.Country;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import testC.dao.CountryMapper;
import testC.model.CountryExample;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CountryMapperTest extends BaseMapperTest {

    @Test
    public void testSelectAll(){
        SqlSession sqlSession=getSqlSession();
        try{
            List<Country> countryList=sqlSession.selectList("com.example.mybatisf.mapper.CountryMapper.selectAll");
            printCountryList(countryList);
        }finally {
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countryList){
        for(Country country:countryList)
            System.out.printf("%-4d%4s%4s\n",country.getId(),country.getCountryname(),country.getCountrycode());
    }

    private void printCountryList2(List<testC.model.Country> countryList){
        for(testC.model.Country country:countryList)
            System.out.printf("%-4d%4s%4s\n",country.getId(),country.getCountryname(),country.getCountrycode());
    }

    @Test
    public void testExample(){
        SqlSession sqlSession=getSqlSession();
        try {
            testC.dao.CountryMapper countryMapper=sqlSession.getMapper(testC.dao.CountryMapper.class);
            CountryExample example=new CountryExample();
            example.setOrderByClause("id desc,countryname asc");
            example.setDistinct(true);
            CountryExample.Criteria criteria=example.createCriteria();
            criteria.andIdGreaterThanOrEqualTo(1);
            criteria.andIdLessThan(4);
            criteria.andCountrycodeLike("%U%");
            CountryExample.Criteria or=example.or();
            or.andCountrynameEqualTo("中国");
            List<testC.model.Country> countryList=countryMapper.selectByExample(example);
            printCountryList2(countryList);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByExampleSelective() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 CountryMapper 接口
            testC.dao.CountryMapper countryMapper = sqlSession.getMapper(testC.dao.CountryMapper.class);
            //创建 Example 对象
            CountryExample example = new CountryExample();
            //创建条件，只能有一个 createCriteria
            CountryExample.Criteria criteria = example.createCriteria();
            //更新所有 id > 2 的国家
            criteria.andIdGreaterThan(2);
            //创建一个要设置的对象
            testC.model.Country country = new testC.model.Country();
            //将国家名字设置为 China
            country.setCountryname("China");
            //执行查询
            countryMapper.updateByExampleSelective(country, example);
            //在把符合条件的结果输出查看
            printCountryList2(countryMapper.selectByExample(example));
        } finally {
            sqlSession.rollback();
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteByExample() {
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 CountryMapper 接口
            testC.dao.CountryMapper countryMapper = sqlSession.getMapper(testC.dao.CountryMapper.class);
            //创建 Example 对象
            CountryExample example = new CountryExample();
            //创建条件，只能有一个 createCriteria
            CountryExample.Criteria criteria = example.createCriteria();
            //删除所有 id > 2 的国家
            criteria.andIdGreaterThan(2);
            //执行查询
            countryMapper.deleteByExample(example);
            //使用 countByExample 查询符合条件的数量，因为删除了，所以这里应该是 0
            Assert.assertEquals(0, countryMapper.countByExample(example));
        } finally {
            sqlSession.rollback();
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
