package CSCI5308.GroupFormationTool.AccessControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser
{
	private static CurrentUser uniqueInstance = null;
	private Logger log = LoggerFactory.getLogger(CurrentUser.class);
	
	public static CurrentUser instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new CurrentUser();
		}
		return uniqueInstance;
	}
	
	public IUser getCurrentAuthenticatedUser()
	{
		log.debug("Getting Current Authenticated User");
		IUserPersistence userDB = AccessControlAbstractFactory.instance().getUserDB();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.isAuthenticated())
		{
			String bannerID = authentication.getPrincipal().toString();
			IUser user = AccessControlAbstractFactory.instance().getUser();
			userDB.loadUserByBannerID(bannerID, user);
			if (user.isValidUser())
			{
				return user;
			}
		}
		return null;
	}
}
