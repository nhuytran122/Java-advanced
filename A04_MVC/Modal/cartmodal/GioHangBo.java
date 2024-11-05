package cartmodal;
import java.util.ArrayList;

import sachmodal.sach;
import sachmodal.sachbo;

public class GioHangBo {
//Tao ra 1 mang de luu Hang
	public ArrayList<Hang> ds = new ArrayList<Hang>();
	
	public void Them(String masach, String tensach, Long gia) {
			 for(Hang h: ds)
				 if(h.getMasach().equals(masach)) {
					 h.setSoluong(h.getSoluong() + 1);
					 return;
				 }
			  ds.add(new Hang(masach, tensach, gia, 1));
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
            if (h.getMasach().equals(masach)) {
                h.setSoluong(slMoi);
                Long g = h.getGia();
                Long tt = slMoi * g;
                h.setThanhtien(tt);
                break;
            }
        }
    }
    
    public void Xoa(String masach) {
        for (Hang h : ds) {
            if (h.getMasach().equals(masach)) {
                ds.remove(h);
                break;
            }
        }
    }
    
    public Hang getHangByMaSach(String masach) {
    	for(Hang h : ds) {
    		if(h.getMasach().equals(masach)) {
    			return h;
    		}
    	}
    	return null;
    }
}