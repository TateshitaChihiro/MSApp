package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.TopDAO;
import dto.ProjectsBean;
import dto.UsersBean;

/**
 * Servlet implementation class Top
 */
@WebServlet("/Top")
public class Top extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		// プロジェクト一覧を取得
		List<ProjectsBean> projectList = TopDAO.MyProjectList(userId);
		request.setAttribute("projectList", projectList);

		// JSPへの遷移
		String path = "/user/top.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
}
