package spoondemo;

import java.sql.*;

public class DynamicCodeForJDBC {
	
	public static void main(String[] args) {
String empID ="SELECT * FROM EMPOYEE WHERE EMPLOYEE ID = '1002'";
}
	public static String empID ="SELECT * FROM EMPOYEE WHERE EMPLOYEE ID = '1001'";
	static final String DB_URL = "jdbc:postgres://localhost/TUTORIALSPOINT";
	static final String USER = "postgres";
	static final String PASS = "Dmap";

	static final String QUERY2 = "FROM Employees";
	static final String QUERY3 = "WHERE EMPLOYEE ID = '1001'";
	static final String QUERY1 = "SELECT";


	public void execute() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(QUERY1);
		sb.append(QUERY2);
		sb.append(QUERY3);
		String dynamic_query = sb.toString();
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(dynamic_query);) {
			while (rs.next()) {
				  System.out.print("ID: " + rs.getInt("id"));
		            System.out.print(", Age: " + rs.getInt("age"));
		            System.out.print(", First: " + rs.getString("first"));
		            System.out.println(", Last: " + rs.getString("last"));
		         }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

public static String getfunction() {
	String empID ="SELECT * FROM EMPOYEE WHERE EMPLOYEE ID = '1003'";
	return empID;
}
}