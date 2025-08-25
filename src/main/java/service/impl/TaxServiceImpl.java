package service.impl;

import daO.impl.TaxDaoImpl;
import model.Tax;
import service.TaxService;
import util.IdGenerator;

public class TaxServiceImpl implements TaxService {

	private static TaxDaoImpl taxDaoImpl = new TaxDaoImpl();
	
	@Override
	public void addTax(Tax tax) {
		tax.setTaxId(IdGenerator.generateTaxId(tax.getDeclarationId()));
		taxDaoImpl.add(tax);
	}

	@Override
	public Tax selectByDeclarationId(String declarationId) {
		Tax temp = taxDaoImpl.selectByDeclarationId(declarationId);
		return temp;
	}

	@Override
	public boolean update(Tax tax) {
		boolean update = false;
		Tax temp =taxDaoImpl.select(tax);
		if( temp != null)
		{
			update = true;
			taxDaoImpl.update(tax);
		}
		return update;
	}

	@Override
	public boolean delete(Tax tax) {
		boolean delete = false;
		Tax temp =taxDaoImpl.select(tax);
		if( temp != null)
		{
			delete = true;
			taxDaoImpl.delete(tax);
		}
		return delete;
	}

}
