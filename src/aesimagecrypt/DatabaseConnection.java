package aesimagecrypt;
import java.io.File;
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
	
	public ResultSet displayDataBase()
	{
		
		ResultSet rs=null;
		PreparedStatement ps = null;
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
		String query = "SELECT distinct filepath, hashvalue FROM pathhash";
		try {
			ps = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
}