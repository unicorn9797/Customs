package service;

import java.security.PrivateKey;
import java.util.List;

import model.Declaration;

public interface DeclarationService {
	//create
	void addDeclaration(Declaration declaration);
	//read
	Declaration selectByDeclarationId(String declarationId);
	List<Declaration> getAllDeclarations();
	//update
	boolean update(Declaration declaration);
	//delete
	boolean delete(Declaration declaration);
	//做簽章
	void declarationSign(Declaration declaration, PrivateKey privateKey);
	
}
