package com.board.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MemberDTO {
	private int mem_seq;
	private String mem_id;
	private String mem_pwd;
	private String mem_email;
	private String mem_name;
	private String mem_type;
	private Date reg_date;
	private Date chg_date;
}
