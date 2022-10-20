package com.board.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PageDTO {
	private Criteria cri;
	private int total; // 총 DB 갯수
	private int pageBtnQty; // 페이지 버튼 갯수
	private int startPage;
	private int endPage;
	private boolean prev, next;

	public PageDTO() {

	}

	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		this.pageBtnQty = 5;
		this.startPage = (cri.getPg() - 1) / pageBtnQty * pageBtnQty + 1;
		this.endPage = startPage + pageBtnQty - 1;

		int realEnd = (int) Math.ceil((total * 1.0) / cri.getAmount());
		if (this.endPage > realEnd) {
			this.endPage = realEnd;
		}

		this.prev = this.startPage > pageBtnQty;
		this.next = this.endPage < realEnd;
	}
}
