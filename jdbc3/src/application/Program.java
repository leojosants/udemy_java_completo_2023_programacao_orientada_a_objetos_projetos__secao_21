/*-------------------- packages --------------------*/
package application;

/*-------------------- libraries --------------------*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*-------------------- modules --------------------*/
import db.DB;

/*-------------------- class Program --------------------*/
public class Program {
	
	/*-------------------- functions --------------------*/
	private static void example01() {
		SimpleDateFormat simple_date_format = new SimpleDateFormat("dd/MM/yyyy");
		Connection connection = null;
		PreparedStatement prepared_statement = null;
		
		try {
			connection = DB.getConnection();
			
			prepared_statement = connection.prepareStatement(
					"INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartMentId)" +
					"VALUES (?, ?, ?, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS
			);
			
			prepared_statement.setString(1, "Carl purple");
			prepared_statement.setString(2, "carl@gmail.com");
			prepared_statement.setDate(3, new java.sql.Date(simple_date_format.parse("22/04/1995").getTime()));
			prepared_statement.setDouble(4, 3000.0);
			prepared_statement.setInt(5, 4);
			
			int rows_affected = prepared_statement.executeUpdate();
			
			if (rows_affected > 0) {
				ResultSet result_set = prepared_statement.getGeneratedKeys(); // id recovery
				while (result_set.next()) {
					int id = result_set.getInt(1);
					System.out.printf("Feito! Id ..: %d%n", id);
				}
			}
			else {
				System.out.println("Nenhuma linha afetada!");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(prepared_statement);
			DB.closeConnection();
		}
	}
	
	private static void example02() {
		Connection connection = null;
		PreparedStatement prepared_statement = null;
		
		try {
			connection = DB.getConnection();
			
			prepared_statement = connection.prepareStatement(
					"INSERT INTO department (Name)" +
					"VALUES ('D1'), ('D2')", 
					Statement.RETURN_GENERATED_KEYS
			);
			
			int rows_affected = prepared_statement.executeUpdate();
			
			if (rows_affected > 0) {
				ResultSet result_set = prepared_statement.getGeneratedKeys(); // id recovery
				while (result_set.next()) {
					int id = result_set.getInt(1);
					System.out.printf("Feito! Id ..: %d%n", id);
				}
			}
			else {
				System.out.println("Nenhuma linha afetada!");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(prepared_statement);
			DB.closeConnection();
		}
	}
	
	private static void displayMessageEndOfProgram() {
		System.out.println("\n---> fim do programa <---");
	}
	
	private static void runProgram() throws ParseException {
//		example01(); // do not run together with example 1
		example02(); // do not run together with example 2
	}
	
	/*-------------------- main method --------------------*/
	public static void main(String[] args) throws ParseException {
		runProgram();
		displayMessageEndOfProgram();
	}
}
