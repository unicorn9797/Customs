package daO;

import java.util.List;

import model.Goods;

public interface GoodsDao {
	//create
	void add(Goods goods);
	//read
	Goods select(Goods goods);
	List<Goods> selectByDeclarationId(String declarationId);
	//update
	void update(Goods goods);
	//delete
	void delete(Goods goods);
}
