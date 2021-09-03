package com.cos.blogapp;

import java.util.ArrayList;
import java.util.List;

/*{
    "이름": "홍길동",
    "나이": 25,
    "성별": "여",
    "주소": "서울특별시 양천구 목동",
    "특기": ["농구", "도술"],
    "가족관계": {"아버지": "홍판서", "어머니": "춘섬"},
    "회사": "경기 수원시 팔달구 우만동"
 }*/

class 가족관계 {
	String 아버지 = "홍판서";
	String 어머니 = "춘섬";
}

public class Test {
	String 이름 = "홍길동";
	int 나이 = 25;
	String 성별 = "여";
	String 주소 = "서울특별시 양천구 목동";
	List<String> 특기 = new ArrayList<>();
	가족관계 가족 = new 가족관계();
	String 회사 = "경기 수원시 팔달구 우만동";
}
