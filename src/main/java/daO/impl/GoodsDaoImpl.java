package daO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daO.GoodsDao;
import model.Goods;
import util.DataBaseConnectionPool;

public class GoodsDaoImpl implements GoodsDao {

	@Override
	public void add(Goods goods) {
		String sql = "insert into goods(goodsId, declarationId, itemName, hsCode, originCountry,"
				+ "quantity, unitPrice, totalPrice, grossWeight, netWeight, quantityUnit, unitPriceUnit, weightUnit) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, goods.getGoodsId());
			preparedStatement.setString(2, goods.getDeclarationId());
			preparedStatement.setString(3, goods.getItemName());
			preparedStatement.setString(4, goods.getHsCode());
			preparedStatement.setString(5, goods.getOriginCountry());
			preparedStatement.setInt(6, goods.getQuantity());
			preparedStatement.setDouble(7, goods.getUnitPrice());
			preparedStatement.setDouble(8, goods.getTotalPrice());
			preparedStatement.setDouble(9, goods.getGrossWeight());
			preparedStatement.setDouble(10, goods.getNetWeight());
			preparedStatement.setString(11, goods.getQuantityUnit());
			preparedStatement.setString(12, goods.getUnitPriceUnit());
			preparedStatement.setString(13, goods.getWeightUnit());
			preparedStatement.executeUpdate();
		}		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public Goods select(Goods goods) {
		String sql = "select * from goods where goodsId = ?";
		Goods temp = null;
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{			
			preparedStatement.setString(1, goods.getGoodsId());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				temp = new Goods();
				temp.setGoodsId(resultSet.getString("goodsId"));
				temp.setDeclarationId(resultSet.getString("declarationId"));
				temp.setItemName(resultSet.getString("itemName"));
				temp.setHsCode(resultSet.getString("hsCode"));
				temp.setOriginCountry(resultSet.getString("originCountry"));
				temp.setQuantity(resultSet.getInt("quantity"));
				temp.setUnitPrice(resultSet.getDouble("unitPrice"));
				temp.setTotalPrice(resultSet.getDouble("totalPrice"));
				temp.setGrossWeight(resultSet.getDouble("grossWeight"));
				temp.setNetWeight(resultSet.getDouble("netWeight"));
				temp.setQuantityUnit(resultSet.getString("quantityUnit"));
				temp.setUnitPriceUnit(resultSet.getString("uniPriceUnit"));
				temp.setWeightUnit(resultSet.getString("weightUnit"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
	
	@Override
	public List<Goods> selectByDeclarationId(String declarationId) {
		String sql = "select * from goods where declarationId = ?";
		List<Goods> tempList = new ArrayList<>();
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{			
			preparedStatement.setString(1, declarationId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Goods temp = new Goods();
				temp.setGoodsId(resultSet.getString("goodsId"));
				temp.setDeclarationId(resultSet.getString("declarationId"));
				temp.setItemName(resultSet.getString("itemName"));
				temp.setHsCode(resultSet.getString("hsCode"));
				temp.setOriginCountry(resultSet.getString("originCountry"));
				temp.setQuantity(resultSet.getInt("quantity"));
				temp.setUnitPrice(resultSet.getDouble("unitPrice"));
				temp.setTotalPrice(resultSet.getDouble("totalPrice"));
				temp.setGrossWeight(resultSet.getDouble("grossWeight"));
				temp.setNetWeight(resultSet.getDouble("netWeight"));
				temp.setQuantityUnit(resultSet.getString("quantityUnit"));
				temp.setUnitPriceUnit(resultSet.getString("uniPriceUnit"));
				temp.setWeightUnit(resultSet.getString("weightUnit"));
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
	public void update(Goods goods) {
		String sql = "update goods set declarationId = ?, itemName = ?, hsCode = ?,"
				+ "originCountry = ?, quantity = ?, unitPrice = ?, totalPrice = ?, grossWeight = ?,"
				+ "netWeight = ?, quantityUnit = ?, unitPriceUnit = ?, weightUnit = ? where goodsId = ?";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, goods.getDeclarationId());
			preparedStatement.setString(2, goods.getItemName());
			preparedStatement.setString(3, goods.getHsCode());
			preparedStatement.setString(4, goods.getOriginCountry());
			preparedStatement.setInt(5, goods.getQuantity());
			preparedStatement.setDouble(6, goods.getUnitPrice());
			preparedStatement.setDouble(7, goods.getTotalPrice());
			preparedStatement.setDouble(8, goods.getGrossWeight());
			preparedStatement.setDouble(9, goods.getNetWeight());			
			preparedStatement.setString(10, goods.getQuantityUnit());
			preparedStatement.setString(11, goods.getUnitPriceUnit());
			preparedStatement.setString(12, goods.getWeightUnit());
			preparedStatement.setString(13, goods.getGoodsId());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Goods goods) {
		String sql = "delete from goods where goodsId = ?";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, goods.getGoodsId());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	
}
