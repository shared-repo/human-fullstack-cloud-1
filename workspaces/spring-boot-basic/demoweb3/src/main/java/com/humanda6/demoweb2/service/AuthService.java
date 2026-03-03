package com.humanda6.demoweb2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanda6.demoweb2.dao.AuthDao;
import com.humanda6.demoweb2.dto.MemberDto;
import com.humanda6.demoweb2.entity.MemberEntity;
import com.humanda6.demoweb2.mapper.MemberMapper;
import com.humanda6.demoweb2.repository.MemberRepository;

@Service("authService") // 스프링 프레임워크에 클래스를 등록하는 설정 --> new 하지 않고 주입 가능
public class AuthService {
	
	@Autowired // 스프링이 관리하는 AuthDao 객체를 주입하세요
	private AuthDao authDao;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberMapper memberMapper;
	
	public void signup(MemberDto member) {		
		// 1. jdbc 사용
		// AuthDao authDao = new AuthDao(); // 파이썬에서 구현한다면 --> authDao = AuthDao();
		// authDao.insertMember(member);
		
		// 2. jpa 사용
////		MemberEntity memberEntity = new MemberEntity();
////		memberEntity.setEmail(member.getEmail());
////		memberEntity.setPasswd(member.getPasswd());
////		memberEntity.setUserName(member.getUserName());
//		
//		MemberEntity memberEntity = new MemberEntity(member);
//		
//		memberRepository.save(memberEntity);
		
		// 3. mybatis 사용
		memberMapper.insertMember(member);
		
	}

	public MemberDto findMemberByEmailAndPasswd(String email, String passwd) {
		// 1. jdbc 사용
		// AuthDao authDao = new AuthDao(); // 파이썬에서 구현한다면 --> authDao = AuthDao();
//		MemberDto member = authDao.selectMemberByIdAndPasswd(email, passwd);
//		return member;
		
		// 2. jpa 사용
//		MemberEntity memberEntity = memberRepository.findByEmailAndPasswd(email, passwd);
//		if (memberEntity == null) {
//			return null;
//		} else {
////			MemberDto memberDto = new MemberDto();
////			memberDto.setEmail(memberEntity.getEmail());
////			memberDto.setUserName(memberEntity.getUserName());
////			memberDto.setPasswd(memberEntity.getPasswd());
//			
//			MemberDto memberDto = memberEntity.toMemberDto();
//			return memberDto;
//		}

		// 3. mybatis 사용
		MemberDto member = memberMapper.selectMemberByEmailAndPasswd(email, passwd);
		return member;
		
	}

}














