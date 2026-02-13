package com.myweb.bootmywebref.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myweb.bootmywebref.entity.ToDoEntity;

// ToDoEntity 클래스의 정보를 사용해서 데이터베이스 테이블의 데이터를 CRUD하는 기능을 자동 구현
public interface ToDoRepository extends JpaRepository<ToDoEntity, Integer> {

}
