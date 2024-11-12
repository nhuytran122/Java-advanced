package TranNhuYketnoimodal;

import java.sql.Connection;
import java.sql.DriverManager;

public class TranNhuY_K45G_KetNoi {
	public Connection cn;

    public void ketnoi() throws Exception {
        // Bước 1: Xác định HQTCSDL
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Bước 2: Kết nối vào CSDL
        String url = "jdbc:sqlserver://DESKTOP-6HUBDCV:1433;databaseName=TranNhuY;user=sa;password=nhuytran;encrypt=true;trustServerCertificate=true";
        cn = DriverManager.getConnection(url);
        System.out.println("Đã kết nối");
    }
}
