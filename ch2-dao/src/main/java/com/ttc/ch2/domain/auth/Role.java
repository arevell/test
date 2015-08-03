package com.ttc.ch2.domain.auth;


public enum Role 
{
	BRAND("ROLE_BRAND"),ADMINISTRATOR("ROLE_ADMIN"),CCAPI("ROLE_CCAPI"),UPLOAD("ROLE_UPLOAD");

private String name;

private Role(String name)
{
	this.name=name;
}

public String getName() {
	return name;
}


	public static Role getRoleByString(String name)
	{
		for (int i = 0; i < values().length; i++) {
			if(values()[i].getName().equals(name))
				return values()[i];
		}		
		throw new IllegalArgumentException("Incorrect role name:"+name);
	}

} 