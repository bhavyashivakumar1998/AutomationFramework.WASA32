package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteUpdate {

	public static void main(String[] args) throws SQLException {
		Connection con =null;
		try {
		Driver driverRef= new Driver();
		
		
		//step 1: register the driver
		DriverManager.registerDriver(driverRef);
		
		//step 2: get the connection
		 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bujji1998db","root","root");
		
		//step 3:issue create statement
		Statement state = con.createStatement();
		
		//step 4: execute a query
		String query="insert into candidateinfo values('Bharath',45,'Bangaluru');";
		int result =state.executeUpdate(query);
		if(result>=1)
		{
			System.out.println("data added");
		}
		ResultSet result1= state.executeQuery("select * from candidateinfo;");
		while(result1.next())
		{
			System.out.println(result1.getString(1)+" "+result1.getInt(2)+" "+result1.getString(3));
		}
		}
		catch(Exception e)
		{
			
		}
		finally {
			//step 5: close the database
			con.close();
			System.out.println("db closed");
		}
		
	}
}
