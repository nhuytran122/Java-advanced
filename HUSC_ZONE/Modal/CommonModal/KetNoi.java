package CommonModal;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetNoi {
	public Connection cn;

    public void ketnoi() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Bước 2: Kết nối vào CSDL
//        String url = "jdbc:sqlserver://LAB403-01:1433;databaseName=QlSach;user=sa;password=123;encrypt=true;trustServerCertificate=true";
        String url = "jdbc:sqlserver://DESKTOP-6HUBDCV:1433;databaseName=QL_HUSCZone;user=sa;password=nhuytran;encrypt=true;trustServerCertificate=true";
        cn = DriverManager.getConnection(url);
    }
}