package com.cos.blogapp.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blogapp.domain.user.User;
import com.cos.blogapp.domain.user.UserRepository;
import com.cos.blogapp.handler.exception.MyAsyncNotFoundException;
import com.cos.blogapp.util.MyAlgorithm;
import com.cos.blogapp.util.SHA256;
import com.cos.blogapp.util.Script;
import com.cos.blogapp.web.dto.CMRespDto;
import com.cos.blogapp.web.dto.JoinReqDto;
import com.cos.blogapp.web.dto.LoginReqDto;
import com.cos.blogapp.web.dto.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserRepository userRepository;
	private final HttpSession session;
	
	@PutMapping("/user/{id}")
	public @ResponseBody CMRespDto<String> update(@PathVariable int id, @Valid @RequestBody UserUpdateDto dto, BindingResult bindingResult) {
		// 인증
		User principal = (User) session.getAttribute("principal");
		if (principal == null) { // 로그인 안됨
			throw new MyAsyncNotFoundException("인증이 되지 않았습니다.");
		}
		
		// 권한
		User userEntity = userRepository.findById(id)
				.orElseThrow(() -> new MyAsyncNotFoundException("해당 계정을 찾을 수가 없습니다."));
		if (principal.getId() != userEntity.getId()) {
			throw new MyAsyncNotFoundException("회원 정보를 수정할 권한이 없습니다.");
		}
		
		// 유효성 검사
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			throw new MyAsyncNotFoundException(errorMap.toString());
		}
		
		// 핵심로직
		principal.setEmail(dto.getEmail());
		session.setAttribute("principal", principal); // 세션값 변경
		
		userRepository.save(principal);
		
		return new CMRespDto<>(1, "성공", null);
	}
	
	@GetMapping("/user/{id}")
	public String userinfo(@PathVariable int id) {
		// 기본은 userRepository.findById(id) DB에서 가져와야 함.
		// 편법은 세션값을 가져올 수 도 있다.
		
		return "user/updateForm";
	}
	
	@GetMapping("/logout")
	public String logout() {
		// return "board/list"; // 게시글 목록 화면에 데이터 x
		session.invalidate(); // 세션 무효화(jsessionid에 있는 값을 비우는 것)
		return "redirect:/";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@PostMapping("/login")
	public @ResponseBody String login(@Valid LoginReqDto dto, BindingResult bindingResult) {	
		System.out.println("에러사이즈:" + bindingResult.getFieldErrors().size());

		if( bindingResult.hasErrors() ) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}	
			return Script.back(errorMap.toString());
		} 
		
		User userEntity =  
				userRepository.mLogin(
						dto.getUsername(), 
						SHA256.encrypt(dto.getPassword(), MyAlgorithm.SHA256));
		
		if(userEntity == null) { 	// username, password 잘못 기입
			return Script.back("아이디 혹은 비밀번호를 잘못 입력하였습니다");
		} else { 
			// 세션이 날라가는 조건 : 1. session.invalidate(), 2. 브라우저를 닫으면 날라감
			session.setAttribute("principal", userEntity);
			return Script.href("/","로그인 성공");
		}		
	}
	
	@PostMapping("/join")
	public @ResponseBody String join(@Valid JoinReqDto dto, BindingResult bindingResult) { 
		
		if( bindingResult.hasErrors() ) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return Script.back(errorMap.toString());
		}
		
		String encpassword = SHA256.encrypt(dto.getPassword(), MyAlgorithm.SHA256);
		
		dto.setPassword(encpassword);
		userRepository.save(dto.toEntity());
		return Script.href( "/loginForm" ); // 리다이렉션 (300)
	}
	
}



