package uploadData;

import java.sql.Connection;
import java.sql.DriverManager;

import connection.DataConnection;

public class DatabaseCon {

	public Connection getConnection() {
//		String  url= "jdbc:sqlserver://FRANKLIN;databasename=walex;";
//		String username="sa";
//		String password="Xavier3456";
//	Connection con=null;
//		
//		try {
//			  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			 con=DriverManager.getConnection(url, username, password);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println(e);
//		}
//		return con;
//	}
	
	
	Connection con=null;
	
	try {
		  Class.forName("org.postgresql.Driver");
		 con=DriverManager.getConnection(DataConnection.url,DataConnection. username,DataConnection. password);

	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e);
	}
	return con;
}

}
