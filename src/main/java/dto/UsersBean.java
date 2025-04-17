package dto;

import java.sql.Timestamp;

public class UsersBean {
    private int userId;
    private int employeeId;
    private String name;
    private String kana;
    private String occupation;
    private String email;
    private String pass;
    private int roleId;
    private Timestamp createdAt;
    private String userStatus;

	public UsersBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

    public UsersBean(int userId, int employeeId, String name, String kana, String occupation, String email, String pass, int roleId, Timestamp createdAt, String userStatus) {
        this.userId = userId;
        this.employeeId = employeeId;
        this.name = name;
        this.kana = kana;
        this.occupation = occupation;
        this.email = email;
        this.pass = pass;
        this.roleId = roleId;
        this.createdAt = createdAt;
        this.userStatus = userStatus;
    }
    
    public UsersBean(int userId,String pass) {
        this.userId = userId;  
        this.pass = pass;
    }
    
    public UsersBean(int userId,String pass, String name) {
        this.userId = userId;  
        this.pass = pass;
        this.name = name;
    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}



}
