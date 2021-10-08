package com.cos.blogapp.web.dto;

<<<<<<< HEAD
=======
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

>>>>>>> ab9a87318f66eb588acc4ebbe69d097c8fe4da50
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO = Data Transfer Object (데이터 전송 오브젝트)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReqDto {
<<<<<<< HEAD
	private String username;
	private String password;
	
	public static void main(String[] args) {
		LoginReqDto dto = new LoginReqDto();
	}
=======
	@Size(min = 2, max = 20)
	@NotBlank
	private String username;
	
	@Size(min = 4, max = 20)
	@NotBlank
	private String password;
>>>>>>> ab9a87318f66eb588acc4ebbe69d097c8fe4da50
}
