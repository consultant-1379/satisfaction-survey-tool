package Operator;

public class Executer 
{
	public String execute(String command)
	{
		StringBuffer output = new StringBuffer();
		Process p;
		
		try
		{
			p = Runtime.getRuntime().exec(new String[] { "/bin/sh"//$NON-NLS-1$
					, "-c", command });
			p.waitFor();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return output.toString();
	}
	
	public String createCommand(String url, String file)
	{
		String command = "curl " + url + " -H \"Content-type:application/json\"" + " -X POST -d @" + file;
		return command;
	}
}
