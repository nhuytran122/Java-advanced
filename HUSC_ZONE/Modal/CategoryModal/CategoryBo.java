package CategoryModal;

import java.util.ArrayList;

public class CategoryBo {
	CategoryDao cateDao = new CategoryDao();
	
	public ArrayList<Category> getListCategories() throws Exception{
		return cateDao.getListCategories();
	}
	
	public Category getCategoryByID(Long cateID) throws Exception {
		return cateDao.getCategoryByID(cateID);
	}
	
	public ArrayList<Category> getListCategoriesByCondition(int page, int pageSize, String searchValue) throws Exception {
		return cateDao.getListCategoriesByCondition(page, pageSize, searchValue);
	}
	
	public int countCategoriesByCondition(String searchValue) throws Exception {
		return cateDao.countCategoriesByCondition(searchValue);
	}
	
	public int addCategory(String cateName, String img) throws Exception {
		return cateDao.addCategory(cateName, img);
	}
	
	public int updateCategory(Long cateID, String cateName, String img) throws Exception {
		return cateDao.updateCategory(cateID, cateName, img);
	}
	
	public int deleteCategory(Long cateID) throws Exception {
		return cateDao.deleteCategory(cateID);
	}
	
}
