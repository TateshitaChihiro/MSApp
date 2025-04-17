package dto;

import java.sql.Date;
import java.sql.Timestamp;

public class ProjectsBean {
	private int projectId;
	private String title;
	private String projectStatus;
	private Timestamp projectCreatedAt;
	private Date completiondate;

	private int projectUserId;

	private String name;

	private int projectCommentId;
	private int projectCommentUserId;
	private String projectComment;
	private String data;
	private Timestamp projectCommentCreatedAt;

	public ProjectsBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	// フィールドすべてを含むコンストラクタ
	public ProjectsBean(int projectId, String title, String projectStatus, Timestamp projectCreatedAt,
			Date completiondate, int projectUserId) {
		this.projectId = projectId;
		this.title = title;
		this.projectStatus = projectStatus;
		this.projectCreatedAt = projectCreatedAt;
		this.completiondate = completiondate;
		this.projectUserId = projectUserId;
	}

	// 未対応、対応中案件リスト
	public ProjectsBean(int projectId, String title, String projectStatus, String name) {
		this.projectId = projectId;
		this.title = title;
		this.projectStatus = projectStatus;
		this.name = name;
	}

	// titleとライブ希望日セット
	public ProjectsBean(String title, Date completiondate, int projectUserId) {
		this.title = title;
		this.completiondate = completiondate;
		this.projectUserId = projectUserId;
	}

	// 詳細画面_案件
	public ProjectsBean(int projectId, String title, String projectStatus, Date completiondate, String name) {
		this.projectId = projectId;
		this.title = title;
		this.projectStatus = projectStatus;
		this.completiondate = completiondate;
		this.name = name;
	}

	// 詳細画面
	public ProjectsBean(int projectId, String title, String projectStatus, Date completiondate, String name,
			Timestamp projectCommentCreatedAt, String projectComment, String data) {
		this.projectId = projectId;
		this.title = title;
		this.projectStatus = projectStatus;
		this.completiondate = completiondate;
		this.name = name;
		this.projectCommentCreatedAt = projectCommentCreatedAt;
		this.projectComment = projectComment;
		this.data = data;
	}

	// 検索
	public ProjectsBean(int projectId, String title) {
		this.projectId = projectId;
		this.title = title;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Timestamp getProjectCreatedAt() {
		return projectCreatedAt;
	}

	public void setProjectCreatedAt(Timestamp projectCreatedAt) {
		this.projectCreatedAt = projectCreatedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCompletiondate() {
		return completiondate;
	}

	public void setCompletiondate(Date completiondate) {
		this.completiondate = completiondate;
	}

	public int getProjectUserId() {
		return projectUserId;
	}

	public void setProjectUserId(int projectUserId) {
		this.projectUserId = projectUserId;
	}

	public int getProjectCommentId() {
		return projectCommentId;
	}

	public void setProjectCommentId(int projectCommentId) {
		this.projectCommentId = projectCommentId;
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

}
