package aesimagecrypt;
import java.sql.*;
public class DatabaseConnection {
	
	public void connectToDatabase(String fileName,String checksum)
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
			String insertTableSQL = "INSERT INTO pathhash"+ "(filepath, hashvalue) VALUES"+ "(?,?)";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = con.prepareStatement(insertTableSQL);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				preparedStatement.setString(1, fileName);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				preparedStatement.setString(2, checksum);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				preparedStatement .executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
			}
}