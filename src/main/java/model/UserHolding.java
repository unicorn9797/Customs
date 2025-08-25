package model;

public class UserHolding {
	//fields
	private String username;
	private String declarationId;
	private String role;
	private Integer id;
	//constructors
	public UserHolding() {
		super();
	}
	public UserHolding(String username, String declarationId, String role) {
		super();
		this.username = username;
		this.declarationId = declarationId;
		this.role = role;
	}
	//methods
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDeclarationId() {
		return declarationId;
	}
	public void setDeclarationId(String declarationId) {
		this.declarationId = declarationId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
