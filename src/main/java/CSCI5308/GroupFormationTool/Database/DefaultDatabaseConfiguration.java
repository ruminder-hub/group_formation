package CSCI5308.GroupFormationTool.Database;

public class DefaultDatabaseConfiguration implements IDatabaseConfiguration
{

	private static final String URL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_17_DEVINT?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER = "CSCI5308_17_DEVINT_USER";
	private static final String PASSWORD = "CSCI5308_17_DEVINT_17284";


	public String getDatabaseUserName()
	{
		return USER;
	}

	public String getDatabasePassword()
	{
		return PASSWORD;
	}

	public String getDatabaseURL()
	{
		return URL;
	}
}
