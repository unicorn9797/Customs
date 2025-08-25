package model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L; // 建議加上版本號
	//fields
	private String name;
	private String username;	
	private String password;
	private String role;
	private String privateKey;
	private String publicKey;
	//constructors
	public User()
	{
		
	}
	public User(String name, String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.name = name;
	}
	//methods
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	
	
}
