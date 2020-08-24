package CSCI5308.GroupFormationTool.AccessControl;

public interface IAccessControlAbstractFactory
{
    public IUserPersistence getUserDB();

    public IUserNotifications getUserNotifications();

    public IUpdatePassword getUpdatePassword();

    public IUser getUser();
}
