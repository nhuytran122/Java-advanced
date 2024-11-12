package TranNhuYtintucModal;

import java.util.ArrayList;

public class TranNhuY_K45G_tintucbo {
	
	TranNhuY_K45G_tintucdao ttd = new TranNhuY_K45G_tintucdao();
	
	public ArrayList<TranNhuY_K45G_tintuc> getFullTin() throws Exception {
		return ttd.getFullTin();
	}
	
	public ArrayList<TranNhuY_K45G_tintuc> searchTin(String key) throws Exception{
		return ttd.searchTin(key);
	}
}
