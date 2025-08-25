package service;

import model.Tax;

public interface TaxService {
	//create
	void addTax(Tax tax);
	//read
	Tax selectByDeclarationId(String declarationId);	
	//update
	boolean update(Tax tax);
	//delete
	boolean delete(Tax tax);

}
