package Mission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public class JSONFileWriteMission 
{
	public void createJsonFile(ResultSet resultSet, String file) throws IOException
	{
		JSONObject obj = new JSONObject();
		
		int count = 0;
		try
		{
			resultSet.beforeFirst();
			while (resultSet.next())
			{
				count ++;
				if(count ==1)
				{
					float rs1 = (float)(Math.round(resultSet.getFloat(1)*2000/100.0));
					obj.put("current", rs1);
				}
				else if(count == 2)
				{
					float rs1 = (float)(Math.round(resultSet.getFloat(1)*2000/100.0));
					obj.put("last", rs1);;
				}
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		
		obj.put("auth_token", "YOUR_AUTH_TOKEN");
		
		File jsonFile = new File(file);
		jsonFile.createNewFile();
		FileWriter fileWriter = new FileWriter(jsonFile);
		fileWriter.write(obj.toJSONString());
		fileWriter.flush();
		fileWriter.close();
	}
}
