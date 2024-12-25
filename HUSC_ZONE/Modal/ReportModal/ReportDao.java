package ReportModal;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import CommonModal.Constants;
import CommonModal.KetNoi;

public class ReportDao {
    
	public Long addReport(Long postID, Long userID, String reason) throws Exception {
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();
	    String sql = "INSERT INTO tbl_Reports (PostID, Reason, CreatedAt, CreatedBy, StatusID) " +
	            "VALUES (?, ?, GETDATE(), ?, ?)";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	    cmd.setLong(1, postID);
	    cmd.setString(2, reason);
	    cmd.setLong(3, userID);
	    cmd.setLong(4, Constants.REPORT_PENDING);
	    cmd.executeUpdate();

	    ResultSet generatedKeys = cmd.getGeneratedKeys();
	    Long reportID = null;
	    if (generatedKeys.next()) {
	        reportID = generatedKeys.getLong(1);
	    }
	    generatedKeys.close();
	    cmd.close();
	    kn.cn.close();
	    return reportID;
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

    public Report getReport(Long reportID) throws Exception {

        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "SELECT * FROM tbl_Reports WHERE ReportID = ?";

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, reportID);
        ResultSet rs = cmd.executeQuery();

        if (rs.next()) {
            return mapReport(rs);
        }

        rs.close();
        cmd.close();
        kn.cn.close();

        return null;
    }
    
    private Report mapReport(ResultSet rs) throws Exception {
        Long ReportID = rs.getLong("ReportID");
        String reason = rs.getString("Reason");
        Date createdAt = rs.getDate("CreatedAt");
        Long createdBy = rs.getLong("CreatedBy");
        Long postID = rs.getLong("PostID");
        Long statusID = rs.getLong("StatusID");
        Date solvedAt = rs.getDate("SolvedAt");
        
        return new Report(ReportID, reason, createdAt, createdBy, postID, statusID, solvedAt);
    }
}
