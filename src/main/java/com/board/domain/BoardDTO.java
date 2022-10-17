package com.board.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {
	int seq;
	String subject;
	String content;
	String name;
	Date reg_date;
	int readCount;
	
}
