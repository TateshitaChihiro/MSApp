<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<header>
		<h1>
			<a href="${pageContext.request.contextPath}/Top" class="logo">案件管理</a>
		</h1>

<!--		<c:if test="${not empty loginUser}">-->
<!--			<div class="username"> ${loginUser.name} さん</div>-->
<!--		</c:if>-->

		<button type="button" class="logout-button" onclick="location.href='${pageContext.request.contextPath}/Logout'">ログアウト</button>

	</header>
