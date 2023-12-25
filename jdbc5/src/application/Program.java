/*-------------------- packages --------------------*/
package application;

/*-------------------- libraries --------------------*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

/*-------------------- modules --------------------*/
import db.DB;
import db.DbIntegrityException;

/*-------------------- class Program --------------------*/
public class Program {
	
	/*-------------------- functions --------------------*/	
	private static void displayMessageEndOfProgram() {
		System.out.println("\n---> fim do programa <---");
	}
	
	private static void runProgram() throws ParseException {
		Connection connection = null;
		PreparedStatement prepared_statement = null;
		
		try {
			connection = DB.getConnection();
			
			prepared_statement = connection.prepareStatement(
				"DELETE FROM department " +
				"WHERE Id = ?"
			);
			
			prepared_statement.setInt(1, 2);
			int rows_affected = prepared_statement.executeUpdate();
			System.out.printf("Feito! Linhas afetadas ..: %d%n", rows_affected);
		} 
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(prepared_statement);
			DB.closeConnection();
		}
	}
	
	/*-------------------- main method --------------------*/
	public static void main(String[] args) throws ParseException {
		runProgram();
		displayMessageEndOfProgram();
	}
}
