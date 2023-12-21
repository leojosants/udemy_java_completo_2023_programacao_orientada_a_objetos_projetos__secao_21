/*-------------------- packages --------------------*/
package application;

/*-------------------- libraries --------------------*/
import java.sql.Connection;

/*-------------------- modules --------------------*/
import db.DB;

/*-------------------- class Program --------------------*/
public class Program {
	
	/*-------------------- functions --------------------*/
	private static void runProgram() {
		Connection conn = DB.getConnection();
		DB.closeConnection();
	}
	
	/*-------------------- main method --------------------*/
	public static void main(String[] args) {
		runProgram();
	}
}
