package com.board.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private final RequestCache requestCache = new HttpSessionRequestCache();
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		// 로그인 실패 기록 제거
		clearFailSession(request);
		
		// prevPage가 존재할 경우 = 사용자가 직접 로그인 페이지로 이동한 경우 
		// 해당 uri는 저장해두고 session의 prevPage는 삭제한다.
		String prevPage = (String) request.getSession().getAttribute("prevPage");
		if(prevPage != null) {
			request.getSession().removeAttribute("prevPage");
		}
		
		// prevPage가 없을 경우 기본 uri (메인 페이지)
		String uri = "/";
		
		// savedRequest가 존재할 경우 = 사용자 권한이 없는 페이지에 접근해 로그인 페이지로 이동된 경우
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest != null) {
			uri = savedRequest.getRedirectUrl(); // 이전 요청 불러오기
		} else if (prevPage != null && !prevPage.equals("")) {
			// savedRequest는 없지만 prevPage가 있을 경우
			uri = prevPage;
		}
		
		redirectStrategy.sendRedirect(request, response, uri);
	}
	
	// 로그인 실패 후 성공 시 남아있던 에러 세션 제거
	protected void clearFailSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}

}
