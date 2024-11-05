package cartmodal;
import java.util.ArrayList;

public class GioHangBo {
	public ArrayList<Hang> ds = new ArrayList<Hang>();
	
	public void Them(String masach, String tensach, Long gia) {
			 for(Hang h: ds)
				 if(h.getMasach().equals(masach)) {
					 h.setSoluong(h.getSoluong() + 1);
					 return;
				 }
			  ds.add(new Hang(masach, tensach, gia, (long)1));
		  }

	public long Tongtien(){
		long s = 0;
		for(Hang h : ds){
			s += h.getThanhtien();
		}
		return s;
	}
	
    public void CapNhatSoLuong(String masach, long slMoi) {
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
}