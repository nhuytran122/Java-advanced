package MaterialModal;

import java.util.ArrayList;

public class MaterialBo {

	MaterialDao mateDao = new MaterialDao();
	
	public ArrayList<Material> getListMaterials() throws Exception{
		return mateDao.getListMaterials();
	}
}
