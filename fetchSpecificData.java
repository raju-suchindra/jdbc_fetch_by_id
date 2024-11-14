package first_jdbc_pkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import first_jdbc_pgm.MyCon;

public class fetchSpecificData {
	private static String sql = "Select * from students where studentId=?";
	static Connection con;
	static PreparedStatement pstmt;
	static ResultSet resultSet;	
	
	public static void main(String[] args) 
	{
		try {
			System.out.println("Enter the studentId : ");
			int studentId = new Scanner(System.in).nextInt();

			// 1. Load the Driver   2.establish the driver
			con = MyCon.connect();

			// 3.complete query -> Create sql query
			//   Incomplete query -> prepare sql query
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, studentId);
			// 4. Executing query
			resultSet = pstmt.executeQuery();

			// 5. Processing the data
			if(resultSet.next()) {
				System.out.println(resultSet.getInt("studentid") + "   " +
						resultSet.getString("studentName") + "  " +
						resultSet.getInt("studentMobile") + " " +
						resultSet.getString("studentEmail") + " " + 
						resultSet.getString("CourseName"));
			}
			else {
				System.out.println("No data found in database");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
