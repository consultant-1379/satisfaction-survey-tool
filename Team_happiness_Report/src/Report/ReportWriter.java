package Report;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBTablePrinter.TablePrinter;
import DBconnector.DataBaseConnection;


public class ReportWriter {
	
	public String init() 
	{
		DataBaseConnection con = new DataBaseConnection();
		ResultSet resultSetCount = con.execute(
				"select count(*) from team_happiness where team = 'Axis'and date >= DATE_ADD(NOW(), INTERVAL -7 DAY) " +
				"union all select count(*) from team_happiness where team = 'Stratus' and date >= DATE_ADD(NOW(), INTERVAL -7 DAY); ");
		int count =0;
		StringBuilder report = new StringBuilder();
		try
		{
			resultSetCount.beforeFirst();
			while (resultSetCount.next())
			{
				count ++;
				if(count == 1)
				{
					String AxisCount = "\nNumber of enteries from Axis: " + resultSetCount.getString(1)+ "\n";
					//System.out.println("Number of enteries from Axis: " + AxisCount);
					report.append(AxisCount);
				}
				else if(count == 2)
				{
					String StratusCount = "\nNumber of enteries from Stratus: " + resultSetCount.getString(1)+ "\n";
					//System.out.println("Number of enteries from Stratus: " + StratusCount + "\n\n");
					report.append(StratusCount +"\n\n");
				}
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		
		
		ResultSet resultSetTable = con.execute("select team,mission_comment,mastery_comment,organization_comment,empowered_comment from team_happiness where date >= DATE_ADD(NOW(), INTERVAL -7 DAY);");
		TablePrinter table = new TablePrinter("Team","Clear mission comments","Building mastery comments","Organization support comments","Feeling empowered comments");
		try
		{
			resultSetTable.beforeFirst();
			while (resultSetTable.next())
			{
				table.addRow(resultSetTable.getString(1),resultSetTable.getString(2),resultSetTable.getString(3),resultSetTable.getString(4),resultSetTable.getString(5));
			}
			report.append(table.print()) ;
			//System.out.println(report);
		}
		catch (SQLException e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		try {
			PrintWriter out = new PrintWriter("report.txt");
			out.println(report);
			out.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//triggers jenkins job that sends the report in an email
		URL url;
		try {
			String ReportEncoded = URLEncoder.encode(report.toString(), "UTF-8");
			String URLString = "https://fem101-eiffel004.lmera.ericsson.se:8443/jenkins/job/Team_Happiness_email/buildWithParameters?token=b176da29db2ac9ef5cdce893d5d8340e&report=" + ReportEncoded;
			//System.out.println(URLString);
			url = new URL(URLString);

			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.connect();
			int responseCode = connection.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return report.toString();
		
		
	
	}

}
