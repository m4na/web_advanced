package com.web_advanced.model;

public class User {

	public static void connexion(LDAPaccess LDAP_access, String id, String pwd) {
		LDAP_access = new LDAPaccess();
		try {
			LDAPObject obj = LDAP_access.LDAPget(id, pwd);
			System.out.println(obj);
		} catch (Exception e) {
			System.out.println("fail !");
		}
	}
}
