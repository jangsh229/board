package com.board.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {
	int seq;
	String subject;
	String content;
	Date reg_date;
	int readCount;
	int mem_seq;
	String name;
}
