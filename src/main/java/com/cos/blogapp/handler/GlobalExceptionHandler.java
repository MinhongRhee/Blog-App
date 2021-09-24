package com.cos.blogapp.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blogapp.handler.exception.MyAsyncNotFoundException;
import com.cos.blogapp.handler.exception.MyNotFoundException;
import com.cos.blogapp.util.Script;
import com.cos.blogapp.web.dto.CMRespDto;

// @ControllerAdvice 이 친구는 1. 익셉션 핸들링, 2. @Controller 의 역활까지 한다.
@ControllerAdvice
public class GlobalExceptionHandler {
	
	// 어떤 Exception은 파일~~
	// 어떤 Exception은 데이터~
	// 어떤 Exception은 ~
	// 어떤 Exception은 ~
	
	@ExceptionHandler(value = MyNotFoundException.class)
	public @ResponseBody String error1(MyNotFoundException e) {
		System.out.println("오류 터졌어 : " + e.getMessage());
		return Script.href("/", e.getMessage());
	}
	
	@ExceptionHandler(value = MyAsyncNotFoundException.class)
	public @ResponseBody CMRespDto error2(MyAsyncNotFoundException e) {
		System.out.println("오류 터졌어 : " + e.getMessage());
		return new CMRespDto(-1, e.getMessage(), null);
	}
}
