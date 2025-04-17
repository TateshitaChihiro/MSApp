<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>案件検索</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/html5reset-1.6.1.css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<!-- ヘッダーを挿入 -->
	<jsp:include page="../inc/header.jsp" />
	<!-- ページタイトル -->
	<div class="title">
		<h2>案件検索</h2>
	</div>
	<main>
		<div class="form">
			<form action="${pageContext.request.contextPath}/ProjectSearch" method="post">
				<div class="formItem">
					<label>案件ID</label><input type="text" name="projectId" placeholder="案件ID">
				</div>
				<div class="formItem">
					<label>案件名</label><input type="text" name="projectName" placeholder="案件名">
				</div>
				<div class="btn">
				<button type="submit" name="sendKind" value="projectSearch" class="searchbtn">検索</button>
				<button type="reset" class="searchbtn">クリア</button>
				</div>
			</form>
		</div>
	</main>
	<!-- フッターを挿入 -->
	<jsp:include page="../inc/footer.jsp" />

</body>
</html>