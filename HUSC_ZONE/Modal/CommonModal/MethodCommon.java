package CommonModal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import CategoryModal.Category;
import CategoryModal.CategoryBo;
import MaterialModal.Material;
import MaterialModal.MaterialBo;

public class MethodCommon {

	public static ArrayList<Category> getListCates() throws Exception {
        CategoryBo cateBo = new CategoryBo();
        return cateBo.getListCategories();
    }
	
	public static ArrayList<Material> getListMates() throws Exception {
        MaterialBo mateBo = new MaterialBo();
        return mateBo.getListMaterials();
    }
	
	public static String convertDateToString(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDay = sdf.format(d);
		return strDay;
	}
}
