package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProjectCommentBean;
import dto.ProjectsBean;

public class ProjectDetailDAO {

	public ProjectDetailDAO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	//project_idが一致するもの
	public static ProjectsBean projectId(int projectId) {
		String sql = "SELECT projects.project_id, projects.title, projects.project_status, projects.completiondate, project_comment.project_comment_id, project_comment.project_comment, project_comment.project_comment_created_at, project_comment.data, users.name \n"
				+ "FROM projects \n"
				+ "LEFT JOIN project_comment ON projects.project_id = project_comment.project_id \n"
				+ "LEFT JOIN users ON projects.project_user_id = users.user_id \n"
				+ "WHERE projects.project_id = ?;";

		ProjectsBean project = null;

		try (Connection connection = DBManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, projectId); // プレースホルダーにprojectIdをセット

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// ProjectsBeanにデータをセット
					project = new ProjectsBean();
					project.setProjectId(resultSet.getInt("projects.project_id"));
					project.setTitle(resultSet.getString("projects.title"));
					project.setProjectStatus(resultSet.getString("projects.project_status"));
					project.setCompletiondate(resultSet.getDate("projects.completiondate"));
					project.setName(resultSet.getString("users.name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return project; // プロジェクト情報を返す
	}

	//project_idが一致する_コメントリスト
	public static List<ProjectCommentBean> projectCommentList(int projectId) {
		String sql = "SELECT projects.project_id, projects.title, projects.project_status, projects.completiondate, "
				+ "project_comment.project_comment_id, project_comment.project_comment, "
				+ "project_comment.project_comment_created_at, project_comment.data, users.name "
				+ "FROM projects "
				+ "LEFT JOIN project_comment ON projects.project_id = project_comment.project_id "
				+ "LEFT JOIN users ON project_comment.project_comment_user_id = users.user_id "
				+ "WHERE projects.project_id = ? "
				+ "ORDER BY project_comment.project_comment_id DESC;";

		List<ProjectCommentBean> projectCommentList = new ArrayList<>();

		try (Connection connection = DBManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, projectId); // プレースホルダーにprojectIdをセット

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					ProjectCommentBean projectComment = new ProjectCommentBean();
					projectComment.setProjectId(resultSet.getInt("projects.project_id"));
					projectComment.setName(resultSet.getString("users.name"));
					projectComment.setProjectCommentCreatedAt(
							resultSet.getTimestamp("project_comment.project_comment_created_at"));
					projectComment.setProjectComment(resultSet.getString("project_comment.project_comment"));
					projectComment.setData(resultSet.getString("project_comment.data"));

					projectCommentList.add(projectComment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectCommentList;
	}

	public static void addComment(ProjectCommentBean comment) throws SQLException {
		String sql = "INSERT INTO project_comment (project_id, project_comment, project_comment_user_id, data) VALUES (?, ?, ?, ?)";

		try (Connection connection = DBManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, comment.getProjectId());
			statement.setString(2, comment.getProjectComment());
			statement.setInt(3, comment.getProjectCommentUserId());
			statement.setString(4, comment.getData());

			statement.executeUpdate();
		}
	}

	// ステータス変更
	public static void updateProjectStatus(int projectId, String newStatus) throws SQLException {
		String sql = "UPDATE projects SET project_status = ? WHERE project_id = ?";
		try (Connection conn = DBManager.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, newStatus);
			ps.setInt(2, projectId);
			ps.executeUpdate();
		}

	}
}
