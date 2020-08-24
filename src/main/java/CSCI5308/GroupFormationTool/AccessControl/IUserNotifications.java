package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserNotifications
{
	void sendUserLoginCredentials(IUser user, String rawPassword);
}
