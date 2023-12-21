/*-------------------- packages --------------------*/
package application;

/*-------------------- libraries --------------------*/
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*-------------------- modules --------------------*/
import db.DB;

/*-------------------- class Program --------------------*/
public class Program {
	
	/*-------------------- functions --------------------*/
	private static void runProgram() {
		Connection conn = null;
		Statement statement = null;
		ResultSet result_set = null;
		
		try {
			conn = DB.getConnection();
			statement = conn.createStatement();
			result_set = statement.executeQuery("select * from department");
			
			while (result_set.next()) {
				System.out.println(result_set.getInt("Id") + ", " + result_set.getString("Name"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			DB.closeResultSet(result_set);
			DB.closeStatement(statement);
			DB.closeConnection();
		}
	}
	
	/*-------------------- main method --------------------*/
	public static void main(String[] args) {
		runProgram();
	}
}
