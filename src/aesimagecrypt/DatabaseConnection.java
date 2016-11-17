package aesimagecrypt;
import java.sql.*;
public class DatabaseConnection {
	
	public void connectToDatabase()
	{

			String url="jdbc:mysql://localhost:3306/thedb";
			String userName="root";
			String password="";
            try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Connection con = null;
			try {
				con = DriverManager.getConnection(url,userName,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("We Have liftoff");
            try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
}
}