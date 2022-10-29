package com.board.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MemberDTO implements UserDetails{
	private int mem_seq;
	private String mem_id;
	private String mem_pwd;
	private String mem_email;
	private String mem_name;
	private String mem_type;
	private Date reg_date;
	private Date chg_date;
	private String auth;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roleGrant = "ROLE_" + auth;
		GrantedAuthority myGrant = new SimpleGrantedAuthority(roleGrant);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(myGrant);
		return authorities;
	}
	@Override
	public String getPassword() {
		return mem_pwd;
	}
	@Override
	public String getUsername() {
		return mem_id;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
