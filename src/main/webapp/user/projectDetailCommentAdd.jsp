<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>コメント追加</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/html5reset-1.6.1.css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<!-- ヘッダーを挿入 -->
	<jsp:include page="../inc/header.jsp" />
	<!-- ページタイトル -->
	<div class="title">
		<h2>コメント追加</h2>
	</div>
	<main>
		<div class="form">
		<form action="${pageContext.request.contextPath}/ProjectDetail" method="post" enctype="multipart/form-data">
<!--			<form action="${pageContext.request.contextPath}/ProjectDetail" method="post">-->
				<div class="formItem">
					<label for="comment">追加コメント</label>
					<textarea id="comment" name="comment" rows="8" cols="50" placeholder="コメントを入力してください"></textarea>
				</div>
				<!-- 添付ファイル -->
				<div class="formItem">
					<label for="data">添付ファイル</label> <input type="file" id="data" name="data" accept=".jpg,.png,.pdf,.docx,.xls,.xlsx">
				</div>
				<div class="btn">
					<button type="submit" name="sendKind" value="commentAddComplete" class="addbtn">コメント追加</button>
					<input type="hidden" name="projectId" value="${projectId}">
				</div>
			</form>
		</div>
	</main>
	<!-- フッターを挿入 -->
	<jsp:include page="../inc/footer.jsp" />

</body>
</html>