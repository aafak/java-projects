package com.mkyong.listener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
// The ServletContextListener will run your code before the web application is started.
//http://www.studytonight.com/servlet/servlet-context-listener.php
/*
 *ServletContextEvent and ServletContextListener?

ServletContextEvent class gives notifications about changes to the servlet context 
of a web application. ServletContextListener receives the notifications about changes to 
the servlet context and perform some action. ServletContextListener is used to perform 
important task at the time when context is initialized and destroyed. In short, 
ServletContextEvent and ServletContextListener works in pair, whenever Servlet COntext 
changes, ServletContextEvent publishes a notification which is received by 
ServletContextListener and then, based on that certain tasks are performed by it.


A ServletContext Listener will be invoked as soon as possible after the servlet context is created.
 This is almost always when the server starts up, although it can also be when a whole servlet
  container or web application is reinitialised for some reason.

The "init" method of a servlet, on the other hand, 
is only guaranteed to be called after a particular servlet has been loaded but before 
it serves any requests.

Servlet containers are free to "init" all servlets at the start, defer running "init" until the first request comes in for that servlet, or any time between these two events.

So. If you definately need your code to execute when the container starts, 
you should use a servlet lifecycle listener. If you just need to make sure your code 
has already been executed before a request has been processed, but don't really
 care when it is done otherwise, put your code in "init".
 */
public class MyAppServletContextListener 
               implements ServletContextListener{
	ServletContext ctx;
    Connection con;
    Statement s;
    PreparedStatement ps;
    ResultSet rs;
    int count=0;
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ServletContextListener destroyed");
		ctx=sce.getServletContext();
	    count=(Integer)ctx.getAttribute("pcount");
		System.out.println("Total hit:"+count);

		/*
		  try
     {
       ctx=sce.getServletContext();
       count=(Integer)ctx.getAttribute("pcount");
       ps=con.prepareStatement("update counter set pcount='"+count+"'");
       ps.executeUpdate(); 
     } 
     catch(Exception e){ e.printStackTrace(); }
    }  
		 
		 */
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContextListener started..........");	
		 ctx=sce.getServletContext();
	     ctx.setAttribute("pcount", count);
	     
		/*
		 try{
      	 Class.forName("com.mysql.jdbc.Driver");
     	 con= DriverManager.
               getConnection("jdbc:mysql://localhost:3306/test","user","password");

      s=con.createStatement();
      
      //fetching pageviews value from table counter
      rs=s.executeQuery("select pageview from counter");
        while(rs.next())
        {
            count=rs.getInt(1);
        }
       
       ctx=sce.getServletContext();
       ctx.setAttribute("pcount", count);
      }
      catch(Exception e){ e.printStackTrace(); } 
		*/
	}
}
