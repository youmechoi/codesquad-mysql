package youmemysql;

import java.sql.*;

public class DbTest {
	public static void main(String[] args) {
		System.out.println("MySQL JDBC TEST");
		DbTest db = new DbTest();
		db.test();
	}

	// java db connect

	public void test() {
		Connection conn;
		Statement stmt;
		ResultSet rs;
		String sql;

		// url + db name
		String addr = "jdbc:mysql://13.124.68.163/youmedb";
		String user = "youme";
		String pw = "1222";
		System.out.println("Hello DB");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("No Driver");
			System.err.println(e.getMessage());
			return;
		}

		System.out.println("Success to load JDBC Driver");
		
		try {
			conn = DriverManager.getConnection(addr, user, pw);
			System.out.println("Success to Connect DB");
			stmt = conn.createStatement();
			
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO USER(NAME, START_DATE) VALUES(?,?)");
			String name = "javajigi";
			String date = "1970-5-31";
			pstmt.setString(1, name);
			pstmt.setString(2, date);
			
			pstmt.execute();
			
			sql = "SELECT UID, NAME, START_DATE FROM USER";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int n = rs.getInt(1);
				name = rs.getString("NAME");
				date = rs.getString(3);
				
				System.out.printf("%d %s %s\n", n, name, date);
			}
			
			conn.close();
		} catch (SQLException e) {
			System.err.println("SQL Fail");
			e.printStackTrace();
		}
	}
}

//		try {
//			conn = DriverManager.getConnection(addr, user, pw);
//			System.out.println("Success to connect");
//			stmt = conn.createStatement();
//			
//			PreparedStatement pstmt = conn.prepareStatement(
//					"INSERT INTO USER(NAME, START_DATE) VALUES(?,?)");
//			
//			while (rs.next()) { // result set 에 record 가 있다
//				int n = rs.getInt("UID");
//				String name = rs.getString("NAME");
//				String date = rs.getString("START_DATE");
//				
//				String name = "javajigi";
//				String date = "1970-5-31";
//				pstmt.setString(1,  name);
//				pstmt.setString(2, date);
//				
//				pstmt.execute();
//
//				System.out.printf("%d %s %s \n", n, name, date);
//				
//				sql = "SELECT UID, NAME, START_DATE FROM USER";
//				rs = stmt.executeQuery(sql);
//			
//			}
//
//			conn.close();
//		} catch (SQLException e) {
//			System.err.println("SQL Fail");
//			e.printStackTrace();
//		}
//
//	}
//
//}
