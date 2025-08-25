package service.impl;

import daO.impl.UserDaoImpl;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {
	
	private static UserDaoImpl userDaoImpl = new UserDaoImpl();

	@Override
	public boolean addUser(User user) {
		boolean add = false;
		User temp = userDaoImpl.selectbyUsername(user.getUsername());
		if(temp == null)
		{
			add = true;
			userDaoImpl.add(user);	
		}
		return add;
	}

	@Override
	public User Login(String username, String password) {
		User temp = userDaoImpl.selectForLogin(username, password);
		return temp;
	}

	@Override
	public boolean update(User user) {
		boolean update = false;
		User temp = userDaoImpl.selectbyUsername(user.getUsername());
		if(temp != null)
		{
			update = true;
			userDaoImpl.update(user);
		}
		return update;
	}

	@Override
	public boolean delete(User user) {
		boolean delete = false;
		User temp = userDaoImpl.selectbyUsername(user.getUsername());
		if( temp != null)
		{
			delete = true;
			userDaoImpl.delete(user);
		}
		return delete;
	}

}
