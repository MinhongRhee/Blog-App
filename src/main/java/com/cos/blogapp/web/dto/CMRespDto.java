package com.cos.blogapp.web.dto;

import com.cos.blogapp.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CMRespDto<T> { // Common Response Data Transfer Object
	private int code; // 1 성공, -1 실패
	private String msg;
	private T body;
}
