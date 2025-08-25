package daO;

import java.util.List;

import model.UserHolding;

public interface UserHoldingDao {
	//create
	void add(UserHolding userHolding);
	//read
	List<UserHolding> selectByUsername(String username);
	UserHolding selectById(int id);
	//update
	void update(UserHolding userHolding);
	//delete
	void delete(UserHolding userHolding);
}
