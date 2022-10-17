package com.board.domain;

import lombok.Data;

@Data
public class Criteria {
	private int pageNum; //현재 페이지
	private int amount; //한 페이지에 보여줄 데이터 갯수
	
	public Criteria() {
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}	
}
