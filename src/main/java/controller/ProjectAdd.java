package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.ProjectAddDAO;
import dto.ProjectsBean;
import dto.UsersBean;

/**
 * Servlet implementation class ProjectAdd
 */
@WebServlet("/ProjectAdd")
public class ProjectAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// DataSourceの初期化 (例: JNDI lookupなど)
		// ds = (DataSource) getServletContext().getAttribute("datasource");
	}

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

		// JSPへの遷移
		String path = "/user/projectAdd.jsp";
		request.getRequestDispatcher(path).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
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
			response.sendRedirect("/ProjectAdd");

		}
		/* 送信種別あり */
		else {
			// 画面遷移先の初期化
			String path = null;

			// ボタン種別チェック
			switch (sendKind) {
			// 追加ボタン押下時
			case "projectAdd":
				/* 入力情報取得 */
				// プロジェクト名
				String projectName = request.getParameter("projectname");
				// 名前
				String strCompletionDate = request.getParameter("completiondate");
				//				Date completionDate = Date.valueOf(strCompletionDate);

				// completiondate が空でないか確認し、適切に Date に変換
				Date completionDate = null;
				if (strCompletionDate != null && !strCompletionDate.isEmpty()) {
					completionDate = Date.valueOf(strCompletionDate); // "yyyy-MM-dd" 形式
				}

				// ProjectsBeanを作成して、値をセット
				ProjectsBean project = new ProjectsBean(projectName, completionDate, userId);

				// プロジェクトをデータベースに追加
				try {
					int generatedProjectId = ProjectAddDAO.addProject(project);
					System.out.println("生成されたProject ID: " + generatedProjectId);

				} catch (SQLException e) {
					e.printStackTrace();
				}

				// 完了画面に遷移
				path = "/user/projectAddComplete.jsp";
				request.getRequestDispatcher(path).forward(request, response);
				break;

			}

		}
	}
}