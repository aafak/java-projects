package test;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LdapAuthenticationExample  
{  
      
    public static void main(String[] args)  
    {         
        String username = "fooUser";  
        String password = "111222";  
        String base = "cn=Users,dc=mycompany,dc=com";  
        String dn = "cn=" + username + "," + base;  
        String ipAddress = "192.168.0.1";  
        String ldapURL = "ldap://" + ipAddress + ":389";  
          
        // Setup environment for authenticating  
        Hashtable<String, String> environment =   new Hashtable<String, String>();  
        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");  
        environment.put(Context.PROVIDER_URL, ldapURL);  
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");  
        environment.put(Context.SECURITY_PRINCIPAL, dn);  
        environment.put(Context.SECURITY_CREDENTIALS, password);  
  
        try  
        {  
            DirContext authContext = new InitialDirContext(environment);  
          
            if (getMultiSearchResult(authContext,   
                    "(&(objectCategory=person)(objectClass=user)(SAMAccountName=*))",  
                    "dc=mycompany,dc=com")) {  
                System.out.println("** FOUND ** ");  
            }else{  
                System.out.println("** NOT FOUND ** ");  
            }  
              
        }  
        catch (AuthenticationException ex)  
        {  
            ex.printStackTrace();  
        }  
        catch (NamingException ex)  
        {  
            ex.printStackTrace();  
        }  
    }  
      
    /** 
     * This method is used to search an entry in LDAP using Novell API 
     * @param dirContext 
     * @param searchFilter 
     * @param searchBase 
     * @return {@link Boolean} 
     * @throws NamingException 
     */  
    private static final boolean getMultiSearchResult(final DirContext dirContext,  
            final String searchFilter,  
            final String searchBase) throws NamingException{  
         
        boolean retVal = false;  
         
        final SearchControls constraints = new SearchControls();  
        constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);  
        final NamingEnumeration<?> searchResults = dirContext.search(searchBase,searchFilter,constraints);  
        int i = 0;  
        while(searchResults != null && searchResults.hasMoreElements()){  
            System.out.println((i++));  
            retVal= true;  
            final SearchResult searchResult = (SearchResult)searchResults.next();  
            displayAttribute("sAMAccountName", searchResult.getAttributes());  
            displayAttribute("memberOf", searchResult.getAttributes());  
        }  
  
        return retVal;  
    }  
  
    /** 
     * This method used to display the Attribute Values 
     * @param attributes 
     * @throws NamingException 
     */  
    @SuppressWarnings("unchecked")  
    private static void displayAttribute(String attrName, final Attributes attributes) throws NamingException{  
         
        if (attributes == null) {  
            System.out.println("*** No attributes ***");  
        }  
        else {  
            for (NamingEnumeration enums = attributes.getAll(); enums.hasMore();) {  
                final Attribute attribute = (Attribute)enums.next();  
                if(attribute.getID().equals(attrName)){  
                    for (NamingEnumeration namingEnu = attribute.getAll();namingEnu.hasMore();)  
                        System.out.println("\t        = " + namingEnu.next());  
                      
                    break;  
                }  
            }  
  
        }  
    }  
  
}  