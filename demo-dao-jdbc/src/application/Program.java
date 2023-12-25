/*-------------------- packages --------------------*/
package application;

/*-------------------- libraries --------------------*/

/*-------------------- modules --------------------*/
import model.entities.Department;

/*-------------------- class Program --------------------*/
public class Program {

	/*-------------------- functions --------------------*/
	private static void runProgram() {
		Department department = new Department(1, "Livros");
		department.printDepartment();
	}
	
	private static void displayMessageEndProgram() {
		System.out.println("\n---> fim do programa <---");
	}
	
	/*-------------------- main method --------------------*/

	public static void main(String[] args) {
		runProgram();
		displayMessageEndProgram();
	}
}
