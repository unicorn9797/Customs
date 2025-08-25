package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;




public class IdGenerator {	
	
	public static String generateDeclarationId()
	{
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String sql = "select declarationId from declaration where declarationId like ? "
				+ "order by declarationId desc limit 1";
		int counter = 1;
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, "DEC" + today + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				String lastId = resultSet.getString("declarationId");
				String[] numParts = lastId.split("-"); 
				counter = Integer.parseInt(numParts[1]) + 1;
			}
		}
		catch(SQLException e)
		{
			throw new RuntimeException("生成 declarationId 失敗", e);
		}		
		String newId = "DEC" + today + "-" +  String.format("%03d", counter);
		
		return newId;
	}
	
	public static String generateGoodsId(String declarationId, int itemIndex)
	{
		return declarationId + "-" + String.format("%02d", itemIndex);
	}
	
	public static String generateTransportId(String declarationId, String VesselOrFlight)
	{
		return VesselOrFlight + "-" + declarationId;
	}
	
	public static String generateTaxId(String declarationId)
	{		
		int count = 1;
		String sql = "select taxId from tax where taxId like ? order by taxId desc limit 1";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, declarationId + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				String lastTaxId = resultSet.getString("taxId");
				String[] parts = lastTaxId.split("TAX");
				try 
				{
					count = Integer.parseInt(parts[1]) + 1;
				}catch(NumberFormatException e)
				{
					count = 1;
				}				
			}
		}
		catch(SQLException e)
		{
			throw new RuntimeException("生成 taxId 失敗", e);
		}
		
		return declarationId + "-TAX" + String.format("%03d", count);
	}
}
