<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp" %>


<h1>${sessionScope.principal.username}</h1> <!-- 변수명을 적으면 자동으로 getter를 해준다  -->


<%@ include file="layout/footer.jsp" %>