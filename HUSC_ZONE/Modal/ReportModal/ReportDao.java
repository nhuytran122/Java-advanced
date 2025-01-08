package ReportModal;

import java.util.ArrayList;
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
    
    public int getCountPendingReports() throws Exception {
        int count = 0;
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT COUNT(*) FROM tbl_Reports WHERE StatusID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, Constants.REPORT_PENDING);

        ResultSet rs = cmd.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }

        rs.close();
        cmd.close();
        kn.cn.close();

        return count;
    }
    
	public int updateReport(Long reportID, Long statusID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "UPDATE tbl_Reports SET StatusID = ?, SolvedAt = GETDATE() "
        		+ "WHERE ReportID = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);

        cmd.setLong(1, statusID);
        cmd.setLong(2, reportID);
        int kq = cmd.executeUpdate();
        kn.cn.close();
        return kq;
    }
	
	public int deleteReportsByUserID(Long userID) throws Exception {
        KetNoi kn = new KetNoi();
        kn.ketnoi();
        String sql = "DELETE FROM tbl_Reports WHERE CreatedBy = ?";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, userID);
        int result = cmd.executeUpdate();
        cmd.close();
        kn.cn.close();
        return result;
    }
	
	public ArrayList<Long> getReportIDsByUserID(Long userID) throws Exception {
	    ArrayList<Long> reportIDs = new ArrayList<>();
	    KetNoi kn = new KetNoi();
	    kn.ketnoi();

	    String sql = "SELECT ReportID FROM tbl_Reports WHERE CreatedBy = ?";
	    PreparedStatement ps = kn.cn.prepareStatement(sql);
	    ps.setLong(1, userID);
	    ResultSet rs = ps.executeQuery();

	    while (rs.next()) {
	        reportIDs.add(rs.getLong("ReportID"));
	    }

	    rs.close();
	    ps.close();
	    kn.cn.close();

	    return reportIDs;
	}

}
