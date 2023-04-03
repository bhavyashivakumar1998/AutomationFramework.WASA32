package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Samplejdbcquery {

	public static void main(String[] args) throws SQLException {
		
		//driver for mysql database
		Driver driverRef= new Driver();
		
		//register the driver
		DriverManager.registerDriver(driverRef);
		
		//get the connection from the database
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/shivakumar75","root", "root");
		
		//issue the create statement
		Statement state=con.createStatement();
		
		//execute a query
		ResultSet result= state.executeQuery("select * from shivainfo;");
		while(result.next())
		{
			System.out.println(result.getString(1)+" "+result.getString(2)+" "+result.getString(3));
		}
		//close the database
		con.close();
		System.out.println("db closed");
	}
}
