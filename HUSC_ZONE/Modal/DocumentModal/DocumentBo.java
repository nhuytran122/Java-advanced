package DocumentModal;

import java.util.ArrayList;

public class DocumentBo {

	DocumentDao docDao = new DocumentDao();
	
	public ArrayList<Document> getListDocs(int page, int pageSize, String searchValue, Long categoryID, Long materialID, Long userID) throws Exception {
		return docDao.getListDocs(page, pageSize, searchValue, categoryID, materialID, userID);
	}
	
	public int getRowCount(String searchValue, Long categoryID, Long materialID, Long userID) throws Exception {
		return docDao.getRowCount(searchValue, categoryID, materialID, userID);
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
