package com.board.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyDTO {
	int rep_seq;
	String rep_content;
	int board_seq;
	int mem_seq;
	Date reg_date;
	Date chg_date;
	int deleted; // default 0, 삭제 후 1
	
	String mem_name;
}
