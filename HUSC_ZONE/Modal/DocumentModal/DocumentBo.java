package DocumentModal;

public class DocumentBo {

	DocumentDao docDao = new DocumentDao();
	
	public Document getDocumentByID(Long documentID) throws Exception {
		return docDao.getDocumentByID(documentID);
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
}
