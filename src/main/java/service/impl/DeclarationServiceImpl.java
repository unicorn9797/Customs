package service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.util.List;

import daO.impl.DeclarationDaoImpl;
import model.Declaration;
import service.DeclarationService;
import util.DigitalSignature;
import util.IdGenerator;

public class DeclarationServiceImpl implements DeclarationService {

	private static DeclarationDaoImpl declarationDaoImpl = new DeclarationDaoImpl();
	
	@Override
	public void addDeclaration(Declaration declaration) {
		declaration.setDeclarationId(IdGenerator.generateDeclarationId());
		declarationDaoImpl.add(declaration);		
	}

	@Override
	public Declaration selectByDeclarationId(String declarationId) {
		Declaration temp = new Declaration();
		temp.setDeclarationId(declarationId);
		temp = declarationDaoImpl.select(temp);
		return temp;
	}
	
	@Override
	public List<Declaration> getAllDeclarations() {
		List<Declaration> tempList = declarationDaoImpl.selectAll();
		return tempList;
	}


	@Override
	public boolean update(Declaration declaration) {
		boolean update = false;
		Declaration temp = declarationDaoImpl.select(declaration);
		if(temp != null)
		{
			update = true;
			declarationDaoImpl.update(declaration);
		}
		return update;
	}

	@Override
	public boolean delete(Declaration declaration) {
		boolean delete = false;
		Declaration temp = declarationDaoImpl.select(declaration);
		if(temp != null)
		{
			delete = true;
			declarationDaoImpl.delete(declaration);
		}
		return delete;
	}
	
	@Override
	public void declarationSign(Declaration declaration, PrivateKey privateKey) {
		String data = buildDataString(declaration);
		
		try {
			String signature = DigitalSignature.sign(data, privateKey);
			declaration.setDigitalSignature(signature);
			declarationDaoImpl.update(declaration);
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public String buildDataString(Declaration declaration)
	{
		return declaration.getDeclarationId() + "|" + declaration.getImporterName() + "|" + 
			   declaration.getExporterName() + "|" + declaration.getTransportMode() + "|" + declaration.getStatus();
				
				
	}

	
	

	

}
