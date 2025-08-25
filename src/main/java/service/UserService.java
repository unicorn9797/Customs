package service;

import model.User;

public interface UserService {
	//create
	boolean addUser(User user);
	//read	
	User Login(String username, String password);
	//update
	boolean update(User user);
	//delete
	boolean delete(User user);
}
