package MaterialModal;

import java.util.ArrayList;

public class MaterialBo {

	MaterialDao mateDao = new MaterialDao();
	
	public Material getMaterialByID(Long mateID) throws Exception {
		return mateDao.getMaterialByID(mateID);
	}
	
	public ArrayList<Material> getListMaterials() throws Exception{
		return mateDao.getListMaterials();
	}
	
	public ArrayList<Material> getListMaterialByCondition(int page, int pageSize, String searchValue) throws Exception {
		return mateDao.getListMaterialByCondition(page, pageSize, searchValue);
	}
	
	public int countMaterialsByCondition(String searchValue) throws Exception {
		return mateDao.countMaterialsByCondition(searchValue);
	}
	
	public int addMaterial(String mateName, String description) throws Exception {
		return mateDao.addMaterial(mateName, description);
	}
	
	public int updateMaterial(Long mateID, String mateName, String description) throws Exception {
		return mateDao.updateMaterial(mateID, mateName, description);
	}
	
	public int deleteMaterial(Long mateID) throws Exception {
		return mateDao.deleteMaterial(mateID);
	}
}
