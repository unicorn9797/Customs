package daO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daO.UserDao;
import model.User;
import util.DataBaseConnectionPool;

public class UserDaoImpl implements UserDao {

	@Override
	public void add(User user) {
		String sql = "insert into user(name, username, password, role, privateKey, publicKey) values(?,?,?,?,?,?)";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getRole());
			preparedStatement.setString(5, user.getPrivateKey());
			preparedStatement.setString(6, user.getPublicKey());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public User selectbyUsername(String username) {
		String sql = "select * from user where username = ?";
		User temp = null;
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{			
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				temp = new User();
				temp.setName(resultSet.getString("name"));
				temp.setUsername(resultSet.getString("username"));
				temp.setPassword(resultSet.getString("password"));
				temp.setRole(resultSet.getString("role"));
				temp.setPrivateKey(resultSet.getString("privateKey"));
				temp.setPublicKey(resultSet.getString("publicKey"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
	
	@Override
	public User selectForLogin(String username, String password) {
		String sql = "select * from user where username = ? and password = ?";
		User temp = null;
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{			
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				temp = new User();
				temp.setName(resultSet.getString("name"));
				temp.setUsername(resultSet.getString("username"));
				temp.setPassword(resultSet.getString("password"));
				temp.setRole(resultSet.getString("role"));
				temp.setPrivateKey(resultSet.getString("privateKey"));
				temp.setPublicKey(resultSet.getString("publicKey"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public void update(User user) {
		String sql = "update user set name = ?, password = ?, role = ? where username = ?";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getRole());
			preparedStatement.setString(4, user.getUsername());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(User user) {
		String sql = "delete from user where username = ?";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	

}
