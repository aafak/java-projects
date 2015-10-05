package jdbctest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class JdbcDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloud","root","test");
			PreparedStatement ps = cn.prepareStatement("insert into cb_pool values(?,?)");
			//System.out.print("Enter id");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//Scanner sc = new Scanner(System.in);
			//int id = sc.nextInt();
//			int id = Integer.parseInt(br.readLine());
//			System.out.print("Enter name:");
//			String name = br.readLine();
//			//String name = sc2.nextLine();
//
//			ps.setInt(1, id);
//			ps.setString(2, name);
//			ps.executeUpdate();
		//	System.out.println("inserted successfully");
			
			//update
			ps = cn.prepareStatement("update cb_pool set name=? where id=?");
			ps.setString(1, "p5");
			ps.setInt(2, 5);
			ps.executeUpdate();
			
			//delete 
			ps = cn.prepareStatement("delete from cb_pool where id=?");
			ps.setInt(1, 5);
			ps.executeUpdate();
			
			//ps = cn.prepareStatement("Select * from cb_pool");
			ps = cn.prepareStatement("Select * from cb_pool where id=?");
            ps.setInt(1, 5);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println("Id:"+rs.getInt(1)+", name: "+rs.getString(2));
			}
		}
		catch(Exception ex){
			System.out.println("Exception: "+ex.getMessage());
		}
	}

}
