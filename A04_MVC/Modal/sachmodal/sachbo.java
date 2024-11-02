package sachmodal;

import java.util.ArrayList;

public class sachbo {
	sachdao sdao = new sachdao();
	ArrayList<sach> ds;
//	public ArrayList<sach> getSach() throws Exception{
//		ds =  sdao.getSach();
//		return ds;
//	}
	public ArrayList<sach> getListSach(int page, int pageSize, String searchValue) throws Exception{
		ds =  sdao.getListSach(page, pageSize, searchValue);
		return ds;
	}
	
//	public ArrayList<sach> TimMa(String maloai){
//		ArrayList<sach> tam = new ArrayList<sach>();		
//		for(sach s : ds) {
//			if(s.getMaloai().toLowerCase().trim().equals(
//					maloai.toLowerCase().trim())) {
//				tam.add(s);
//			}
//		}
//		return tam;
//	}
//	
//	public ArrayList<sach> Tim(String key){
//		ArrayList<sach> tam = new ArrayList<sach>();
//		String tmpKey = key.toLowerCase().trim();
//		for(sach s : ds) {
//			if(s.getTensach().toLowerCase().trim().contains(tmpKey) || 
//					s.getTacgia().toLowerCase().trim().equals(tmpKey)) {
//				tam.add(s);
//			}
//		}
//		return tam;
//	}
	
	public sach TimTheoId(String id) throws Exception{
		
		return sdao.getSach(id);
	}
	
	public int getRowCount(String searchValue) throws Exception {
		return sdao.getRowCount(searchValue);
	}
	
}