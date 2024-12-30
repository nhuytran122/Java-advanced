package V_DetailsReportModal;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.Constants;
import CommonModal.KetNoi;
import UserModal.User;

public class DetailsReportDao {
    public ArrayList<DetailsReport> getListReportsByUserID(int page, int pageSize, Long userID) throws Exception {
        ArrayList<DetailsReport> ds = new ArrayList<DetailsReport>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT * " +
                "FROM V_Details_Reports " +
                "WHERE CreatedBy = ? " +
                "ORDER BY CreatedAt DESC " +
                "OFFSET (? - 1) * ? ROWS " +
                "FETCH NEXT ? ROWS ONLY";

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, userID);
        cmd.setInt(2, page);
        cmd.setInt(3, pageSize);
        cmd.setInt(4, pageSize);

        ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
            ds.add(mapDetailsReport(rs));
        }

        rs.close();
        cmd.close();
        kn.cn.close();

        return ds;
    }

    public int getCountReportsByUserID(Long userID) throws Exception {
        int count = 0;
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT COUNT(*) FROM V_Details_Reports WHERE CreatedBy = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, userID);

        ResultSet rs = cmd.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }

        rs.close();
        cmd.close();
        kn.cn.close();

        return count;
    }
    
    public ArrayList<DetailsReport> getReportsByConditions(int page, int pageSize, String searchValue, Long statusID) throws Exception {
		ArrayList<DetailsReport> ds = new ArrayList<DetailsReport>();
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT * FROM V_Details_Reports " +
                "WHERE (PosterName LIKE ? OR PostContent LIKE ? OR Reason LIKE ?) " +
                "AND (? = 0 OR StatusID = ?) " +
                "ORDER BY CreatedAt DESC " +
                "OFFSET (? - 1) * ? ROWS " +  
                "FETCH NEXT ? ROWS ONLY";
		
	   searchValue = "%" + searchValue + "%";
	   PreparedStatement cmd = kn.cn.prepareStatement(sql);
	   cmd.setString(1, searchValue);
	   cmd.setString(2, searchValue);
	   cmd.setString(3, searchValue);
	   cmd.setLong(4, statusID);
	   cmd.setLong(5, statusID);
	   cmd.setInt(6, page);
	   cmd.setInt(7, pageSize);
	   cmd.setInt(8, pageSize);

		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(mapDetailsReport(rs));
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return ds;
	}
    
    public int getCountReportsByConditions(String searchValue, Long statusID) throws Exception {
		int count = 0;
		searchValue = "%" + searchValue + "%";
		KetNoi kn = new KetNoi();
		kn.ketnoi();

		String sql = "SELECT COUNT(*) " +
				"FROM V_Details_Reports " +
				"WHERE (PosterName LIKE ? OR PostContent LIKE ? OR Reason LIKE ?) " +
				"AND (? = 0 OR StatusID = ?)"; 

		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, searchValue);
		cmd.setString(2, searchValue);
		cmd.setString(3, searchValue);
		cmd.setLong(4, statusID);
		cmd.setLong(5, statusID);

		ResultSet rs = cmd.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}

		rs.close();
		cmd.close();
		kn.cn.close();

		return count;
	}
    
    public DetailsReport getReportByID(Long reportID) throws Exception {
        DetailsReport report = null;
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT * FROM V_Details_Reports WHERE ReportID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, reportID);
        ResultSet rs = cmd.executeQuery();

        if (rs.next()) {
        	report = mapDetailsReport(rs);
        }

        rs.close();
        cmd.close();
        kn.cn.close();
        return report;
    }

    private DetailsReport mapDetailsReport(ResultSet rs) throws Exception {
        Long reportID = rs.getLong("ReportID");
        String reason = rs.getString("Reason");
        Date createdAt = rs.getDate("CreatedAt");
        Long createdBy = rs.getLong("CreatedBy");
        Long postID = rs.getLong("PostID");
        Long statusID = rs.getLong("StatusID");
        String postContent = rs.getString("PostContent");
        String imagePath = rs.getString("ImagePath");
        String description = rs.getString("Description");
        String posterName = rs.getString("PosterName");
        String posterAvatar = rs.getString("PosterAvatar");
        Long posterID = rs.getLong("PosterID");
        Date solvedAt = rs.getDate("SolvedAt");

        return new DetailsReport(reportID, reason, createdAt, createdBy,
                postID, statusID, posterID, posterName, posterAvatar, postContent, imagePath, description, solvedAt);
    }
}
