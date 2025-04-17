package dto;

import java.sql.Timestamp;

public class ProjectCommentBean {
	private int projectCommentId;
	private int projectId;
	private int projectCommentUserId;
	private String projectComment;
	private String data;
	private Timestamp projectCommentCreatedAt;

	private String name;

	public ProjectCommentBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	// 全フィールドを含むコンストラクタ
	public ProjectCommentBean(int projectCommentId, int projectId, int projectCommentUserId, String projectComment,
			String data, Timestamp projectCommentCreatedAt) {
		this.projectCommentId = projectCommentId;
		this.projectId = projectId;
		this.projectCommentUserId = projectCommentUserId;
		this.projectComment = projectComment;
		this.data = data;
		this.projectCommentCreatedAt = projectCommentCreatedAt;
	}

	// コメント用
	public ProjectCommentBean(int projectId, String name, Timestamp projectCommentCreatedAt, String projectComment,
			String data) {
		this.projectId = projectId;
		this.name = name;
		this.projectCommentCreatedAt = projectCommentCreatedAt;
		this.projectComment = projectComment;
		this.data = data;
	}

	// コメント追加用
	public ProjectCommentBean(int projectId, String projectComment, int projectCommentUserId, String data) {
		this.projectId = projectId;
		this.projectComment = projectComment;
		this.projectCommentUserId = projectCommentUserId;
		this.data = data;
	}

	public int getProjectCommentId() {
		return projectCommentId;
	}

	public void setProjectCommentId(int projectCommentId) {
		this.projectCommentId = projectCommentId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getProjectCommentUserId() {
		return projectCommentUserId;
	}

	public void setProjectCommentUserId(int projectCommentUserId) {
		this.projectCommentUserId = projectCommentUserId;
	}

	public String getProjectComment() {
		return projectComment;
	}

	public void setProjectComment(String projectComment) {
		this.projectComment = projectComment;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Timestamp getProjectCommentCreatedAt() {
		return projectCommentCreatedAt;
	}

	public void setProjectCommentCreatedAt(Timestamp projectCommentCreatedAt) {
		this.projectCommentCreatedAt = projectCommentCreatedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
