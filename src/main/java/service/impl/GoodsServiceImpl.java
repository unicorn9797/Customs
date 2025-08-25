package service.impl;

import java.util.ArrayList;
import java.util.List;

import daO.impl.GoodsDaoImpl;
import model.Goods;
import service.GoodsService;
import util.IdGenerator;

public class GoodsServiceImpl implements GoodsService {
	
	private static GoodsDaoImpl goodsDaoImpl = new GoodsDaoImpl();	

	@Override
	public void addGoodsToDeclaration(String declarationId, List<Goods> goodsList) {
		 
		for(int i = 0; i< goodsList.size(); i++)
		{
			Goods temp = goodsList.get(i);			
			int itemIndex = i + 1;			
			temp.setGoodsId(IdGenerator.generateGoodsId(declarationId, itemIndex));
			temp.setDeclarationId(declarationId);
			goodsDaoImpl.add(temp);
		}
	}

	@Override
	public List<Goods> selectByDeclarationId(String declarationId) {
		List<Goods> tempList = new ArrayList<>();
		tempList = goodsDaoImpl.selectByDeclarationId(declarationId);
		return tempList;
	}

	@Override
	public boolean update(Goods goods) {
		boolean update = false;
		Goods temp = goodsDaoImpl.select(goods);
		if(temp != null)
		{
			update = true;
			goodsDaoImpl.update(goods);
		}
		return update;
	}

	@Override
	public boolean delete(Goods goods) {
		boolean delete = false;
		Goods temp = goodsDaoImpl.select(goods);
		if(temp != null)
		{
			delete = true;
			goodsDaoImpl.update(goods);
		}
		return delete;
	}

}
