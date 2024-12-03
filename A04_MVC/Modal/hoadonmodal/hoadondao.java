package hoadonmodal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			ds.add(mapHoadon(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}

	public ArrayList<hoadon> getListHDUnpaidFromView(String searchValue, int page, int pageSize) throws Exception {
		ArrayList<hoadon> ds = new ArrayList<>();
		searchValue = "%" + searchValue + "%";

		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * FROM V_HoaDon " +
				"WHERE hoten LIKE ? AND damua = 0 " +
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
			ds.add(mapHoadon(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}

	public int countAllHDByCondition(String searchValue) throws Exception {
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

	public int countListHDUnpaidByCondition(String searchValue) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();
		searchValue = "%" + searchValue + "%";
		String sql = "SELECT COUNT(MaHoaDon) AS total FROM V_HoaDon WHERE hoten like ? AND damua = 0";
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

		// String sqlDltCTHD = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon = ?";
		// PreparedStatement cmdDltCTHD = kn.cn.prepareStatement(sqlDltCTHD);
		// cmdDltCTHD.setLong(1, maHD);
		// cmdDltCTHD.executeUpdate();
		// cmdDltCTHD.close();

		String sqlDltHD = "DELETE FROM hoadon WHERE MaHoaDon = ?";
		PreparedStatement cmdDltHD = kn.cn.prepareStatement(sqlDltHD);
		cmdDltHD.setLong(1, maHD);
		int result = cmdDltHD.executeUpdate();

		cmdDltHD.close();
		kn.cn.close();

		return result;
	}

	public int payHD(long maHD) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		int kq = 0;

		String sqlCTHD = "UPDATE ChiTietHoaDon SET DaThanhToan = 1 WHERE MaHoaDon = ?";
		PreparedStatement cmdCTHD = kn.cn.prepareStatement(sqlCTHD);
		cmdCTHD.setLong(1, maHD);
		kq += cmdCTHD.executeUpdate();

		String sqlHD = "UPDATE hoadon SET damua = 1 WHERE MaHoaDon = ?";
		PreparedStatement cmdHD = kn.cn.prepareStatement(sqlHD);
		cmdHD.setLong(1, maHD);
		kq += cmdHD.executeUpdate();

		return kq;
	}

	public hoadon getHDByMaHD(Long maHD) throws Exception {
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * FROM V_HoaDon WHERE MaHoaDon = ?";

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, maHD);

		ResultSet rs = cmd.executeQuery();
		if (rs.next()) {
			return mapHoadon(rs);
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return null;
	}

	private hoadon mapHoadon(ResultSet rs) throws SQLException {
		Long MaHoaDon = rs.getLong("MaHoaDon");
		Date NgayMua = rs.getDate("NgayMua");
		String hoten = rs.getString("hoten");
		Long TongSoLuong = rs.getLong("TongSoLuong");
		Long ThanhTien = rs.getLong("ThanhTien");
		Boolean damua = rs.getBoolean("damua");
		return new hoadon(MaHoaDon, hoten, NgayMua, TongSoLuong, ThanhTien, damua);
	}

}
