package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.ProjectSearchDAO;
import dto.ProjectsBean;
import dto.UsersBean;

/**
 * Servlet implementation class ProjectSearch
 */
@WebServlet("/ProjectSearch")
public class ProjectSearch extends HttpServlet {
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
		String path = "/user/projectSearch.jsp";
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
			response.sendRedirect("/ProjectSearch");

		}
		/* 送信種別あり */
		else {
			// 画面遷移先の初期化
			String path = null;

			// ボタン種別チェック
			switch (sendKind) {
			// 追加ボタン押下時
			case "projectSearch":
				/* 入力情報取得 */
				String projectIdStr = request.getParameter("projectId");
				String projectName = request.getParameter("projectName");

				int projectId = 0;
				if (projectIdStr != null && !projectIdStr.isEmpty()) {
					projectId = Integer.parseInt(projectIdStr); // projectIdがあれば取得
				}
				// ProjectsBeanを作成して、値をセット
				List<ProjectsBean> searchResults = ProjectSearchDAO.searchProjects(projectId, projectName);

				// 検索結果をリクエストスコープにセット
			    request.setAttribute("searchResults", searchResults);

				// 検索結果画面に遷移
				path = "/user/projectSearchShow.jsp";
				request.getRequestDispatcher(path).forward(request, response);
				break;

			}

		}
	}
}