<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
<<<<<<< HEAD
	<form action="/join" method="post">
	  <div class="form-group">
	    <input type="text" name="username" class="form-control" placeholder="Enter username" required="required">
	  </div>
	  <div class="form-group">
	    <input type="password" name="password" class="form-control" placeholder="Enter password" required="required">
	  </div>
	  <div class="form-group">
	    <input type="email" name="email" class="form-control" placeholder="Enter email address" required="required">
=======
	<form action="/join"  method="post">
	  <div class="form-group">
	    <input type="text" name="username" class="form-control" placeholder="Enter username"  required="required" maxlength="20">
	  </div>
	  <div class="form-group">
	    <input type="password" name="password" class="form-control" placeholder="Enter password"  required="required" maxlength="20">
	  </div>
	  <div class="form-group">
	    <input type="email" name="email" class="form-control" placeholder="Enter email" >
>>>>>>> ab9a87318f66eb588acc4ebbe69d097c8fe4da50
	  </div>
	  <button type="submit" class="btn btn-primary">회원가입</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>