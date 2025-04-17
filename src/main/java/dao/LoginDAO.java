package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UsersBean;

public class LoginDAO {

	// 管理者名とパスワードで検索してオブジェクトを返す
	public static UsersBean LoginUser(int userId, String pass) {
		// 初期値をnullに設定
		UsersBean loginUser = null;

		try (Connection connection = DBManager.getConnection()) {
			String sql = "SELECT * FROM users WHERE user_id = ? AND pass = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			// SQLにuserIdとpassをセット
			statement.setInt(1, userId);
			statement.setString(2, pass);

			// 実行
			ResultSet rsLoginUser = statement.executeQuery();

			if (rsLoginUser.next()) {
				loginUser = new UsersBean();
				loginUser.setUserId(rsLoginUser.getInt("user_id"));
				loginUser.setPass(rsLoginUser.getString("pass"));
			}
		} catch (SQLException e) {
			e.printStackTrace(); // エラー時にスタックトレースを出力
		}

		return loginUser; // ユーザー情報を返す
	}
}
