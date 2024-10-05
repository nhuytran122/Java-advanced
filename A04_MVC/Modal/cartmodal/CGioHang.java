package cartmodal;
import java.util.ArrayList;

import sachmodal.sach;
import sachmodal.sachbo;

public class CGioHang {
//Tao ra 1 mang de luu Hang
	public ArrayList<Hang> ds = new ArrayList<Hang>();
	
	public void Them(String id, int sl) {
	    int n = ds.size();
	    sachbo sb = new sachbo();
	    sach book = sb.TimTheoId(id);
	    
	    for (int i = 0; i < n; i++) {
	        if (ds.get(i).getSach().getMasach().equals(book.getMasach())) {
	            int slt = ds.get(i).getSoluong() + sl;
	            ds.get(i).setSoluong(slt);
	            Long g = ds.get(i).getSach().getGia();
	            Long tt = slt * g;
	            ds.get(i).setThanhtien(tt);
	            return;
	        }
	    }
	    Hang h = new Hang(book, sl);
	    ds.add(h);
	}

	public long Tongtien(){
		int n = ds.size();
		long s = 0;
		for(int i=0; i<n; i++){
			s = s + ds.get(i).getThanhtien();
		}
		return s;
	}
	
    public void CapNhatSoLuong(String th, int slMoi) {
        for (Hang h : ds) {
        	sach s = h.getSach();
            if (s.getTensach().toLowerCase().trim().equals(th.toLowerCase().trim())) {
                h.setSoluong(slMoi);
                Long g = s.getGia();
                Long tt = slMoi * g;
                h.setThanhtien(tt);
                break;
            }
        }
    }
    
    public void Xoa(String tenHang) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getSach().getTensach().equals(tenHang)) {
                ds.remove(i);
                break;
            }
        }
    }
}