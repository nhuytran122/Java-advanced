package DocumentModal;

import java.util.ArrayList;

public class DocumentBo {

	DocumentDao docDao = new DocumentDao();
	
	public ArrayList<Document> getDocsByUserID(int page, int pageSize, Long userID) throws Exception {
		return docDao.getDocsByUserID(page, pageSize, userID);
	}
	
	public ArrayList<Document> getDocsByConditions(int page, int pageSize, String searchValue,
			Long categoryID, Long materialID) throws Exception {
		return docDao.getDocsByConditions(page, pageSize, searchValue, categoryID, materialID);
	}
	
	public int getCountDocsByUserID(Long userID) throws Exception {
		return docDao.getCountDocsByUserID(userID);
	}
	
	public int getCountDocsByConditions(String searchValue, Long categoryID, Long materialID) throws Exception {
		return docDao.getCountDocsByConditions(searchValue, categoryID, materialID);
	}
	
	public int addDocument(String title, String description, String filePath, Long categoryID, Long materialID, Long uploadedBy) throws Exception {
		return docDao.addDocument(title, description, filePath, categoryID, materialID, uploadedBy);
	}
	
	public int updateDocument(Long documentID, String title, String description, String filePath, Long categoryID, Long materialID) throws Exception {
		return docDao.updateDocument(documentID, title, description, filePath, categoryID, materialID);
	}
	
	public int deleteDocument(Long documentID) throws Exception {
		return docDao.deleteDocument(documentID);
	}
	
	public Document getDocument(Long documentID) throws Exception {
		return docDao.getDocument(documentID);
	}
}
