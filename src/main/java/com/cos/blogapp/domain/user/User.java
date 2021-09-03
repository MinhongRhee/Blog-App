package com.cos.blogapp.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 테이블 모델
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor // 기본 생성자
@Data
@Entity // spring 이 유저테이블 생성(서버 실행 시)
public class User { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //PK (자동증가 번호)
	private String username; // 아이디
	private String password; //
	private String email;
}
