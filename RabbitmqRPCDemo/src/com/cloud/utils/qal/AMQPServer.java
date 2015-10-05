package com.cloud.utils.qal;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;


public class AMQPServer implements Runnable{
	private static final Logger s_logger = Logger.getLogger(AMQPServer.class);
	
	/**Exchange name of the queue**/
    private String exchange = "cloudbyte";
    
    /** queue name to use for requests */
	private String _queueName;
	
	/*Listener service class */
	ListenerService lst;
	
	
	public AMQPServer(ListenerService Obj, String service) throws UnknownHostException{
		String uuid="";
		Properties prop = new Properties();
		final File dbPropsFile = PropertiesUtil.findConfigFile("db.properties");
		FileInputStream in = null;
		String USER = null;
		String PASS = null;
		String HOST = null;
		String DB   = null;
	//	ElasConfig _elasConfig = new ElasConfig();
		try{
			in = new FileInputStream(dbPropsFile);
			prop.load(in);
			USER = prop.getProperty("db.cloud.username");
			PASS = prop.getProperty("db.cloud.password");
			HOST = prop.getProperty("db.cloud.host");
			DB   = prop.getProperty("db.cloud.name");
			String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			String DB_URL = "jdbc:mysql://"+HOST+"/"+DB;
			Connection conn = null;
			Statement  stmt = null;
			int count=0;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String test;
			test="SELECT count(*) from cb_elasticenter";
			ResultSet r1=stmt.executeQuery(test);
			if(r1.next()){
				count=r1.getInt(1);
				r1.close();
			}
			if (count == 0 ){
				ElasConfig.setConfig("server");
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
			uuid = System.getProperty("ec.service.uuid");
			if(uuid !=null){
				_queueName = "cbd." + uuid + "." + service;
				lst = Obj;
			}
			else{
				ElasConfig.setServiceUUID();
				uuid=System.getProperty("ec.service.uuid");
				_queueName = "cbd." + uuid + "." + service;
				lst = Obj;
			}
			
	}

				
	public void run(){
		try {
			CloudByteRpcServer rpcServer = new CloudByteRpcServer(exchange,_queueName,lst);
			rpcServer.service();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	

}
