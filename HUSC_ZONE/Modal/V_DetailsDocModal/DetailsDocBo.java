package V_DetailsDocModal;

import java.util.ArrayList;

public class DetailsDocBo {
	
	DetailsDocDao dtdocDao = new DetailsDocDao();
	
	public DetailsDoc getDetailsDocByID(Long docID) throws Exception {
		return dtdocDao.getDetailsDocByID(docID);
	}
	
	public ArrayList<DetailsDoc> getListDocsSuggest(Long docID, Long cateID) throws Exception {
		return dtdocDao.getListDocsSuggest(docID, cateID);
	}
	
	public int getCountDocsByConditions(String searchValue, Long categoryID, Long materialID) throws Exception {
		return dtdocDao.getCountDocsByConditions(searchValue, categoryID, materialID);
	}
	
	public ArrayList<DetailsDoc> getDocsByUserID(int page, int pageSize, Long userID) throws Exception {
		return dtdocDao.getDocsByUserID(page, pageSize, userID);
	}
	
	public ArrayList<DetailsDoc> getDocsByConditions(int page, int pageSize, String searchValue,
			Long categoryID, Long materialID) throws Exception {
		return dtdocDao.getDocsByConditions(page, pageSize, searchValue, categoryID, materialID);
	}
}
