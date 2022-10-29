package com.board.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.dao.MemberDAO;
import com.board.domain.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO dao;
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public Integer checkAvailability(String field, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("field", field);
		map.put("value", value);
		return dao.checkAvailability(map);
	}
	
	@Override
	public int certifyEmail(String email) {
		// 인증번호(난수) 생성
		Random random = new Random();
		int certCode = random.nextInt(888888) + 111111;
		// 메일 내용
		String content = new StringBuffer().append("<h1>[💙Board 이메일 인증 코드입니다]</h1>")
				.append("<p>아래의 인증번호를 입력하시면 이메일 인증이 완료됩니다.</p>")
				.append("<p><strong>" + certCode + "</strong></p>").toString();

		// 메일 전송
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setSubject("[💙Board] 이메일 인증 코드");
			helper.setText(content,true);
			helper.setFrom("96_0229@naver.com");
			helper.setTo(email);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return certCode;
	}
	
	@Override
	public Integer regist(MemberDTO dto) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		dto.setMem_pwd(pe.encode(dto.getMem_pwd()));
		return dao.regist(dto);
	}
	
	@Override
	public MemberDTO selectById(String id) {
		return dao.selectById(id);
	}
}
