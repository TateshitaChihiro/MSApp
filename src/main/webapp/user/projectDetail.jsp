<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>案件詳細</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/html5reset-1.6.1.css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
	<!-- ヘッダーを挿入 -->
	<jsp:include page="../inc/header.jsp" />
	<!-- ページタイトル -->
	<div class="title">
		<h2>案件詳細</h2>
	</div>
	<main>
		<div class="formtop">
			<div class="item">
				<div class="labeldetail">案件ID：</div>
				<div class="data">${editProject.projectId}</div>
			</div>
			<div class="item">
				<div class="labeldetail">案件名：</div>
				<div class="data">${editProject.title}</div>
			</div>
			<div class="item">
				<div class="labeldetail">案件ステータス：</div>
				<div class="data">
					<div class="status-container">
						<form action="${pageContext.request.contextPath}/ProjectDetail" method="post">
							<select name="projectStatus">
								<option value="未対応" ${editProject.projectStatus == '未対応' ? 'selected' : ''}>未対応</option>
								<option value="対応中" ${editProject.projectStatus == '対応中' ? 'selected' : ''}>対応中</option>
								<option value="完了" ${editProject.projectStatus == '完了' ? 'selected' : ''}>完了</option>
							</select>
							<button type="submit" name="sendKind" value="statusChange" class="button01">ステータス変更</button>
							<input type="hidden" name="projectId" value="${editProject.projectId}">
						</form>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="labeldetail">ライブ希望日：</div>
				<div class="data">${editProject.completiondate}</div>
			</div>
			<div class="item">
				<div class="labeldetail">作成者：</div>
				<div class="data">${editProject.name}</div>
			</div>
		</div>
		<!-- コメント一覧エリア -->
		<div class="projectlist">
			<form action="${pageContext.request.contextPath}/ProjectDetail" method="get">
				<button type="submit" name="sendKind" value="commentAdd" class="button01">コメント追加</button>
				<input type="hidden" name="projectId" value="${editProject.projectId}">
			</form>
			<br>
			<!-- コメント一覧を表示 -->
			<c:if test="${not empty commentList}">
				<table class="table_design07">
					<thead>
						<tr>
							<th>投稿者</th>
							<th>投稿日時</th>
							<th>コメント</th>
							<th>添付ファイル</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="comment" items="${commentList}">
							<tr>
								<td>${comment.name}</td>
								<td><fmt:formatDate value="${comment.projectCommentCreatedAt}" pattern="yyyy-MM-dd HH:mm" /></td>
								<td>${comment.projectComment}</td>
								<td><c:if test="${not empty comment.data}">
										<a href="${pageContext.request.contextPath}/DownloadFile?file=${comment.data}" target="_blank">
											${comment.data} </a>
									</c:if></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<!-- コメントがまだ無い場合のメッセージ -->
			<c:if test="${empty commentList}">
				<p>まだコメントはありません。</p>
			</c:if>
		</div>
	</main>
	<!-- フッターを挿入 -->
	<jsp:include page="../inc/footer.jsp" />

</body>
</html>