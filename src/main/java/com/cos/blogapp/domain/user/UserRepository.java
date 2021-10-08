package com.cos.blogapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

<<<<<<< HEAD
// save() 인서트, 업데이트
// findById(1) 한건 셀렉트
// findAll() 전체 셀렉트
// deleteById(1) 한건 삭제
// DAO
// @Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value = "insert into user (username, password, email) values (:username, :password, :email)", nativeQuery = true)
	void join(String username, String password, String email);
	
	@Query(value = "select * from user where username=:username and password=:password", nativeQuery = true)
	User mLogin(String username, String password);
}
=======
// save(user) 인서트, 업데이트
// findById(1) 한건셀렉트
// findAll() 전체셀렉트
// deleteById(1) 한건 삭제
// DAO
//@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query(value = "insert into user (username, password, email) values(:username, :password, :email)", nativeQuery = true)
	void join(String username, String password, String email);
	
	@Query(value = "select * from user where username = :username and password = :password", nativeQuery = true)
	User mLogin(String username, String password);
}



>>>>>>> ab9a87318f66eb588acc4ebbe69d097c8fe4da50
