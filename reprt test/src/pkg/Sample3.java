package pkg;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;



public class Sample3 {
 
    Connection conn = null;
 
    public void initConnection(){
        
       // String HOST = "jdbc:mysql://localhost:3306/DATABASE_NAME";
    	 String HOST = "jdbc:mysql://10.20.63.22:3306/cloud";
        String USERNAME = "root";
        String PASSWORD = "test";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    public void showReport() throws FileNotFoundException{
         
        //Path to your .jasper file in your package
        String reportName = "configuration_site";
         String reportDefFileName="report/defination"+File.separator+reportName+".jasper";
        
        try {
        	
        	//Get a stream to read the file
              // InputStream is = new FileInputStream(reportDefFileName);
     
     //Fill the report with parameter, connection and the stream reader     
            JasperPrint jp = JasperFillManager.fillReport(reportDefFileName, null, conn);
         
     //Viewer for JasperReport
            JRViewer jv = new JRViewer(jp);
     
     //Insert viewer to a JFrame to make it showable
            JFrame jf = new JFrame();
            jf.getContentPane().add(jv);
            jf.validate();
            jf.setVisible(true);
            jf.setSize(new Dimension(800,600));
            jf.setLocation(300,100);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
 
    public static void main(String[] args) throws FileNotFoundException {
 
        Sample3 s = new Sample3();
        s.initConnection();
        s.showReport();
    }
 
}

