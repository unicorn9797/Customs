package service.impl;

import daO.impl.TransportDaoImpl;
import model.Transport;
import service.TransportService;
import util.IdGenerator;

public class TransportServiceImpl implements TransportService {
	
	private static TransportDaoImpl transportDaoImpl = new TransportDaoImpl();

	@Override
	public void addTransportToDeclaration(String declarationId, Transport transport) {
		transport.setTransportId(IdGenerator.generateTransportId(declarationId, transport.getVesselOrFlight()));
		transportDaoImpl.add(transport);
	}

	@Override
	public Transport selectByDeclarationId(String DeclarationId) {
		Transport temp = transportDaoImpl.selectByDeclarationId(DeclarationId);
		return temp;
	}

	@Override
	public boolean update(Transport transport) {
		boolean update = false;
		Transport temp = transportDaoImpl.select(transport);
		if(temp != null)
		{
			update = true;
			transportDaoImpl.update(transport);
		}
		return update;
	}

	@Override
	public boolean delete(Transport transport) {
		boolean delete = false;
		Transport temp = transportDaoImpl.select(transport);
		if(temp != null)
		{
			delete = true;
			transportDaoImpl.delete(transport);
		}
		return delete;
	}

}
