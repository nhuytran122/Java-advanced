package lichsumodal;

import java.util.ArrayList;


public class lichsubo {
	lichsudao lsdao = new lichsudao();
	
	public ArrayList<lichsu> getLichsu(long maKH, boolean flagPurchased) throws Exception {
		return lsdao.getLichsu(maKH, flagPurchased);
	}
	
	public ArrayList<lichsu> getCTHDByOrderID(long maHD) throws Exception {
		return lsdao.getCTHDByOrderID(maHD);
	}
}
