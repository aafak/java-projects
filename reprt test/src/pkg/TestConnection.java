package pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   Connection cn=null;
		    String HOST = "jdbc:mysql://10.20.63.22:3306/cloud";
	        String USERNAME = "root";
	        String PASSWORD = "test";
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        }
	 
	        try {
	            cn = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
	            Statement smt=cn.createStatement();
	            ResultSet rs=smt.executeQuery("select * from cb_site");
	            if(rs.next())
	            {
	            	System.out.println("site id:"+rs.getInt(1));
	            	
	            }
	        
	        
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	}

}
