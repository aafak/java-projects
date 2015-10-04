package test;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LoginByLdap2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username = "cbqa\\Administrator";
		String password = "Cloudbyte@123";
		Hashtable<String, String> env = new Hashtable<String, String>(11);
		boolean b = false;

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://20.10.110.103:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			// Create initial context
			DirContext ctx = new InitialDirContext(env);

			SearchControls searchCtrls = new SearchControls();
			searchCtrls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			String[] attributes = { "sAMAccountName","dn","distinguishedName","cn","name","uid",
					"sn",        	//sn means sir name
					"givenname",
					"mail",
					"telephonenumber","memberOf","userPrincipalName"};

			searchCtrls.setReturningAttributes(attributes);


			//*****************Get all the users of Active directory for a Particular domain(CBQA)
			String filter = "(objectClass=organizationalPerson)";
			NamingEnumeration values = ctx.search("CN=Users,DC=CBQA, DC=com", filter, searchCtrls);

			while (values.hasMoreElements())
			{
				SearchResult result = (SearchResult) values.next();
				Attributes attribs = result.getAttributes();

				if (null != attribs)
				{
					for (NamingEnumeration ae = attribs.getAll(); ae.hasMoreElements();)
					{
						Attribute atr = (Attribute) ae.next();
						String attributeID = atr.getID();
						for (
								Enumeration vals = atr.getAll(); 
								vals.hasMoreElements(); 
								System.out.println(attributeID +": "+ vals.nextElement())
								);
					}
				}
			}


			/* OUTPUT:
			 * telephoneNumber: 7676532370
			mail: aafak.mohammad@cloudbyte.com
			name: Aafak Mohammad
			givenName: Aafak
			userPrincipalName: aafak@cbqa.com
			sn: Mohammad
			distinguishedName: CN=Aafak Mohammad,CN=Users,DC=cbqa,DC=com
			cn: Aafak Mohammad
			 */
			/////////////////// verify a particuler user(aafak@cbqa.com)//////////////////////

			//First input parameter is search bas, it can be "CN=Users,DC=YourDomain,DC=com"
			//Second Attribute can be uid=username
			String principalName = "aafak@cbqa.com";
			filter = "(& (userPrincipalName="+principalName+")(objectClass=user))";
			//filter = "(objectClass=organizationalPerson)";
			NamingEnumeration answer = ctx.search("CN=Users,DC=CBQA,DC=com", filter, searchCtrls);
			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				System.out.println(".......sAMAccountName "+ attrs.get("sAMAccountName"));
				System.out.println(".......dn "+ attrs.get("dn"));

				System.out.println(".......distinguishedName "+ attrs.get("distinguishedName"));
				System.out.println(".......name "+ attrs.get("name"));
				System.out.println(".....givenname "+ attrs.get("givenname"));
				System.out.println("........sn "+ attrs.get("sn"));
				System.out.println(".......mail "+ attrs.get("mail"));
				System.out.println(".........telephonenumber "+ attrs.get("telephonenumber"));
			}else{
				throw new Exception(".........Invalid User");
			}

			//////////////////////////
			// Close the context when we're done
			b = true;
			ctx.close();

		} catch (NamingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			b = false;
		} catch (Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

		finally{
			if(b){
				System.out.print("Success");
			}else{
				System.out.print("Failure");
			}
		}

	}

}
