package com.cos.blogapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blogapp.domain.user.User;
import com.cos.blogapp.domain.user.UserRepository;
import com.cos.blogapp.handler.exception.MyAsyncNotFoundException;
import com.cos.blogapp.handler.exception.MyNotFoundException;
import com.cos.blogapp.util.MyAlgorithm;
import com.cos.blogapp.util.SHA256;
import com.cos.blogapp.web.dto.JoinReqDto;
import com.cos.blogapp.web.dto.LoginReqDto;
import com.cos.blogapp.web.dto.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	// 생성자 주입 (DI)
	private final UserRepository userRepository;
	
	// 이건 하나의 서비스(핵심로직)인가? (principal 값 변경, update치고, 세션값 변경:request관련 ->(x))
	
	@Transactional(rollbackFor = MyAsyncNotFoundException.class)
	public void 회원수정(int id, User principal, UserUpdateDto dto) {
		// 권한
		User userEntity = userRepository.findById(id)
				.orElseThrow(() -> new MyAsyncNotFoundException("해당 계정을 찾을 수가 없습니다."));
		if (principal.getId() != userEntity.getId()) {
			throw new MyAsyncNotFoundException("회원 정보를 수정할 권한이 없습니다.");
		}
		
		principal.setEmail(dto.getEmail());		
		userRepository.save(principal);
	}
	
	public User 로그인(LoginReqDto dto) {		
		return userRepository.mLogin(	dto.getUsername(), SHA256.encrypt(dto.getPassword(), MyAlgorithm.SHA256));
	}	
	
	@Transactional(rollbackFor = MyNotFoundException.class)
	public void 회원가입(JoinReqDto dto) {
		String encpassword = SHA256.encrypt(dto.getPassword(), MyAlgorithm.SHA256);
		dto.setPassword(encpassword);
		userRepository.save(dto.toEntity());
	}
}
