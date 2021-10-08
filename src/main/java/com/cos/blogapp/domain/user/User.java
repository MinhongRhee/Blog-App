package com.cos.blogapp.domain.user;

<<<<<<< HEAD
=======
import javax.persistence.Column;
>>>>>>> ab9a87318f66eb588acc4ebbe69d097c8fe4da50
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 테이블 모델
<<<<<<< HEAD
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
=======
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //PK (자동증가 번호)
	@Column(nullable = false, length = 20, unique = true)
	private String username; // 아이디
	@Column(nullable = false, length = 70)
	private String password;
	@Column(nullable = false, length = 50)
>>>>>>> ab9a87318f66eb588acc4ebbe69d097c8fe4da50
	private String email;
}
