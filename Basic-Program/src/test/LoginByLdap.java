package test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LoginByLdap {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String username = "cbqa\\Administrator1";
		String password = "Cloudbyte@1231";
		Hashtable<String, String> env = new Hashtable<String, String>(11);

		boolean b = false;

		/* env.put(Context.INITIAL_CONTEXT_FACTORY,
        "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid="+ username +",ou=system");
        env.put(Context.SECURITY_CREDENTIALS, password);*/
		System.out.println(username);
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://20.10.110.103:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		//env.put(Context.SECURITY_AUTHENTICATION, "DIGEST-MD5");
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);
		//env.put(Context.SECURITY_CREDENTIALS, MD5Utils.encrypt(password));

		try {
			// Create initial context
			DirContext ctx = new InitialDirContext(env);

			// Close the context when we're done
			b = true;
			ctx.close();

		} catch (NamingException e) {
			e.printStackTrace();
			b = false;
		}finally{
			if(b){
				System.out.print("Success");
			}else{
				System.out.print("Failure");
			}
		}

	}

}
