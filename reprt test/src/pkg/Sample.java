package pkg;
import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;



public class Sample {
/** Creates a new instance of Sample */
public Sample() {
}
/**
* @param args the command line arguments
*/
public static void main(String[] args)
{
try
{
    // First, load JasperDesign from XML and compile it into JasperReport
     InputStream in = new FileInputStream("report/configuration_site.jrxml");
 	 JasperReport jasperReport = JasperCompileManager.compileReport(in);
	
      // Second, create a map of parameters to pass to the report.
       Map<String, Object> parameters = new HashMap<String, Object>();
      
       
       // Third, get a database connection
           Connection cn = null;
           String HOST = "jdbc:mysql://10.20.63.22:3306/cloud";
          String USERNAME = "root";
          String PASSWORD = "test";
          try {
               Class.forName("com.mysql.jdbc.Driver");
             }
            catch (ClassNotFoundException ex) {
            	ex.printStackTrace();
                   
            }

       try {
              cn = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
    

           }
       catch (SQLException ex) {
                ex.printStackTrace();
          }
      
         // Fourth, create JasperPrint using fillReport() method
          
            JasperPrint jasperPrint =JasperFillManager.fillReport(jasperReport, parameters, cn);
          // You can use JasperPrint to create PDF
            // JasperManager.printReportToPdfFile(jasperPrint, "\\SampleReport.pdf");
             // Or to view report in the JasperViewer
             JasperViewer.viewReport(jasperPrint);
            
}
catch(Exception e){e.printStackTrace();}

}

}