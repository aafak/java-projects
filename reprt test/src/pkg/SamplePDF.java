package pkg;
import java.util.HashMap;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.view.JasperViewer;



public class SamplePDF {
/** Creates a new instance of Sample */
public SamplePDF() {
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
                     
         //  to view report in the JasperViewer
             JasperViewer.viewReport(jasperPrint);
           
         
          // You can use JasperPrint to create PDF
             
           /*  JRPdfExporter pdfExporter = new JRPdfExporter();
             pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
             pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "report/site.pdf");
             pdfExporter.exportReport();*/
             File reportDir=new File("report","reportName");  // we r creating folder of report name also
             
             
             if(!reportDir.isDirectory())
            	 reportDir.mkdir();
             
            	 String reportFile=reportDir.getAbsolutePath()+File.separator+"reportName"+".pdf";
            // JasperExportManager.exportReportToPdfFile(jasperPrint,"report/test.pdf"); //this is enough for export
             
            	 JasperExportManager.exportReportToPdfFile(jasperPrint,reportFile);
            	 JasperExportManager.exportReportToHtmlFile(jasperPrint, "report/myreport.html");
            	 System.out.println("exported...");
             //
             
      
}

catch(Exception e){e.printStackTrace();}
}
}


