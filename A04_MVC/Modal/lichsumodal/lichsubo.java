package lichsumodal;

import java.util.ArrayList;


public class lichsubo {
	lichsudao lsdao = new lichsudao();
	
	public ArrayList<lichsu> getLichsu(long maKH) throws Exception {
		return lsdao.getLichsu(maKH);
	}
}
