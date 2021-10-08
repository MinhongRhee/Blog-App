package com.cos.blogapp.domain.board;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
=======
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.cos.blogapp.domain.comment.Comment;
import com.cos.blogapp.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
>>>>>>> ab9a87318f66eb588acc4ebbe69d097c8fe4da50

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
// 데이터 모델
@AllArgsConstructor
@NoArgsConstructor
@Data
=======
@AllArgsConstructor
@NoArgsConstructor
@Data // getter, setter, toString
>>>>>>> ab9a87318f66eb588acc4ebbe69d097c8fe4da50
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //PK (자동증가 번호)
<<<<<<< HEAD
	private String title;
	private String content;
=======
	@Column(nullable = false, length = 50)
	private String title; // 제목
	@Lob
	private String content;
	
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	// 양방향 매핑
	// mappedBy 에는 FK의 주인의 변수이름을 추가한다.
	@JsonIgnoreProperties({"board"}) // comments 객체 내부의 필드를 제외시키는 법
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
	private List<Comment> comments;

>>>>>>> ab9a87318f66eb588acc4ebbe69d097c8fe4da50
}
