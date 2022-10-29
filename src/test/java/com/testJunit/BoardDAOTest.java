package com.testJunit;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.dao.BoardDAOImpl;
import com.board.domain.BoardDTO;
import com.board.domain.Criteria;

import lombok.extern.log4j.Log4j2;

@ContextConfiguration(locations={
	"file:src/main/webapp/WEB-INF/spring/*.xml"}) //root-context.xml
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
public class BoardDAOTest {	
	@Autowired
	private BoardDAOImpl dao;
	
	@DisplayName("검색결과,페이징된 게시글 불러오기")
	@Test
	public void getListTest() {
		//given
		Criteria cri = new Criteria(1,10);
		//cri.setType("C");
		//cri.setKeyword("테스트");
		
		//when
		List<BoardDTO> list = dao.getList(cri); 
		
		//then
		assertNotNull(list);
		for(BoardDTO board : list) {
			log.info(board);
		}
	}
}
