package com.testJunit;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.service.BoardService;
import com.board.service.BoardServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//@ExtendWith(MockitoExtension)
public class BoardServiceTest {	
	@Autowired
	private BoardServiceImpl service;
	
	@Test
	public void getListTest() {
		//given
		Criteria cri = new Criteria(1,10);
		
		//when
		List<BoardDTO> list = service.getList(cri); 
		
		//then
		list.forEach(board -> System.out.println(board));
	}


}
