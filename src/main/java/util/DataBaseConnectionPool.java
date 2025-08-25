package util;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBaseConnectionPool {
	
	private static HikariDataSource dataSource = null;
	
	
	static
	{
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/customs?serverTimezone=UTC&useSSL=false");
		config.setUsername("root");
		config.setPassword("1234");
		config.setMaximumPoolSize(10);//連線池最多允許10條連線
		config.setMinimumIdle(2);//最少2條閒置連線
		config.setIdleTimeout(60000);//單位為ms(千分之一秒)這邊設置閒置60秒回收連線
		config.setConnectionTimeout(30000);//最多等30秒來等待連線
		
		dataSource = new HikariDataSource(config);
	}
	
	public static Connection getDataBaseConnection()
	{
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}

}
