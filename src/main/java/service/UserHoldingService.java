package service;

import java.util.List;

import model.UserHolding;

public interface UserHoldingService {
	//create
	void addUserHolding(UserHolding userHolding);
	//read
	List<UserHolding> selectByUsername(String username);
	//update
	boolean update(UserHolding userHolding);
	//delete
	boolean delete(UserHolding userHolding);
	
}
