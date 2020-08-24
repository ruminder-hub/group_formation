package CSCI5308.GroupFormationTool.Security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import CSCI5308.GroupFormationTool.AccessControl.*;

public class CustomAuthenticationManager implements AuthenticationManager
{
	private static final String ADMIN_BANNER_ID = "B-000000";
	private Logger log = LoggerFactory.getLogger(CustomAuthenticationManager.class);
	
	private Authentication checkAdmin(String password, IUser u, Authentication authentication) throws AuthenticationException
	{
		if (password.equals(u.getPassword()))
		{
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(new SimpleGrantedAuthority("ADMIN"));
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), rights);
			return token;
		}
		else
		{
			log.error("Bad credentials entered");
			throw new BadCredentialsException("1000");
		}
	}
	
	private Authentication checkNormal(String password, IUser u, Authentication authentication) throws AuthenticationException
	{
		IPasswordEncryption passwordEncryption = SecurityAbstractFactory.instance().getPasswordEncryption();
		if (passwordEncryption.matches(password, u.getPassword()))
		{
			List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
			rights.add(new SimpleGrantedAuthority("USER"));
			UsernamePasswordAuthenticationToken token;
			token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), rights);
			return token;
		}
		else
		{
			log.error("Bad credentials entered");
			throw new BadCredentialsException("1000");
		}
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String bannerID = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		log.debug("Authentication user with bannerId: " + bannerID);
		IUserPersistence userDB = AccessControlAbstractFactory.instance().getUserDB();
		IUser user;
		try
		{
			user = new User(bannerID, userDB);
		}
		catch (Exception e)
		{
			log.error("Failed while authenticating User due to " + e.getMessage());
			throw new AuthenticationServiceException("1000");
		}
		if (user.isValidUser())
		{
			if (bannerID.toUpperCase().equals(ADMIN_BANNER_ID))
			{
				return checkAdmin(password, user, authentication);
			}
			else
			{
				return checkNormal(password, user, authentication);
			}
		}
		else
		{
			log.error("Bad credentials entered");
			throw new BadCredentialsException("1001");
		}			
	}
}
