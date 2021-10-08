package com.cos.blogapp.web.dto;

<<<<<<< HEAD
=======
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

>>>>>>> ab9a87318f66eb588acc4ebbe69d097c8fe4da50
import com.cos.blogapp.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoinReqDto {
<<<<<<< HEAD
	private String username;
	private String password;
=======
	@Size(min = 2, max = 20)
	@NotBlank // @NotNull + @NotEmpty
	private String username;
	
	@Size(min = 4, max = 20)
	@NotBlank
	private String password;
	
	@Size(min = 4, max = 50)
	@NotBlank
>>>>>>> ab9a87318f66eb588acc4ebbe69d097c8fe4da50
	private String email;
	
	public User toEntity() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		return user;
	}
}



<<<<<<< HEAD

=======
>>>>>>> ab9a87318f66eb588acc4ebbe69d097c8fe4da50
