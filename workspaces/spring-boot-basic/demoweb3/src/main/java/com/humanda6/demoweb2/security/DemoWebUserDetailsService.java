package com.humanda6.demoweb2.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.humanda6.demoweb2.dto.MemberDto;
import com.humanda6.demoweb2.dto.RoleDto;
import com.humanda6.demoweb2.mapper.MemberMapper;

import lombok.Setter;

public class DemoWebUserDetailsService implements UserDetailsService {

	@Setter(onMethod_ = { @Autowired })
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		DemoWebUserDetails userDetails = null;
		MemberDto member = memberMapper.selectMemberByEmail(username);
		if (member == null) {
			throw new UsernameNotFoundException(username);
		} else {
			List<RoleDto> roles = memberMapper.selectRolesByEmail(username);
			member.setRoles(roles);
			userDetails = new DemoWebUserDetails(member);
			return userDetails;
		}
	}

}
