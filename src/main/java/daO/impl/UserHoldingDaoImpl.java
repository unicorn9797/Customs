package daO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daO.UserHoldingDao;
import model.UserHolding;
import util.DataBaseConnectionPool;

public class UserHoldingDaoImpl implements UserHoldingDao{

	@Override
	public void add(UserHolding userHolding) {
		String sql = "insert into userholding(username, declarationId, role) values(?,?,?)";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, userHolding.getUsername());
			preparedStatement.setString(2, userHolding.getDeclarationId());
			preparedStatement.setString(3, userHolding.getRole());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public List<UserHolding> selectByUsername(String username) {
		String sql = "select * from userholding where username = ?";
		List<UserHolding> tempList = new ArrayList<>();
		
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				UserHolding temp = new UserHolding();
				temp.setUsername(resultSet.getString("username"));
				temp.setDeclarationId(resultSet.getString("declarationId"));
				temp.setRole(resultSet.getString("role"));
				temp.setId(resultSet.getInt("id"));
				tempList.add(temp);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return tempList;
	}
	
	@Override
	public UserHolding selectById(int id) {
		String sql = "select * from userholding where id = ?";
		UserHolding temp = null;
		
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				temp = new UserHolding();
				temp.setUsername(resultSet.getString("username"));
				temp.setDeclarationId(resultSet.getString("declarationId"));
				temp.setRole(resultSet.getString("role"));
				temp.setId(resultSet.getInt("id"));				
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}


	@Override
	public void update(UserHolding userHolding) {
		String sql = "update userholding set username = ?, declarationId = ?, role = ? where id = ?";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, userHolding.getUsername());
			preparedStatement.setString(2, userHolding.getDeclarationId());
			preparedStatement.setString(3, userHolding.getRole());
			preparedStatement.setInt(4, userHolding.getId());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(UserHolding userHolding) {
		String sql = "delete from userholding where id = ?";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setInt(1, userHolding.getId());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	
}
