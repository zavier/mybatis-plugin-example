package com.github.zavier.mybatis.plugin;


import com.github.zavier.mapper.EmployeesMapper;
import com.github.zavier.model.Employees;
import com.github.zavier.model.EmployeesExample;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PageInterceptorTest {

    private SqlSession sqlSession;
    private EmployeesMapper employeesMapper;

    @Before
    public void before() {
        sqlSession = Mybatis3Utils.getCurrentSqlSession();
        employeesMapper = sqlSession.getMapper(EmployeesMapper.class);
    }

    @After
    public void after() {
        Mybatis3Utils.closeCurrentSession();
    }

    @Test
    public void test() {
        PageInterceptor.page(1, 2);
        final EmployeesExample example = new EmployeesExample();
        final List<Employees> employees = employeesMapper.selectByExample(example);
        Assert.assertEquals(2, employees.size());
    }

}