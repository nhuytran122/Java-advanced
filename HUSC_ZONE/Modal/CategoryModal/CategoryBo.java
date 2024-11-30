package CategoryModal;

import java.util.ArrayList;

public class CategoryBo {
	CategoryDao cateDao = new CategoryDao();
	
	public ArrayList<Category> getListCategories() throws Exception{
		return cateDao.getListCategories();
	}
	
}
