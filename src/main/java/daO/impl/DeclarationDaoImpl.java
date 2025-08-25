package daO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import daO.DeclarationDao;
import model.Declaration;
import util.DataBaseConnectionPool;

public class DeclarationDaoImpl implements DeclarationDao{	
	
	@Override
	public void add(Declaration declaration) {
		String sql = "insert into declaration(declarationId, declarationDate, declarationType," +
			      "importerName, importerId, exporterName, exporterId, taxFound) values(?,?,?,?,?,?,?,?)";
		
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{			
			preparedStatement.setString(1, declaration.getDeclarationId());
			preparedStatement.setObject(2, declaration.getDeclarationDate());
			preparedStatement.setString(3, declaration.getDeclarationType());
			preparedStatement.setString(4, declaration.getImporterName());
			preparedStatement.setString(5, declaration.getImporterId());
			preparedStatement.setString(6, declaration.getExporterName());
			preparedStatement.setString(7, declaration.getExporterId());
			preparedStatement.setDouble(8, declaration.getTaxFound());
			preparedStatement.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Declaration select(Declaration declaration) {
		String sql = "select * from declaration where declarationId = ?";
		Declaration temp = null;
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, declaration.getDeclarationId());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				temp = new Declaration();
				temp.setDeclarationId(resultSet.getString("declarationId"));
				temp.setDeclarationDate(resultSet.getObject("declarationDate", LocalDateTime.class));
				temp.setDeclarationType(resultSet.getString("declarationType"));
				temp.setImporterName(resultSet.getString("importerName"));
				temp.setImporterId(resultSet.getString("importerId"));
				temp.setExporterName(resultSet.getString("exporterName"));
				temp.setExporterId(resultSet.getString("exporterId"));
				temp.setTransportMode(resultSet.getString("transportMode"));
				temp.setStatus(resultSet.getString("status"));
				temp.setDigitalSignature(resultSet.getString("digitalSignature"));
				temp.setPublicKey(resultSet.getString("publicKey"));
				temp.setTaxFound(resultSet.getDouble("taxFound"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
	
	@Override
	public List<Declaration> selectAll() {
		String sql = "select * from declaration";
		List<Declaration> tempList = new ArrayList<>();
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Declaration temp = new Declaration();
				temp.setDeclarationId(resultSet.getString("declarationId"));
				temp.setDeclarationDate(resultSet.getObject("declarationDate", LocalDateTime.class));
				temp.setDeclarationType(resultSet.getString("declarationType"));
				temp.setImporterName(resultSet.getString("importerName"));
				temp.setImporterId(resultSet.getString("importerId"));
				temp.setExporterName(resultSet.getString("exporterName"));
				temp.setExporterId(resultSet.getString("exporterId"));
				temp.setTransportMode(resultSet.getString("transportMode"));
				temp.setStatus(resultSet.getString("status"));
				temp.setDigitalSignature(resultSet.getString("digitalSignature"));
				temp.setPublicKey(resultSet.getString("publicKey"));
				temp.setTaxFound(resultSet.getDouble("taxFound"));
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
	public void update(Declaration declaration) {
		String sql = "update declaration set declarationDate = ?, declarationType = ?, importerName = ?,"
				+ "importerId = ?, exporterName = ?,exporterId = ?, transportMode = ?,"
				+ "status = ?, digitalSignature = ?, publicKey = ?, taxFound = ?  where declarationId = ?";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setObject(1, declaration.getDeclarationDate());
			preparedStatement.setString(2, declaration.getDeclarationType());
			preparedStatement.setString(3, declaration.getImporterName());
			preparedStatement.setString(4, declaration.getImporterId());
			preparedStatement.setString(5, declaration.getExporterName());
			preparedStatement.setString(6, declaration.getExporterId());
			preparedStatement.setString(7, declaration.getTransportMode());
			preparedStatement.setString(8, declaration.getStatus());
			preparedStatement.setString(9, declaration.getDigitalSignature());
			preparedStatement.setString(10, declaration.getPublicKey());
			preparedStatement.setDouble(11, declaration.getTaxFound());
			preparedStatement.setString(12, declaration.getDeclarationId());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Declaration declaration) {
		String sql = "delete from declaration where declarationId = ?";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, declaration.getDeclarationId());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	

}
