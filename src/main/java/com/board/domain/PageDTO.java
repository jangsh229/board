package com.board.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PageDTO {
	private int pageNum; // 현재 페이지
	private int amount; // 페이지에 보여줄 데이터 갯수
	private int total; // 총 DB 갯수
	private int pageBtnQty; // 페이지 버튼 갯수
	private int startPage;
	private int endPage;
	private boolean prev, next;

	public PageDTO() {

	}

	public PageDTO(Criteria cri, int pageBtnQty, int total) {
		this.pageNum = cri.getPageNum();
		this.amount = cri.getAmount();
		this.total = total;
		this.pageBtnQty = pageBtnQty;
		this.startPage = (pageNum - 1) / pageBtnQty * pageBtnQty + 1;
		this.endPage = startPage + pageBtnQty - 1;

		int realEnd = (int) Math.ceil((total * 1.0) / amount);
		if (this.endPage > realEnd) {
			this.endPage = realEnd;
		}

		this.prev = this.startPage > pageBtnQty;
		this.next = this.endPage < realEnd;
	}
}
