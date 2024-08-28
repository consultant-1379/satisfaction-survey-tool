package Organization;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Operator.DataBaseConnection;
import Operator.Executer;

public class OrganizationUpdate 
{
	public void init(String sqlStatement, String widgetUrl, String JSONfile)
	{
		DataBaseConnection con = new DataBaseConnection();
		ResultSet rs = con.execute(sqlStatement);
		
		JSONFileWriteOrganization jsonFileWrite = new JSONFileWriteOrganization();
		
		try
		{
			//Creates JSON file with result set
			jsonFileWrite.createJsonFile(rs, JSONfile);
		}
		catch (IOException e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				System.out.println(e);
			}
		}
		
		//creates and executes the curl command
		Executer executer = new Executer();
		String command = executer.createCommand(widgetUrl, JSONfile);
		System.out.println(command);
		executer.execute(command);
	}
}
