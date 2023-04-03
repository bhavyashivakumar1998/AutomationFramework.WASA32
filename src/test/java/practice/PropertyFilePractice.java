package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFilePractice {

	public static void main(String[] args) throws IOException {
		
		//step 1: open the file in java readable format
		FileInputStream fis= new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		//step 2: create object of properties from java.util package
		Properties pObj= new Properties();
		
		//step 3: load the file input stream into properties
		pObj.load(fis);
		
		//step 4: access the values with keys
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
	    String PASSWORD = pObj.getProperty("password");
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
	}
}
