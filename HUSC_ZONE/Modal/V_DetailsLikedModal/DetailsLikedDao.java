package V_DetailsLikedModal;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CommonModal.KetNoi;

public class DetailsLikedDao {

    public ArrayList<DetailsLiked> getListLikesByUserID(int page, int pageSize, Long userID) throws Exception {
        ArrayList<DetailsLiked> ds = new ArrayList<DetailsLiked>();
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT * " +
                "FROM V_Details_Liked " +
                "WHERE LikedBy = ? " +
                "ORDER BY LikedAt DESC " +
                "OFFSET (? - 1) * ? ROWS " +
                "FETCH NEXT ? ROWS ONLY";

        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setLong(1, userID);
        cmd.setInt(2, page);
        cmd.setInt(3, pageSize);
        cmd.setInt(4, pageSize);

        ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
            ds.add(mapDetailsLiked(rs));
        }

        rs.close();
        cmd.close();
        kn.cn.close();

        return ds;
    }

    public int getCountLikesByUserID(Long userID) throws Exception {
        int count = 0;
        KetNoi kn = new KetNoi();
        kn.ketnoi();

        String sql = "SELECT COUNT(*) FROM V_Details_Liked WHERE LikedBy = ?";
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

    private DetailsLiked mapDetailsLiked(ResultSet rs) throws Exception {
        Long likeID = rs.getLong("LikeID");
        Long postID = rs.getLong("PostID");
        Date likedAt = rs.getDate("LikedAt");
        Long likedBy = rs.getLong("LikedBy");
        String postContent = rs.getString("PostContent");
        String imagePath = rs.getString("ImagePath");
        String posterName = rs.getString("PosterName");
        String avatar = rs.getString("PosterAvatar");
        Long posterID = rs.getLong("PosterID");

        return new DetailsLiked(likeID, postID, likedAt, likedBy, postContent,
                imagePath, posterName, avatar, posterID);
    }
}
