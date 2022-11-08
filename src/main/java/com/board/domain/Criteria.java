package com.board.domain;

import lombok.Data;

@Data
public class Criteria {
	private int pg; //현재 페이지
	private int amount; //한 페이지에 보여줄 데이터 갯수
	private int offset; //페이징 limit offset
	private String type; //검색조건
	private String keyword; //검색 키워드
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pg, int amount) {
		this.pg = pg;
		this.amount = amount;
	}	
}
