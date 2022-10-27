package com.testJunit;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.dao.MemberDAOImpl;

import lombok.extern.log4j.Log4j2;

@ContextConfiguration(locations={
	"file:src/main/webapp/WEB-INF/spring/*.xml"}) //root-context.xml
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
public class MemberDAOTest {
	@Autowired
	private MemberDAOImpl dao;
	
	@DisplayName("아이디, 이메일, 닉네임 중복 확인")
	@Test
	public void checkAailabilityTest() {
		//given
		Map<String, String> map = new HashMap<String, String>();
		map.put("field", "mem_email");
		map.put("value", "testid01@gmail.com");
		
		//when
		int result = dao.checkAvailability(map);
		
		//then
		log.info(result);
	}
}
