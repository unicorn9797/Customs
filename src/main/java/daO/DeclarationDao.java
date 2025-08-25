package daO;

import java.util.List;

import model.Declaration;

public interface DeclarationDao {
	//create
	void add(Declaration declaration);
	//read
	Declaration select(Declaration declaration);
	List<Declaration> selectAll();
	//update
	void update(Declaration declaration);
	//delete
	void delete(Declaration declaration);
	
}
