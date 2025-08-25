package service.impl;

import java.util.ArrayList;
import java.util.List;

import daO.impl.UserHoldingDaoImpl;
import model.UserHolding;
import service.UserHoldingService;

public class UserHoldingServiceImpl implements UserHoldingService {
	
	private static UserHoldingDaoImpl userHoldingDaoImpl = new UserHoldingDaoImpl();

	@Override
	public void addUserHolding(UserHolding userHolding) {
		userHoldingDaoImpl.add(userHolding);		
	}

	@Override
	public List<UserHolding> selectByUsername(String username) {
		List<UserHolding> tempList = new ArrayList<>();
		tempList = userHoldingDaoImpl.selectByUsername(username);
		return tempList;
	}

	@Override
	public boolean update(UserHolding userHolding) {
		boolean update = false;
		UserHolding temp = userHoldingDaoImpl.selectById(userHolding.getId());
		if(temp != null)
		{
			update = true;
			userHoldingDaoImpl.update(userHolding);
		}
		return update;
	}

	@Override
	public boolean delete(UserHolding userHolding) {
		boolean delete = false;
		UserHolding temp = userHoldingDaoImpl.selectById(userHolding.getId());
		if(temp != null)
		{
			delete = true;
			userHoldingDaoImpl.delete(userHolding);
		}
		return delete;
	}

}
