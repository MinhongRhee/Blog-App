package com.cos.blogapp.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blogapp.domain.user.User;
import com.cos.blogapp.domain.user.UserRepository;
import com.cos.blogapp.util.Script;
import com.cos.blogapp.web.dto.JoinReqDto;
import com.cos.blogapp.web.dto.LoginReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserRepository userRepository;
	private final HttpSession session;
	
	@GetMapping({"/", "/home"})
	public String home() {
		return "home";
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
	public @ResponseBody String login(@Valid LoginReqDto dto, BindingResult bindingResult, Model model) {	
		System.out.println("에러사이즈:" + bindingResult.getFieldErrors().size());

		if( bindingResult.hasErrors() ) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("필드:" + error.getField());
				System.out.println("메시지:" + error.getDefaultMessage());
			}	
			model.addAttribute("errorMap", errorMap);
			return Script.back(errorMap.toString());
		} 

		User userEntity =  userRepository.mLogin(dto.getUsername(), dto.getPassword());
		
		if(userEntity == null) {
			return Script.back("아이디 혹은 비밀번호를 잘못 입력하였습니다");
		} else { 
			
			session.setAttribute("principal", userEntity);
			return Script.href("/","로그인 되었습니다");
		}		
	}
	
	@PostMapping("/join")
	public @ResponseBody String join(@Valid JoinReqDto dto, BindingResult bindingResult, Model model) { 
		// @Valid를 걸면 JoinReqDto 에 있는 annotation을 자동으로 검증한다
		// 검증을 통해 실패한 것을 bindingResult에 담아준다. 
		
		// 유효성 검사 실패 - 자바스크립트 응답(경고창, 뒤로가기 )
		
		// System.out.println("에러사이즈:" + bindingResult.getFieldErrors().size());
		
		if( bindingResult.hasErrors() ) {
			Map<String, String> errorMap = new HashMap<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("필드:" + error.getField());
				System.out.println("메시지:" + error.getDefaultMessage());
			}
			
			model.addAttribute("errorMap", errorMap);
			return Script.back(errorMap.toString());
		}
		
		userRepository.save(dto.toEntity());
		return Script.href( "/loginForm" ); // 리다이렉션 (300)
	}
	
}



