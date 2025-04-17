package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.LoginDAO;
import dto.UsersBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		//画面遷移
		String path = "./user/login.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String strUserId = request.getParameter("userId");
		String password = request.getParameter("password");

		// バリデーション
		if (strUserId == null || password == null || strUserId.trim().isEmpty() || password.trim().isEmpty()) {
			request.setAttribute("errorMessage", "ユーザーIDとパスワードを入力してください。");
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
			return;
		}

		int userId = Integer.parseInt(strUserId);
		UsersBean loginUser = LoginDAO.LoginUser(userId, password);

		if (loginUser != null) {
			// 認証成功
			HttpSession session = request.getSession();
			session.setAttribute("userId", loginUser);
			response.sendRedirect(request.getContextPath() + "/Top"); // Topサーブレットへリダイレクト
		} else {
			// 認証失敗
			request.setAttribute("errorMessage", "ユーザー名またはパスワードが正しくありません。");
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		}
	}
}
