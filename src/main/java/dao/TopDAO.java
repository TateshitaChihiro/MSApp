package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProjectsBean;

public class TopDAO {
	public static List<ProjectsBean> MyProjectList(int userId) {

	// 対応中案件一覧を取得
				String myProjectSql = "SELECT projects.project_id, projects.title, projects.project_status, users.user_id, users.name "
						+ "FROM projects "
						+ "LEFT JOIN project_person ON projects.project_id = project_person.project_id "
						+ "LEFT JOIN users ON project_person.user_id = users.user_id "
						+ "WHERE (projects.project_status = \"未対応\" OR projects.project_status = \"対応中\")"
						+ "AND users.user_id = ? ";
				
				List<ProjectsBean> projectList = new ArrayList<>();

				try (Connection conn = DBManager.getConnection();
						PreparedStatement myProjectPStmt = conn.prepareStatement(myProjectSql)) {
					// プレースホルダーに値をセット
		            myProjectPStmt.setInt(1, userId);

		            try (ResultSet rsProjectList = myProjectPStmt.executeQuery()) {
		                while (rsProjectList.next()) {
		                    ProjectsBean project = new ProjectsBean();
		                    project.setProjectId(rsProjectList.getInt("projects.project_id"));
		                    project.setTitle(rsProjectList.getString("projects.title"));
		                    project.setProjectStatus(rsProjectList.getString("projects.project_status"));
		                    project.setName(rsProjectList.getString("users.name"));

		                    // 結果をリストに追加
		                    projectList.add(project);
		                }
		            }
		        }catch (SQLException e) {
					e.printStackTrace();
		        }
		        return projectList; // 最終的なリストを返す
		    }
		}