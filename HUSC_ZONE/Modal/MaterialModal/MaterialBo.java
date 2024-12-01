package MaterialModal;

import java.util.ArrayList;

public class MaterialBo {

	MaterialDao mateDao = new MaterialDao();
	
	public ArrayList<Material> getListMaterials() throws Exception{
		return mateDao.getListMaterials();
	}
	
	public Material getMaterialByID(Long mateID) throws Exception {
		return mateDao.getMaterialByID(mateID);
	}
}
