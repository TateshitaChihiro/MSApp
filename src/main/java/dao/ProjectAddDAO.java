package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.ProjectsBean;

public class ProjectAddDAO {

	public static int addProject(ProjectsBean projects) throws SQLException {
		String sql = "INSERT INTO projects (title, completiondate, project_user_id) VALUES (?, ?, ?)";
		int generatedProjectId = 0; // 生成されたproject_idを格納

		try (Connection connection = DBManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql,
						PreparedStatement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, projects.getTitle());

			if (projects.getCompletiondate() != null) {
				statement.setDate(2, new java.sql.Date(projects.getCompletiondate().getTime()));
			} else {
				statement.setDate(2, null);
			}
			statement.setInt(3, projects.getProjectUserId());

			// SQLクエリを実行
			statement.executeUpdate();

			// 生成されたproject_idを取得
			try (var rs = statement.getGeneratedKeys()) {
				if (rs.next()) {
					generatedProjectId = rs.getInt(1);
				}
			}
		}

		// project_personテーブルにデータ挿入
		insertProjectPerson(generatedProjectId, projects.getProjectUserId());

		return generatedProjectId;
	}

	private static void insertProjectPerson(int projectId, int userId) throws SQLException {
		String sql = "INSERT INTO project_person (project_id, user_id) VALUES (?, ?)";

		try (Connection connection = DBManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, projectId);
			statement.setInt(2, userId);
			statement.executeUpdate();
		}
	}
}
