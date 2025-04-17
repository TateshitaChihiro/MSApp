package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProjectsBean;

public class ProjectSearchDAO {

	// 案件検索
	public static List<ProjectsBean> searchProjects(int projectId, String projectName) {
		StringBuilder sql = new StringBuilder("SELECT * FROM projects WHERE 1=1 ");

		if (projectId > 0) {
			sql.append("AND project_id = ? ");
		}
		if (projectName != null && !projectName.isEmpty()) {
			sql.append("AND title LIKE ? ");
		}

		List<ProjectsBean> projectList = new ArrayList<>();

		try (Connection connection = DBManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql.toString())) {

			int index = 1;

			// パラメータをセット
			if (projectId > 0) {
				statement.setInt(index++, projectId);
			}
			if (projectName != null && !projectName.isEmpty()) {
				statement.setString(index++, "%" + projectName + "%");
			}

			// SQLを実行
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					ProjectsBean project = new ProjectsBean();
					project.setProjectId(resultSet.getInt("project_id"));
					project.setTitle(resultSet.getString("title"));
					project.setProjectStatus(resultSet.getString("project_status"));
					project.setCompletiondate(resultSet.getDate("completiondate"));
					project.setProjectCreatedAt(resultSet.getTimestamp("project_created_at"));
					projectList.add(project);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectList;
	}
}
