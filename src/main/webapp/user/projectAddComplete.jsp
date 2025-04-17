<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>新規案件登録完了</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/html5reset-1.6.1.css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<!-- ヘッダーを挿入 -->
	<jsp:include page="../inc/header.jsp" />
	<!-- ページタイトル -->
	<div class="title">
		<h2>新規案件登録完了</h2>
	</div>
	<main>
		<div class="form">案件の登録が完了しました。</div>

		<div class="btn">
		<form action="${pageContext.request.contextPath}/Top" method="get">
			<button type="submit" name="sendKind" value="top" class="topbtn">TOPに戻る</button>
<!--			<input type="submit" value="会員情報TOPに戻る">-->
<!--		</div>-->
		</form>
		</div>
	</main>
	<!-- フッターを挿入 -->
	<jsp:include page="../inc/footer.jsp" />

</body>
</html>