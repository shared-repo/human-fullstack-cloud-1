package com.humanda6.demoweb2.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.humanda6.demoweb2.dto.MemberDto;
import com.humanda6.demoweb2.dto.RoleDto;

public class DemoWebUserDetails implements UserDetails {

	private MemberDto member;
	
	public DemoWebUserDetails() {}
	public DemoWebUserDetails(MemberDto member) {
		this.member = member;
	}	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> grants = new ArrayList<>();
		for (RoleDto role : member.getRoles()) {
			grants.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return grants;
	}

	@Override
	public String getPassword() {
		return member.getPasswd();
	}

	@Override
	public String getUsername() {
		return member.getEmail();
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
	public boolean isEnabled() {
		// return member.isActive();
		return true;
	}
	
}
