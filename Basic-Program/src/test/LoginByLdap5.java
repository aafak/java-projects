package test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

//verifying serach base
public class LoginByLdap5 {

	public static boolean isValidSearchBase(DirContext ctx, String searchBase) {
		boolean validSearchBase = false;
		try{
			if(ctx != null && searchBase != null) {
				String queryFilter = "(objectClass=organizationalPerson)";
				SearchControls sc = new SearchControls();
				String[] returningAttributes = {"cn"};
				sc.setReturningAttributes(returningAttributes);
				sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
				sc.setCountLimit(1);

				NamingEnumeration<SearchResult> answer = ctx.search(searchBase, queryFilter,  sc);
				SearchResult sr = (SearchResult)answer.next();
				String cn = sr.getName();
				System.out.println("CN(comman name) from LDAP: " + cn);
				answer.close();
				ctx.close();
			}
			validSearchBase =true;

		} catch (NamingException ex){
			ex.printStackTrace();
			System.out.println("Authentication failed , error is:"+ ex.getMessage());
		} 
		catch (Exception ex){
			ex.printStackTrace();
			System.out.println("Authentication failed, due to unknown error encountered, error is:"+ ex.getMessage());
		}
		return validSearchBase;
		
	}
	
	public static void main(String[] args) {
	
		//LDAP server details
		String protocol = "ldap://" ;
		String url="20.10.110.103";
        String port = "389";
        String useSSL = "false";
        
        //Credentials of admin who has permission to search all the users on the define search base
        String bindDN = "cbqa\\Administrator";//username who can search all user
        String bindPasswd ="cloudbyte@123";
        String trustStore = "";
        String trustStorePassword = "";

				try {
					// get all params
					Hashtable<String, String> env = new Hashtable<String, String>(11);
					env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
					env.put(Context.PROVIDER_URL, protocol + url  + ":" + port);
					if (bindDN != null && bindPasswd != null){
						env.put(Context.SECURITY_PRINCIPAL, bindDN);
						env.put(Context.SECURITY_CREDENTIALS, bindPasswd);
					}
					else {
						// Use anonymous authentication
						env.put(Context.SECURITY_AUTHENTICATION, "none");
					}
					if (new Boolean(useSSL)){
						env.put(Context.SECURITY_PROTOCOL, "ssl");
						protocol="ldaps://" ;
						System.setProperty("javax.net.ssl.trustStore", trustStore);
						System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
					}
					
					// Create the initial context, 
					DirContext ctx = new InitialDirContext(env);
					
					//if control comes here,it means the admin has rights to authenticate the user
					// use this context to search the user
				    String searchBase = "CN=Users,DC=CBQA, DC=com";
					if(isValidSearchBase(ctx, searchBase)){
						System.out.println("SearchBase is valid");
					} else{
						System.out.println("Invalid search base ");

					}
					
				} catch (NamingException ne) {
					ne.printStackTrace();
					System.out.println("Authentication failed on LDAP server: " +url+", error is:"+ ne.getMessage());
				}
				catch (Exception e){
					e.printStackTrace();
					System.out.println("Authentication failed on LDAP server: " +url+", due to unknown error encountered, error is:"+ e.getMessage());
				}
			}

}
/*
 * OUTPUT
 * CN(comman name) from LDAP: CN=aafak khan
firstName: aafak
lastName: khan
mobileNo: 7676532370
email: aafak.mohammad@cloudbyte.com
orgnization: CloudByte
memberOfString: memberOf: CN=CB_VIEW_ADMIN,CN=Users,DC=cbqa,DC=com, CN=CB_HA_ADMIN,CN=Users,DC=cbqa,DC=com, CN=CB_SITE_ADMIN,CN=Users,DC=cbqa,DC=com, CN=cb_blr,CN=Users,DC=cbqa,DC=com

 */
