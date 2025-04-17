package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import dao.ProjectDetailDAO;
import dto.ProjectCommentBean;
import dto.ProjectsBean;
import dto.UsersBean;

/**
 * Servlet implementation class ProjectDetail
 */

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB超えたら一時ディスク保存
		maxFileSize = 1024 * 1024 * 5, // 最大5MB
		maxRequestSize = 1024 * 1024 * 10 // リクエスト全体で10MB
)

@WebServlet("/ProjectDetail")
public class ProjectDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 送信されたデータのエンコーディングを指定（文字化け対策）
		request.setCharacterEncoding("UTF-8");

		//---ログイン情報取得--------------------------------------------------------------
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			// ログインしていない場合は、ログインページにリダイレクト
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		// ログインユーザーの情報を取得
		UsersBean loginUser = (UsersBean) session.getAttribute("userId");
		int userId = loginUser.getUserId();
		System.out.println("ログインユーザーID: " + userId);
		//---------------------------------------------------------------------------------

		// 送信種別の取得
		String sendKind = request.getParameter("sendKind");
		if (sendKind == null || sendKind.isEmpty()) {
			/* 送信種別なし or 取り消し */
			String path = "./user/projectDetail.jsp";
			request.getRequestDispatcher(path).forward(request, response);

		}
		/* 送信種別あり */
		else {
			// 画面遷移先の初期化
			String path = null;

			// ボタン種別チェック
			switch (sendKind) {
			// 追加ボタン押下時
			case "projectDetail":
				/* 入力情報取得 */
				// projectIdを取得
				String projectId = request.getParameter("projectId");
				int intProjectId = Integer.parseInt(projectId);

				// project情報を取得
				ProjectsBean editProject = ProjectDetailDAO.projectId(intProjectId);
				// コメント一覧を取得
				List<ProjectCommentBean> commentList = ProjectDetailDAO.projectCommentList(intProjectId);

				// リクエストスコープにプロジェクト情報を保存
				request.setAttribute("editProject", editProject);
				request.setAttribute("commentList", commentList);

				// ユーザー編集画面にフォワード
				String editPath = "/user/projectDetail.jsp";
				request.getRequestDispatcher(editPath).forward(request, response);
				break;

			// コメント追加ボタン押下時
			case "commentAdd":
				/* projectId取得 */
				String CAprojectId = request.getParameter("projectId");
				int intCAProjectId = Integer.parseInt(CAprojectId);

				// プロジェクトIDをリクエストスコープに格納
				request.setAttribute("projectId", intCAProjectId);

				// コメント追加画面に遷移
				path = "/user/projectDetailCommentAdd.jsp";
				request.getRequestDispatcher(path).forward(request, response);
				break;

			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 送信されたデータのエンコーディングを指定（文字化け対策）
		request.setCharacterEncoding("UTF-8");

		//---ログイン情報取得--------------------------------------------------------------
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			// ログインしていない場合は、ログインページにリダイレクト
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}
		// ログインユーザーの情報を取得
		UsersBean loginUser = (UsersBean) session.getAttribute("userId");
		int userId = loginUser.getUserId();
		System.out.println("ログインユーザーID: " + userId);
		//---------------------------------------------------------------------------------

		// 送信種別の取得
		String sendKind = request.getParameter("sendKind");
		if (sendKind == null || sendKind.isEmpty()) {
			/* 送信種別なし or 取り消し */
			response.sendRedirect("/ProjectDetail");

		}
		/* 送信種別あり */
		else {
			// 画面遷移先の初期化
			String path = null;

			// ボタン種別チェック
			switch (sendKind) {
			// 追加ボタン押下時
			case "commentAddComplete":
				// projectIdを取得
				String projectId = request.getParameter("projectId");
				int intProjectId = Integer.parseInt(projectId);
				/* 入力情報取得 */
				String comment = request.getParameter("comment");

				// 保存先（アプリ外のフォルダ）
				String uploadDir = "C:/MSAppUploads";

				Part filePart = request.getPart("data");
				String fileName = null;

				if (filePart != null && filePart.getSize() > 0) {
					// ファイル名
					String originalFileName = Path.of(filePart.getSubmittedFileName()).getFileName().toString();

					// ファイル名と拡張子を切り分け
					String baseName = originalFileName;
					String extension = "";
					int dotIndex = originalFileName.lastIndexOf(".");
					if (dotIndex != -1) {
						baseName = originalFileName.substring(0, dotIndex);
						extension = originalFileName.substring(dotIndex);
					}

					// フォルダがない場合は作成
					File dir = new File(uploadDir);
					if (!dir.exists()) {
						dir.mkdirs();
					}

					// ファイル名が重複した場合
					fileName = originalFileName;
					int counter = 1;
					File saveFile = new File(uploadDir, fileName);
					while (saveFile.exists()) {
						fileName = baseName + "(" + counter + ")" + extension;
						saveFile = new File(uploadDir, fileName);
						counter++;
					}

					// ファイルをフォルダに保存
					filePart.write(saveFile.getAbsolutePath());
					System.out.println("ファイル保存完了: " + saveFile.getAbsolutePath());
				}

				// ProjectCommentBeanを作成して、値をセット
				ProjectCommentBean addCommentBean = new ProjectCommentBean(intProjectId, comment, userId, fileName);

				// コメントとファイルをデータベースに追加
				try {
					ProjectDetailDAO.addComment(addCommentBean);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				// projectIdをリクエストスコープに設定
				request.setAttribute("projectId", intProjectId);

				// 完了画面に遷移
				path = "/user/projectDetailCommentAddComplete.jsp";
				request.getRequestDispatcher(path).forward(request, response);
				break;

			case "statusChange":
				// 情報取得
				String newStatus = request.getParameter("projectStatus");
				String projectIdForStatus = request.getParameter("projectId");
				int projectIdInt = Integer.parseInt(projectIdForStatus);

				try {
					ProjectDetailDAO.updateProjectStatus(projectIdInt, newStatus);
					System.out.println("ステータス変更完了: " + newStatus);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				// 詳細画面にリダイレクト
				response.sendRedirect(
						request.getContextPath() + "/ProjectDetail?sendKind=projectDetail&projectId=" + projectIdInt);
				break;

			}
		}
	}
}
