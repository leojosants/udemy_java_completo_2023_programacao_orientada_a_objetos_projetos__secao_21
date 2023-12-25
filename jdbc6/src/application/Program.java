/*-------------------- packages --------------------*/
package application;

/*-------------------- libraries --------------------*/
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

/*-------------------- modules --------------------*/
import db.DB;
import db.DbException;
import db.DbIntegrityException;

/*-------------------- class Program --------------------*/
public class Program {
	
	/*-------------------- functions --------------------*/	
	private static void displayMessageEndOfProgram() {
		System.out.println("\n---> fim do programa <---");
	}
	
	private static void runProgram() throws ParseException {
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = DB.getConnection();
			connection.setAutoCommit(false); // do not confirm operations automatically
			statement = connection.createStatement();
			
			int rows_1 = statement.executeUpdate("UPDATE seller SET BaseSalary = 2090 where DepartmentId = 1");

//			int x = 1;
//			if (x < 2) {
//				throw new SQLException("Erro falso!");
//			}
			
			int rows_2 = statement.executeUpdate("UPDATE seller SET BaseSalary = 3090 where DepartmentId = 2");
			connection.commit(); // confirm transaction
			
			System.out.printf("Linha 1 ..: %d%n", rows_1);
			System.out.printf("Linha 2 ..: %d%n", rows_2);
		} 
		catch (SQLException e) {
			try {
				connection.rollback();
				throw new DbException("Transação revertida! Causado por ..: " + e.getMessage());
			} 
			catch (SQLException e_1) {
				throw new DbIntegrityException("Erro enquanto tentava reverter a transação! Causado por ..: " + e_1.getMessage());
			}
		}
		finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}
	}
	
	/*-------------------- main method --------------------*/
	public static void main(String[] args) throws ParseException {
		runProgram();
		displayMessageEndOfProgram();
	}
}
