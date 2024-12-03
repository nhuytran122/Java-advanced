package hoadonmodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import ketnoimodal.KetNoi;

public class hoadondao {
	public int themHoaDon(long maKh) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "INSERT INTO hoadon (makh, NgayMua, damua) VALUES (?, GETDATE(), 0)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, maKh);

		int kq = cmd.executeUpdate();
		kn.cn.close();

		return kq;
	}

	public long getMaxHD() throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "select max(MaHoaDon) as MaxHD from hoadon";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);

		ResultSet rs = cmd.executeQuery();
		long max = 0;
		if (rs.next())
			max = rs.getLong("MaxHD");
		cmd.close();
		kn.cn.close();

		return max;
	}

	public long updateHDByKH(long makh) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "UPDATE hoadon\n"
		           + "SET damua = 1\n"
		           + "WHERE MaHoaDon IN (\n"
		           + "    SELECT cthd.MaHoaDon\n"
		           + "    FROM ChiTietHoaDon cthd\n"
		           + "    JOIN hoadon hd ON cthd.MaHoaDon = hd.MaHoaDon\n"
		           + "    WHERE hd.makh = ?\n"
		           + "    GROUP BY cthd.MaHoaDon\n"
		           + "    HAVING COUNT(*) = SUM(CASE WHEN cthd.DaThanhToan = 1 THEN 1 ELSE 0 END)\n"
		           + ")";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, makh);
		long kq = cmd.executeUpdate();
		kn.cn.close();
		return kq;
	}

	public int countAllHD() throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT COUNT(MaHoaDon) AS total FROM hoadon";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();

		int count = 0;
		if (rs.next()) {
			count = rs.getInt("total");
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return count;
	}

	public int countHDPaid() throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		String sql = "SELECT COUNT(*) AS total FROM hoadon WHERE damua = 1";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();

		int count = 0;
		if (rs.next()) {
			count = rs.getInt("total");
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return count;
	}

	public ArrayList<hoadon> getAllHDFromView(String searchValue, int page, int pageSize) throws Exception {
		ArrayList<hoadon> ds = new ArrayList<>();
		searchValue = "%" + searchValue + "%";

		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * FROM V_HoaDon " +
				"WHERE hoten LIKE ? " +
				"ORDER BY NgayMua DESC " +
				"OFFSET (? - 1) * ? ROWS " +
				"FETCH NEXT ? ROWS ONLY";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);

		cmd.setString(1, searchValue);
		cmd.setInt(2, page);
		cmd.setInt(3, pageSize);
		cmd.setInt(4, pageSize);

		ResultSet rs = cmd.executeQuery();

		while (rs.next()) {
			Long MaHoaDon = rs.getLong("MaHoaDon");
			Date NgayMua = rs.getDate("NgayMua");
			String hoten = rs.getString("hoten");
			Long TongSoLuong = rs.getLong("TongSoLuong");
			Long ThanhTien = rs.getLong("ThanhTien");
			
			ds.add(new hoadon(MaHoaDon, hoten, NgayMua, TongSoLuong, ThanhTien));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}

	public int countHDByCondition(String searchValue) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		searchValue = "%" + searchValue + "%";
		String sql = "SELECT COUNT(MaHoaDon) AS total FROM V_HoaDon WHERE hoten like ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, searchValue);
		ResultSet rs = cmd.executeQuery();

		int count = 0;
		if (rs.next()) {
			count = rs.getInt("total");
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return count;
	}

	public int deleteHD(long maHD) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();

//		String sqlDltCTHD = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon = ?";
//		PreparedStatement cmdDltCTHD = kn.cn.prepareStatement(sqlDltCTHD);
//		cmdDltCTHD.setLong(1, maHD);
//		cmdDltCTHD.executeUpdate();
//		cmdDltCTHD.close();

		String sqlDltHD = "DELETE FROM hoadon WHERE MaHoaDon = ?";
		PreparedStatement cmdDltHD = kn.cn.prepareStatement(sqlDltHD);
		cmdDltHD.setLong(1, maHD);
		int result = cmdDltHD.executeUpdate();

		cmdDltHD.close();
		kn.cn.close();

		return result;
	}

}
