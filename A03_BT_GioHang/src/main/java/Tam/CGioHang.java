package Tam;
import java.util.ArrayList;
import java.util.List;

public class CGioHang {
//Tao ra 1 mang de luu Hang
	public List<Hang> ds = new ArrayList<Hang>();
	public void Them(String th, int gia, int sl){
		int n = ds.size();
		for(int i=0; i<n; i++){
			if(ds.get(i).getTenhang().toLowerCase().trim().equals(th.toLowerCase().trim())){
				int slt = ds.get(i).getSoluong() + sl;
				ds.get(i).setSoluong(slt);
				int g = ds.get(i).getGia();
				int tt = slt*g;
				ds.get(i).setThanhtien(tt);
				return;
			}
		}
		Hang h = new Hang(th, gia, sl);
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
            if (h.getTenhang().toLowerCase().trim().equals(th.toLowerCase().trim())) {
                h.setSoluong(slMoi);
                int g = h.getGia();
                int tt = slMoi * g;
                h.setThanhtien(tt);
                break;
            }
        }
    }
}