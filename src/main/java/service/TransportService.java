package service;

import model.Transport;

public interface TransportService {
	//create
	void addTransportToDeclaration(String declarationId, Transport transport);
	//read
	Transport selectByDeclarationId(String declarationId);
	//update
	boolean update(Transport transport);
	//delete
	boolean delete(Transport transport);
}
