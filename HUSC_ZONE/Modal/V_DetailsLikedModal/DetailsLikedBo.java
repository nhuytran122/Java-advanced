package V_DetailsLikedModal;

import java.util.ArrayList;

public class DetailsLikedBo {
    DetailsLikedDao dtLDao = new DetailsLikedDao();

    public ArrayList<DetailsLiked> getListLikesByUserID(int page, int pageSize, Long userID) throws Exception {
        return dtLDao.getListLikesByUserID(page, pageSize, userID);
    }
    
    public int getCountLikesByUserID(Long userID) throws Exception {
    	return dtLDao.getCountLikesByUserID(userID);
    }

}
