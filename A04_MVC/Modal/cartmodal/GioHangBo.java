package cartmodal;
import java.util.ArrayList;

import sachmodal.sach;
import sachmodal.sachbo;

public class GioHangBo {
//Tao ra 1 mang de luu Hang
	public ArrayList<Hang> ds = new ArrayList<Hang>();
	
	public void Them(String id, int sl) {
	    sachbo sb = new sachbo();
	    sach book = sb.TimTheoId(id);
	    for (Hang h : ds) {
	    	if(h.getSach().getMasach().equals(book.getMasach())) {
	    		int slt = h.getSoluong() + sl;
	    		h.setSoluong(slt);
	    		Long g = h.getSach().getGia();
	    		Long tt = slt * g;
	    		h.setThanhtien(tt);
	    		return;
	    	}
	    }
	    Hang h = new Hang(book, sl);
	    ds.add(h);
	}

	public long Tongtien(){
		long s = 0;
		for(Hang h : ds){
			s += h.getThanhtien();
		}
		return s;
	}
	
    public void CapNhatSoLuong(String masach, int slMoi) {
        for (Hang h : ds) {
        	sach s = h.getSach();
            if (s.getMasach().equals(masach)) {
                h.setSoluong(slMoi);
                Long g = s.getGia();
                Long tt = slMoi * g;
                h.setThanhtien(tt);
                break;
            }
        }
    }
    
    public void Xoa(String masach) {
        for (Hang h : ds) {
            if (h.getSach().getMasach().equals(masach)) {
                ds.remove(h);
                break;
            }
        }
    }
}