package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectJDBC {
	public static Connection getConnection() {
		Connection conn = null ;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://ADMIN\\SQLEXPRESS:1433;databaseName=DACS1;encrypt=true;trustServerCertificate=true",
					"sa", "123456789");
			System.out.println("Kết nối thành công!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn) {

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	public static void main(String[] args) {
		System.out.println("kết nối thành công");
	}

}
