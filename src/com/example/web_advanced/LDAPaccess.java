package com.example.web_advanced;

/**
 * accès à l'annuaire LDAP de l'ISEP
 * pour :
 * authentification des utilisateurs
 * retrouver le mail ISEP d'un utilisateur
 */

import isep.menu.Libelles;

import java.io.Serializable;
import java.util.Hashtable;

import javax.naming.*;
import javax.naming.directory.*;

public class LDAPaccess implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * authentification
	 * 
	 * @param user
	 * @param mdp
	 * @return l'objet LDAP ou null si pas trouvé
	 * @throws Exception
	 */

	public LDAPObject LDAPget(String user, String mdp) throws Exception {

		// Initial context implementation
		String INITCTX = "com.sun.jndi.ldap.LdapCtxFactory";
		String MY_HOST = Libelles.getTexte("LDAP_SERVER");
		String MGR_DN = "uid=" + user + ", " + Libelles.getTexte("LDAP_ROOT");
		String MGR_PW = mdp;
		String MY_SEARCHBASE = Libelles.getTexte("LDAP_SEARCH_BASE");
		String MY_FILTER = "(uid=" + user + ")";

		String login = null;
		String nom = null;
		String type = null;
		String employeeNumber = null;
		String mail = null;
		String messageErreur = Libelles.getTexte("LOGIN_INVALIDE");
		try {

			// Hashtable for environmental information
			Hashtable<String, String> env = new Hashtable<String, String>();

			// Specify which class to use for our JNDI provider
			env.put(Context.INITIAL_CONTEXT_FACTORY, INITCTX);

			// Specify host and port to use for directory service
			env.put(Context.PROVIDER_URL, MY_HOST);
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, MGR_DN);
			env.put(Context.SECURITY_CREDENTIALS, MGR_PW);

			// Get a reference to a directory context
			DirContext ctx = new InitialDirContext(env);

			// Specify the scope of the search
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

			// Perform the actual search
			// We give it a searchbase, a filter and a the constraints
			// containing the scope of the search
			NamingEnumeration<SearchResult> results = ctx.search(MY_SEARCHBASE,
					MY_FILTER, constraints);

			// Now step through the search results
			while (results != null && results.hasMore()) {
				SearchResult sr = (SearchResult) results.next();

				String dn = sr.getName();

				Attribute cn = sr.getAttributes().get("cn");
				nom = (String) cn.get();
				Attribute uid = sr.getAttributes().get("uid");
				login = (String) uid.get();
				Attribute et = sr.getAttributes().get("employeeType");
				type = (String) et.get();

				try {
					Attribute en = sr.getAttributes().get("employeeNumber");
					employeeNumber = (String) en.get();
				} catch (Exception e) {
					messageErreur = "numéro d'élève non trouvé dans l'annuaire";
				}
				Attribute em = sr.getAttributes().get("mail");
				mail = (String) em.get();

				// Attributes attrs = sr.getAttributes();

			}
		} catch (Exception e) {
			System.err.println(e);
			throw (new Exception(messageErreur));
		}

		LDAPObject reponse = new LDAPObject(login, mdp, nom, type,
				employeeNumber, mail);
		return reponse;
	}

	/**
	 * récupération des informations d'un utilisateur
	 * 
	 * @param user
	 * @return objet LDAP ou null
	 * @throws Exception
	 */
	public LDAPObject LDAPget(String numero) throws Exception {

		// Initial context implementation
		String INITCTX = "com.sun.jndi.ldap.LdapCtxFactory";
		String MY_HOST = Libelles.getTexte("LDAP_SERVER");
		String MGR_DN = "uid=" + Libelles.getTexte("LDAP_UID") + ", "
				+ Libelles.getTexte("LDAP_ROOT");
		String MGR_PW = Libelles.getTexte("LDAP_PWD");
		String MY_SEARCHBASE = Libelles.getTexte("LDAP_SEARCH_BASE");
		String MY_FILTER = "(employeeNumber=" + numero + ")";

		String login = null;
		String nom = null;
		String type = null;
		String employeeNumber = null;
		String mail = null;
		String messageErreur = Libelles.getTexte("LOGIN_INVALIDE");
		try {

			// Hashtable for environmental information
			Hashtable<String, String> env = new Hashtable<String, String>();

			// Specify which class to use for our JNDI provider
			env.put(Context.INITIAL_CONTEXT_FACTORY, INITCTX);

			// Specify host and port to use for directory service
			env.put(Context.PROVIDER_URL, MY_HOST);
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, MGR_DN);
			env.put(Context.SECURITY_CREDENTIALS, MGR_PW);

			// Get a reference to a directory context
			DirContext ctx = new InitialDirContext(env);

			// Specify the scope of the search
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

			// Perform the actual search
			// We give it a searchbase, a filter and a the constraints
			// containing the scope of the search
			NamingEnumeration<SearchResult> results = ctx.search(MY_SEARCHBASE,
					MY_FILTER, constraints);

			// Now step through the search results
			while (results != null && results.hasMore()) {
				SearchResult sr = (SearchResult) results.next();

				String dn = sr.getName();
				System.out.println("Distinguished Name is " + dn);

				Attribute cn = sr.getAttributes().get("cn");
				nom = (String) cn.get();
				Attribute uid = sr.getAttributes().get("uid");
				login = (String) uid.get();
				Attribute et = sr.getAttributes().get("employeeType");
				type = (String) et.get();

				try {
					Attribute en = sr.getAttributes().get("employeeNumber");
					employeeNumber = (String) en.get();
				} catch (Exception e) {
					messageErreur = "numéro d'élève non trouvé dans l'annuaire";
				}
				Attribute em = sr.getAttributes().get("mail");
				mail = (String) em.get();

				// Attributes attrs = sr.getAttributes();

			}
		} catch (Exception e) {
			System.err.println(e);
			throw (new Exception(messageErreur));
		}

		LDAPObject reponse = new LDAPObject(login, "", nom, type,
				employeeNumber, mail);
		return reponse;
	}
}