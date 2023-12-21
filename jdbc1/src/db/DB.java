/*-------------------- packages --------------------*/
package db;

/*-------------------- libraries --------------------*/
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*-------------------- class DB --------------------*/
public class DB {

	/*-------------------- attributes --------------------*/
	private static Connection conn = null;
	
	/*-------------------- methods --------------------*/
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties properties = loadProperties();
				String url = properties.getProperty("dburl");
				conn = DriverManager.getConnection(url, properties);
			} 
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} 
		catch (Exception e) {
			throw new DbException(e.getMessage());
		}
	}
	
	private static Properties loadProperties() {
		try (FileInputStream file_input_stream = new FileInputStream("db.properties")) {
			Properties properties = new Properties();
			properties.load(file_input_stream);
			return properties;
		} 
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
}
