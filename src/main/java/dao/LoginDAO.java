package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UsersBean;

public class LoginDAO {

	// 管理者名とパスワードで検索してオブジェクトを返す
	public static UsersBean LoginUser(int userId, String pass) {
		//        UsersBean loginUser = null; // 初期値をnullに設定
		// 変数を用意
		UsersBean loginUser = new UsersBean();

		try (Connection connection = DBManager.getConnection()) {
			String sql = "SELECT * FROM users WHERE user_id = ? AND pass = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			// SQLにuserIdとpassをセット
			statement.setInt(1, userId);
			statement.setString(2, pass);

			// 実行
			ResultSet rsLoginUser = statement.executeQuery();
			// 実行
			//            try (ResultSet rsUser = statement.executeQuery()) {
			if (rsLoginUser.next()) {
				// UsersBeanにデータをセット
				//                    loginUser = new UsersBean();
				loginUser.setUserId(rsLoginUser.getInt("user_id"));
				loginUser.setPass(rsLoginUser.getString("pass"));
				//                    loginUser.setName(rsUser.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace(); // エラー時にスタックトレースを出力
		}

		return loginUser; // ユーザー情報を返す
	}
}
