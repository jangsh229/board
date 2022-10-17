package com.testJunit;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class DataSourceTest {
	@Autowired
	private DataSource ds;
	
	@Autowired
	private SqlSessionFactory sf;
	
	@DisplayName("dataSource 연결 확인")
	@Test
	public void dsTest()throws Exception{
		assertNotNull(ds);
	}
	
	@DisplayName("mybatis 연결 확인")
	@Test
	public void sfTest()throws Exception{
		assertNotNull(sf);
	}	
}
