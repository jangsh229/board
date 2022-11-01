package com.testJunit;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.dao.ReplyDAOImpl;
import com.board.domain.ReplyDTO;

import lombok.extern.log4j.Log4j2;

@ContextConfiguration(locations={
"file:src/main/webapp/WEB-INF/spring/*.xml"}) //root-context.xml
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
public class ReplyDAOTest {
	@Autowired
	private ReplyDAOImpl dao;
	
	@DisplayName("댓글 작성")
	//@Test
	public void registTest() {
		//given
		ReplyDTO dto = new ReplyDTO();
		dto.setRep_content("대단한걸");
		dto.setBoard_seq(163);
		dto.setMem_seq(22);
		
		//when
		
		//then
		log.info(dao.regist(dto));		
	}
	
	@DisplayName("댓글 목록")
	@Test
	public void list() {
		int boardSeq = 162;
		List<ReplyDTO> list = dao.list(boardSeq);
		for(ReplyDTO reply : list) {
			log.info(reply);
		}
	}
	
}
