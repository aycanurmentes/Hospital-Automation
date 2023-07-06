package Helper;
import java.sql.*;

public class DBConnection {
Connection c=null;

public DBConnection() {
	
}

public Connection connDb() {
	try
    {
        Class.forName("org.sqlite.JDBC");//load the sqlite driver
        //connect the database
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Hospital Automation.db");
        System.out.println("Connection is successful");
        return conn;
    }
    catch(Exception e)
    {
        System.out.println("Connection failed");
        return null;
     }
}
}
