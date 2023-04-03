package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExcecuteQuery {

	public static void main(String[] args) throws SQLException
	{
		//driver for mysql database
		Driver driverRef=new Driver();
		
		//step 1:register the driver
		DriverManager.registerDriver(driverRef);
		
		//step 2: get the connection from database-database name
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bujji1998db","root","root");
		
		//step 3: issue the create statement
		Statement state=con.createStatement();
		
		//step 4: execute a query-table name
		ResultSet result=state.executeQuery("select * from candidateinfo;");
		while(result.next())
		{
			System.out.println(result.getString(1)+" "+result.getInt(2)+" "+result.getString(3));
		}
	
		//step 5: close the database
		con.close();
		System.out.println("db closed");
		
	}
}
