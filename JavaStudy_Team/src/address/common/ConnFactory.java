package address.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// 1,2 단계를 거쳐 커넥션객체를 만들어 주는 공장
public class ConnFactory {
	
	public static Connection getConnection(String dbConfig) {
		
		ResourceBundle bundle = ResourceBundle.getBundle(dbConfig);
		
		// 1단계
		try {
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("1단계 오류 발생");
			return null;
		}
		
		// 2단계
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("2단계 오류 발생");
			return null;
		}
		return conn;
	}
	
	
	// 4단계
	public static void close(Connection conn) throws SQLException {
			if(conn!=null & conn.isClosed()) {
				conn.close();
			}

	}
}
