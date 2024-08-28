package DBconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseConnection 
{
	public ResultSet execute(String command)
	{
		Connection con = null;
		Statement stmt;
		ResultSet rs = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TABLENAME", "USERNAME", "PASSWORD");
			stmt = con.createStatement();
			rs = stmt.executeQuery(command);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return rs;
	}
}
