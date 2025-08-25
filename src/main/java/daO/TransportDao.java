package daO;

import model.Transport;

public interface TransportDao {
	//create
	void add(Transport transport);
	//read
	Transport select(Transport transport);
	Transport selectByDeclarationId(String declarationId);
	//update
	void update(Transport transport);
	//delete
	void delete(Transport transport);
}
