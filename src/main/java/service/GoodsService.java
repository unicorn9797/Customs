package service;

import java.util.List;

import model.Goods;

public interface GoodsService {
	//create
	void addGoodsToDeclaration(String declarationId, List<Goods> goodsList);
	//read
	List<Goods> selectByDeclarationId(String declarationId);
	//update
	boolean update(Goods goods);
	//delete
	boolean delete(Goods goods);

}
