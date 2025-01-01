package CommonModal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import CategoryModal.Category;
import CategoryModal.CategoryBo;
import MaterialModal.Material;
import MaterialModal.MaterialBo;
import V_DetailsNotificationModal.DetailsNotification;
import V_DetailsNotificationModal.DetailsNotificationBo;

public class MethodCommon {

	public static ArrayList<Category> getListCates() throws Exception {
        CategoryBo cateBo = new CategoryBo();
        return cateBo.getListCategories();
    }
	
	public static ArrayList<Material> getListMates() throws Exception {
        MaterialBo mateBo = new MaterialBo();
        return mateBo.getListMaterials();
    }
	
	public static ArrayList<DetailsNotification> getListNotis(Long userID) throws Exception {
        DetailsNotificationBo notiBo = new DetailsNotificationBo();
        return notiBo.getNotificationsByUserID(userID);
    }
	
	public static String convertDateToString(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDay = sdf.format(d);
		return strDay;
	}
	
	public static int calculatePageCount(int rowCount, int pageSize) {
    	int pageCount = rowCount / pageSize;
        if (rowCount % pageSize > 0) {
            pageCount += 1;
        }
        return pageCount;
    }
}
