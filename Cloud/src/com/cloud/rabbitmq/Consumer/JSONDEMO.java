package com.cloud.rabbitmq.Consumer;

import java.lang.reflect.Modifier;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class Controller{
     String name;
    
        public Controller(String name){
             this.name=name;
        }
 }

class Site{

      String name;
      String location;
       String desc;
      List <Controller>controllers;
    
    public Site(String name,String desc,String loc,List<Controller>cont){
      this.location=loc;
      this.name=name;
      this.desc=desc;
      this.controllers=cont;
      
   }
}
public class JSONDEMO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
          
		  List<Controller> controller=new ArrayList<Controller>();
        
		   controller.add(new Controller("c1"));
           controller.add(new Controller("c21"));
           controller.add(new Controller("c31"));
		   Site s=new Site("Site1","This is demo site","Banglore",controller);
          
		   Gson gson=new Gson();
           String json=gson.toJson(s);
           System.out.println(json);
    
           Gson g2=new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
           String json2=g2.toJson(s);
           System.out.println(json2);
           String loc = "Banglore";

           String json_res = "{\"name\":\"Site1\",\"desc\":\"demosite\",  \"location\":\" "+loc+" \",\"controllers\":[{\"name\":\"c1\"},{\"name\":\"c2\"}]}";
           Site siteobj=gson.fromJson(json_res,Site.class);
           System.out.println("site name:"+siteobj.name);
           System.out.println("site Description:"+siteobj.desc);
           System.out.println("site Location:"+siteobj.location);
           List<Controller>c=siteobj.controllers;
           Iterator<Controller> itr=c.iterator();
           System.out.println("Controllers are");
           while(itr.hasNext())
           { Controller cont=itr.next();
        	   System.out.println(cont.name);
        	   
           }
	}

}

