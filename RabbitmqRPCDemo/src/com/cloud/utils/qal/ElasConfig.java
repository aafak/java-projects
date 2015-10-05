package com.cloud.utils.qal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.UUID;



public class ElasConfig {
	
	 static String insert;
	 static String uuid;
	 static Properties prop = new Properties();
	 static final File dbPropsFile = PropertiesUtil.findConfigFile("db.properties");
	 static FileInputStream in = null;
	 static String USER = null;
	 static String PASS = null;
	 static String HOST = null;
	 static String DB   = null;
	
	 static Connection conn = null;
	 static Statement  stmt = null;
	 
	
	public static void setConfig(String s){
		try{
				in = new FileInputStream(dbPropsFile);
				prop.load(in);
				USER = prop.getProperty("db.cloud.username");
				PASS = prop.getProperty("db.cloud.password");
				HOST = prop.getProperty("db.cloud.host");
				DB   = prop.getProperty("db.cloud.name");
				String JDBC_DRIVER = "com.mysql.jdbc.Driver";
				String DB_URL = "jdbc:mysql://"+HOST+"/"+DB;
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
				stmt = conn.createStatement();
				InetAddress devmanIp = InetAddress.getLocalHost();
				String ipAddress = devmanIp.getHostAddress();
				String uid = UUID.nameUUIDFromBytes(ipAddress.getBytes()).toString();
				String ser_uid = UUID.randomUUID().toString();
				NetworkInterface networkInterface = NetworkInterface.getByInetAddress(devmanIp);
				int size = networkInterface.getInterfaceAddresses().size();
				InterfaceAddress address;
				if (size == 2 ){
				address = networkInterface.getInterfaceAddresses().get(1);
				}
				else{
				address = networkInterface.getInterfaceAddresses().get(0);
				}
				int netNum = address.getNetworkPrefixLength();
				long bits;
				bits = 0xffffffff ^ (1 << 32 - netNum) - 1;
				String netmask = String.format("%d.%d.%d.%d", (bits & 0x0000000000ff000000L) >> 24, (bits & 0x0000000000ff0000) >> 16, (bits & 0x0000000000ff00) >> 8, bits & 0xff);
				FileInputStream input = null;
				String hostUuid = null;
			    //hostUuid=HostUtils.getHostUuId();
			    hostUuid=hostUuid.trim();
				try{
					input = new FileInputStream("/etc/rc.conf");
					prop.load(input);
					String gateway = prop.getProperty("defaultrouter");
					gateway=gateway.replace("\"","");
					String hostname=prop.getProperty("hostname");
					hostname=hostname.replace("\"","");
					if(s.equals("server")){
						insert="Insert into cb_elasticenter(ipaddr,uuid,service_uuid,netmask,gateway,hostname,ha_state,status)" + "values ('" +ipAddress+ "' , '" +hostUuid+ "','" +ser_uid+ "', '" +netmask+ "','" +gateway+ "','"+hostname+"','"+"primary"+"','"+"online"+"')";
						System.setProperty("ec.service.uuid", ser_uid);
					}
					else if(s.equals("utils")) {
						insert="Insert into cb_elasticenter(ipaddr,uuid,service_uuid,netmask,gateway,hostname,ha_state,status)" + "values ('" +ipAddress+ "' , '" +hostUuid+ "','" +uid+ "', '" +netmask+ "','" +gateway+ "','"+hostname+"','"+"primary"+"','"+"online"+"')"; 
						System.setProperty("ec.service.uuid", uid);
					}
					stmt.executeUpdate(insert);
					stmt.close();
					conn.close();
					
				}
					catch(Exception e){
						e.printStackTrace();
						System.exit(1);
					}
					finally{
						if (input != null) {
							try {
								input.close();
							}
							catch(IOException e) {
								e.printStackTrace();
							}
						}
					}
		}
		catch(SQLException se){
				se.printStackTrace();
				System.exit(1);
		}
		catch(Exception e){
				e.printStackTrace();
				System.exit(1);
		}
		
	}
	
	public static void setServiceUUID(){
		try{
			in = new FileInputStream(dbPropsFile);
			prop.load(in);
			USER = prop.getProperty("db.cloud.username");
			PASS = prop.getProperty("db.cloud.password");
			HOST = prop.getProperty("db.cloud.host");
			DB   = prop.getProperty("db.cloud.name");
			String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			String DB_URL = "jdbc:mysql://"+HOST+"/"+DB;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String test;
			test="SELECT service_uuid from cb_elasticenter where removed is null limit 1";
			ResultSet r1=stmt.executeQuery(test);
			while(r1.next()){
			   	uuid=r1.getString("service_uuid");
				}
			r1.close();
			stmt.close();
			conn.close();
			System.setProperty("ec.service.uuid", uuid);
		}
		catch(SQLException e){
			e.printStackTrace();
			System.exit(1);
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		
		
	}
}
