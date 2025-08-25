package daO;

import model.Tax;

public interface TaxDao {
	//create
	void add(Tax tax);
	//read
	Tax select(Tax tax);
	Tax selectByDeclarationId(String declarationId);
	//update
	void update(Tax tax);
	//delete
	void delete(Tax tax);
}
