package com.advancesd.group17.auth.dao;

import com.advancesd.group17.users.model.User;

public interface AuthDao {

	public boolean loginAuthentication(User u);
	
	public boolean isAlreadyUser(User u);
	
    public boolean registerUser(User u);
	
    public boolean checkUserByEmail(User u);
    
    public User getUserCred(String email);
}