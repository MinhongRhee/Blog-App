package com.cos.blogapp.test;

// 1.8 람다식
// 1. 함수를 넘기는게 목적 (익명함수 : 람다함수)
// 2. 인터페이스에 함수가 무조건 하나여야 함.
// 3. 쓰면 코드가 간결해지고, 타입을 몰라도 된다.

interface MySupplier {
	void get(); 
}

public class LamdaTest {
	
	static void start(MySupplier s) {
		s.get();
	}
	
	public static void main(String[] args) {
		// 람다식 사용 시 스택을 넘길 수 있다
		start(()->{System.out.println("get함수 호출됨");});
	}
}
