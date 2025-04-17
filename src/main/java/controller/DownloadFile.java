package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DownloadFile")
public class DownloadFile extends HttpServlet {
	private static final String FILE_DIR = "C:/MSAppUploads";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// クエリパラメータからファイル名を取得
		String fileName = request.getParameter("file");
		if (fileName == null || fileName.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ファイル名が指定されていません");
			return;
		}

		File file = new File(FILE_DIR, fileName);
		if (!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "ファイルが存在しません");
			return;
		}

		// レスポンスの設定
		response.setContentType(getServletContext().getMimeType(fileName));
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentLengthLong(file.length());

		// ファイル出力
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
				OutputStream out = response.getOutputStream()) {
			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}
	}
}
