



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author arper
 */
public class singleton {
    private static singleton instance;
    private Connection connection;
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/TwoVago";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private singleton(){
	try {
            connection = DriverManager.getConnection (DATABASE_URL,USER,PASSWORD);
	}catch (SQLException e){
            e.printStackTrace();
	}
    }
    
    public static singleton getInstance(){
	if (instance == null){
            instance = new singleton();
	}
	return instance;
	}

   public Connection getconnection(){
	return connection;
    }
   
    public void closeconnection(){
	if (connection != null){
            try {
		connection.close();
            }catch (SQLException e) {
            e.printStackTrace();
            }
	}
    }
}

