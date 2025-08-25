package daO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daO.TaxDao;
import model.Tax;
import util.DataBaseConnectionPool;

public class TaxDaoImpl implements TaxDao {

	@Override
	public void add(Tax tax) {
		String sql = "insert into tax(taxId, declarationId, declarationType, itemName, hsCode, customDuty, vat, otherTax, totalTax) values(?,?,?,?,?,?,?,?,?)";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, tax.getTaxId());
			preparedStatement.setString(2, tax.getDeclarationId());
			preparedStatement.setString(3, tax.getDeclarationType());
			preparedStatement.setString(4, tax.getItemName());
			preparedStatement.setString(5, tax.getHsCode());
			preparedStatement.setDouble(6, tax.getCustomsDuty());
			preparedStatement.setDouble(7, tax.getVat());
			preparedStatement.setDouble(8, tax.getOtherTax());
			preparedStatement.setDouble(9, tax.getTotalTax());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Tax select(Tax tax) {
		String sql = "select * from tax where taxId = ?";
		Tax temp = null;
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{			
			preparedStatement.setString(1, tax.getTaxId());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				temp = new Tax();
				temp.setDeclarationId(resultSet.getString("declarationId"));
				temp.setDeclarationType(resultSet.getString("declarationType"));
				temp.setItemName(resultSet.getString("ItemName"));
				temp.setHsCode(resultSet.getString("hsCode"));
				temp.setCustomsDuty(resultSet.getDouble("customDuty"));
				temp.setOtherTax(resultSet.getDouble("otherTax"));
				temp.setVat(resultSet.getDouble("vat"));
				temp.setTotalTax(resultSet.getDouble("totalTax"));
				temp.setTaxId(tax.getTaxId());								
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
	
	@Override
	public Tax selectByDeclarationId(String declarationId) {
		String sql = "select * from tax where declarationId = ?";
		Tax temp = null;
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{			
			preparedStatement.setString(1, declarationId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				temp = new Tax();
				temp.setDeclarationId(resultSet.getString("declarationId"));
				temp.setDeclarationType(resultSet.getString("declarationType"));
				temp.setItemName(resultSet.getString("ItemName"));
				temp.setHsCode(resultSet.getString("hsCode"));
				temp.setCustomsDuty(resultSet.getDouble("customDuty"));
				temp.setOtherTax(resultSet.getDouble("otherTax"));
				temp.setVat(resultSet.getDouble("vat"));
				temp.setTotalTax(resultSet.getDouble("totalTax"));
				temp.setTaxId(resultSet.getString("taxId"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public void update(Tax tax) {
		String sql = "update tax set declarationId = ?, declarationType = ?, itemName = ?, hsCode = ?, customDuty = ?, vat = ?, otherTax = ?, totalTax = ? where taxid = ?";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, tax.getDeclarationId());
			preparedStatement.setString(2, tax.getDeclarationType());
			preparedStatement.setString(3, tax.getItemName());
			preparedStatement.setString(4, tax.getHsCode());
			preparedStatement.setDouble(5, tax.getCustomsDuty());
			preparedStatement.setDouble(6, tax.getVat());
			preparedStatement.setDouble(7, tax.getOtherTax());
			preparedStatement.setDouble(8, tax.getTotalTax());
			preparedStatement.setString(9, tax.getTaxId());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Tax tax) {
		String sql = "delete from tax where taxId = ?";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, tax.getTaxId());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	

}
