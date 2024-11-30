package ReportModal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.Constants;
import CommonModal.KetNoi;

public class ReportDao {
	public ArrayList<Report> getListReports(Long userID) throws Exception {
	    ArrayList<Report> ds = new ArrayList<Report>();
	    
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "SELECT * FROM tbl_Reports WHERE CreatedBy = ?";
	    
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, userID); 
	    ResultSet rs = cmd.executeQuery();
	    while (rs.next()) {
	        Long ReportID = rs.getLong("ReportID");
	        String reason = rs.getString("Reason");
	        Date createdAt = rs.getDate("CreatedAt");
	        Long createdBy = rs.getLong("CreatedBy");
	        Long postID = rs.getLong("PostID");
	        Long statusID = rs.getLong("StatusID");
	        ds.add(new Report(ReportID, reason, createdAt, createdBy, postID, statusID));
	    }
	    
	    rs.close();
	    cmd.close();
	    kn.cn.close();
	    
	    return ds;
	}
	
    public int addReport(Long postID, Long userID, String reason) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "INSERT INTO tbl_Reports (PostID, Reason, CreatedAt, CreatedBy, StatusID) " +
                "VALUES (?, ?, GETDATE(), ?, ?)";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, postID);
        cmd.setString(2, reason);
        cmd.setLong(3, userID);
        cmd.setLong(4, Constants.REPORT_PENDING);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }

    public int deleteReport(Long ReportID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Reports WHERE ReportID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, ReportID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }
}
