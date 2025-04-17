<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>案件検索結果</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/html5reset-1.6.1.css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<!-- ヘッダーを挿入 -->
	<jsp:include page="../inc/header.jsp" />
	<!-- ページタイトル -->
	<div class="title">
		<h2>案件検索結果</h2>
	</div>
	<main>
		<!-- 検索結果一覧エリア -->
		<div class="projectlist">
			<h3>検索結果一覧</h3>
			<br>
			<c:if test="${not empty searchResults}">
				<table class="table_design08">
					<thead>
						<tr>
							<th>案件ID</th>
							<th>案件名</th>
							<th>ステータス</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<!-- 検索結果を表示 -->
						<c:forEach var="projectLists" items="${searchResults}">
							<tr>
								<td>${projectLists.projectId}</td>
								<td>${projectLists.title}</td>
								<td>${projectLists.projectStatus}</td>
								<td>
									<!-- 詳細ボタン -->
									<form action="${pageContext.request.contextPath}/ProjectDetail" method="get" style="display: inline;">
										<button type="submit" name="sendKind" value="projectDetail">詳細</button>
										<input type="hidden" name="projectId" value="${projectLists.projectId}">
									</form> 
								</td>
							</tr>
						</c:forEach>

					</tbody>

				</table>
			</c:if>

			<!-- 検索結果が空の場合のメッセージ -->
			<c:if test="${empty searchResults}">
				<p>検索結果はありません。</p>
			</c:if>
		</div>
	</main>
	<!-- フッターを挿入 -->
	<jsp:include page="../inc/footer.jsp" />

</body>
</html>