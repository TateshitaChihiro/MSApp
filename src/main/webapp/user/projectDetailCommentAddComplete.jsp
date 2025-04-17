<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>コメント追加完了</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/html5reset-1.6.1.css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<!-- ヘッダーを挿入 -->
	<jsp:include page="../inc/header.jsp" />
	<!-- ページタイトル -->
	<div class="title">
		<h2>コメント追加完了</h2>
	</div>
	<main>
		<div class="form">コメントの追加が完了しました。</div>
		<div class="btn">
			<form action="${pageContext.request.contextPath}/ProjectDetail" method="get">
				<button type="submit" name="sendKind" value="projectDetail" class="topbtn">案件詳細に戻る</button>
				<input type="hidden" name="projectId" value="${projectId}">
			</form>
		</div>
	</main>
	<!-- フッターを挿入 -->
	<jsp:include page="../inc/footer.jsp" />

</body>
</html>