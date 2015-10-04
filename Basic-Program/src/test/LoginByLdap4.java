package test;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LoginByLdap4 {

	public static void main(String[] args) {
		
		//Credentials of a user to authenticate on LDAP
		String username = "aafak@cbqa.com";
		String password = "behonest@CB32";
		
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
				    String searchBase = "CN=Users,DC=CBQA,DC=com";
					String queryFilter = "(& (dn="+bindDN+")(objectClass=user))";
					SearchControls sc = new SearchControls();
					String[] returningAttributes = {"dn","givenName","sn","mail","telephonenumber",
							"company","memberOf","distinguishedName","cn"};
					sc.setReturningAttributes(returningAttributes);
					sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
					sc.setCountLimit(1);
			      
					NamingEnumeration<SearchResult> answer = ctx.search(searchBase, queryFilter,  sc);
					SearchResult sr = (SearchResult)answer.next();
					String cn = sr.getName();
					System.out.println("CN(comman name) from LDAP: " + cn);

					answer.close();
					ctx.close();
					
					//if control comes here, means its a valid user on LDAP
					// now check the given password of user on LDAP server
					env = new Hashtable<String, String>(11);
					env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
					env.put(Context.PROVIDER_URL, protocol + url  + ":" + port);
					env.put(Context.SECURITY_PRINCIPAL, cn + "," + searchBase);
					env.put(Context.SECURITY_CREDENTIALS, password);
					
					// Create the initial context again to check user credentials
					ctx = new InitialDirContext(env);
					ctx.close();
					
					//if control comes here, means user authentication done successfully from LDAP
					//now get the user details
					Attributes attrs = sr.getAttributes();
					if(attrs != null){
						String firstName = attrs.get("givenName")!=null?attrs.get("givenName").get().toString():null;
						System.out.println("firstName: "+ firstName);
						String lastName = attrs.get("sn")!=null?attrs.get("sn").get().toString():null;
						System.out.println("lastName: "+ lastName);
						String mobileNo = attrs.get("telephonenumber")!=null?attrs.get("telephonenumber").get().toString():null;
						System.out.println("mobileNo: "+ mobileNo);
						String email = attrs.get("mail")!=null?attrs.get("mail").get().toString():null;
						System.out.println("email: "+ email);
						String orgnization = attrs.get("company")!=null?attrs.get("company").get().toString():null;
						System.out.println("orgnization: "+ orgnization);
						String memberOfString = null;
						if(attrs.get("memberOf") != null){
							memberOfString = attrs.get("memberOf").toString();//!=null?attrs.get("memberOf").get().toString():null;
						}
						System.out.println("memberOfString: "+ memberOfString);
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
