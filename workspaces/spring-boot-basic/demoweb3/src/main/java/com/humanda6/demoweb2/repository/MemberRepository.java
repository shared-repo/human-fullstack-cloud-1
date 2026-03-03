package com.humanda6.demoweb2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humanda6.demoweb2.entity.MemberEntity;

// MemberEntity와 연결된 테이블에 데이터를 처리하는 코드를 자동으로 생성하는 인터페이스
public interface MemberRepository extends JpaRepository<MemberEntity, String> {
	
	// JpaRepository가 자동으로 제공하지 않는 기능은 직접 작성
	// 형태만 작성하면 내용은 JPA (Hibernate)가 자동으로 구현
	public MemberEntity findByEmailAndPasswd(String email, String passwd);

}
