package daO;

import model.User;

public interface UserDao {
	//create
	void add(User user);
	//read
	User selectbyUsername(String username);
	User selectForLogin(String username, String password);
	//Update
	void update(User user);
	//delete
	void delete(User user);
}
