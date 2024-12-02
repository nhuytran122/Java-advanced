package V_DetailsDoc;

import java.util.ArrayList;

public class DetailsDocBo {
	
	DetailsDocDao dtdocDao = new DetailsDocDao();
	
	public DetailsDoc getDetailsDocByID(Long docID) throws Exception {
		return dtdocDao.getDetailsDocByID(docID);
	}
	
	public ArrayList<DetailsDoc> getListDocsSuggest(Long docID, Long cateID) throws Exception {
		return dtdocDao.getListDocsSuggest(docID, cateID);
	}
}
