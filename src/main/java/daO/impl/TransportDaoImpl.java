package daO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import daO.TransportDao;
import model.Transport;
import util.DataBaseConnectionPool;

public class TransportDaoImpl implements TransportDao {

	@Override
	public void add(Transport transport) {
		String sql = "insert into transport(transportId, declarationId, vesselOrFlight, portOfLoading, portOfDischarge,"
				+ " departureDate, arrivalDate) values(?,?,?,?,?,?,?)";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, transport.getTransportId());
			preparedStatement.setString(2, transport.getDeclarationId());
			preparedStatement.setString(3, transport.getVesselOrFlight());
			preparedStatement.setString(4, transport.getPortOfLoading());
			preparedStatement.setString(5, transport.getPortOfDischarge());
			preparedStatement.setObject(6, transport.getDepartureDate());
			preparedStatement.setObject(7, transport.getArrivalDate());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public Transport select(Transport transport) {
		String sql = "select * from transport where transportId = ?";
		Transport temp = null;
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			
			preparedStatement.setString(1, transport.getTransportId());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				temp = new Transport();
				temp.setTransportId(transport.getTransportId());
				temp.setDeclarationId(resultSet.getString("declarationId"));
				temp.setVesselOrFlight(resultSet.getString("vesselOrFlight"));
				temp.setPortOfLoading(resultSet.getString("portOfLoading"));
				temp.setPortOfDischarge(resultSet.getString("portOfDischarge"));
				temp.setDepartureDate(resultSet.getObject("departureDate", LocalDateTime.class));
				temp.setArrivalDate(resultSet.getObject("arrivalDate", LocalDateTime.class));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}
	
	@Override
	public Transport selectByDeclarationId(String declarationId) {
		String sql = "select * from transport where declarationId = ?";
		Transport temp = null;
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			
			preparedStatement.setString(1, declarationId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				temp = new Transport();
				temp.setTransportId(resultSet.getString("transportId"));
				temp.setDeclarationId(resultSet.getString("declarationId"));
				temp.setVesselOrFlight(resultSet.getString("vesselOrFlight"));
				temp.setPortOfLoading(resultSet.getString("portOfLoading"));
				temp.setPortOfDischarge(resultSet.getString("portOfDischarge"));
				temp.setDepartureDate(resultSet.getObject("departureDate", LocalDateTime.class));
				temp.setArrivalDate(resultSet.getObject("arrivalDate", LocalDateTime.class));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public void update(Transport transport) {
		String sql = "update transport set declarationId = ?,vesselOrFlight = ?,portOfLoading = ?,"
				+ "portOfDischarge = ?,departureDate = ?,ArrivalDate = ? where transportId = ?";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, transport.getDeclarationId());
			preparedStatement.setString(2, transport.getVesselOrFlight());
			preparedStatement.setString(3, transport.getPortOfLoading());
			preparedStatement.setString(4, transport.getPortOfDischarge());
			preparedStatement.setObject(5, transport.getDepartureDate());
			preparedStatement.setObject(6, transport.getArrivalDate());
			preparedStatement.setString(7, transport.getTransportId());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Transport transport) {
		String sql = "delete from transport where transportId = ?";
		try(Connection connection = DataBaseConnectionPool.getDataBaseConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			preparedStatement.setString(1, transport.getTransportId());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	

}
