<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form onsubmit="update(event,${sessionScope.principal.id })">
		<div class="form-group">
			<input type="text" " id="username" value="${sessionScope.principal.username}"
				class="form-control" placeholder="Enter username"
				required="required" maxlength="20" readonly="readonly">
		</div>
		<div class="form-group">
			<input type="email"  id="email" value="${sessionScope.principal.email}"
				class="form-control" placeholder="Enter email">
		</div>
		<button type="submit" class="btn btn-primary">회원수정</button>
	</form>
</div>
<script>
	async function update(event, id) {
		event.preventDefault();
		
		let userUpdateDto = {
				email: document.querySelector("#email").value
		};
		
		console.log(userUpdateDto);
		console.log(JSON.stringify(userUpdateDto));
		
		let response = await fetch("http://localhost:8080/user/"+id, { // 응답을 기다리기 위해 await 사용
			method: 'PUT',
			body: JSON.stringify(userUpdateDto),
			headers: {
				"Content-Type": "application/json; charset=utf-8"
			}
		});
		
		let parseResponse = await response.json(); // 나중에 스프링함수에서 리턴될 때 뭐가 리턴되는지 확인해보자!!
		
		// response.text()로 변경해보자
		console.log(parseResponse)
		
		if(parseResponse.code == 1){
			alert("업데이트 완료");
			location.href="/";
		} else {
			alert(parseResponse.msg);
		}
	}
</script>
<%@ include file="../layout/footer.jsp"%>