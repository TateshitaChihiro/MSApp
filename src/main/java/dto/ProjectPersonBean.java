package dto;

public class ProjectPersonBean {
	private int projectPersonId;
	private int projectId;
	private int userId;

	public ProjectPersonBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	// 全フィールドを含むコンストラクタ
	public ProjectPersonBean(int projectPersonId, int projectId, int userId) {
		this.projectPersonId = projectPersonId;
		this.projectId = projectId;
		this.userId = userId;
	}

	public int getProjectPersonId() {
		return projectPersonId;
	}

	public void setProjectPersonId(int projectPersonId) {
		this.projectPersonId = projectPersonId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
