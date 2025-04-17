<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ログイン</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/html5reset-1.6.1.css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<header>
		<h1>
			<a href="${pageContext.request.contextPath}/Top" class="logo">案件管理</a>
		</h1>
	</header>
	<!-- ページタイトル -->
	<div class="title">
		<h2>ログイン</h2>
	</div>
	<main>
		<c:if test="${not empty errorMessage}">
			<div style="color: tomato;">${errorMessage}</div>
		</c:if>
		<div class="form">
			<form action="${pageContext.request.contextPath}/Login" method="post">
				<div class="formItem">
					<label>ユーザーID</label><input type="text" name="userId" placeholder="ユーザーID" required>
				</div>
				<div class="formItem">
					<label>パスワード</label><input type="password" name="password" placeholder="パスワード" required>
				</div>
				<div class="btn">
					<!--				<button type="submit" class="loginbtn">ログイン</button>-->
					<button type="submit" name="sendKind" value="top" class="loginbtn">ログイン</button>
					<!--					<input type="submit" value="ログイン">-->
				</div>
			</form>
		</div>
	</main>
	<!-- フッターを挿入 -->
	<jsp:include page="../inc/footer.jsp" />

</body>
</html>