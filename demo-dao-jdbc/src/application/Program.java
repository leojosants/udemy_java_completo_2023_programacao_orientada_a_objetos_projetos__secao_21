/*-------------------- packages --------------------*/
package application;

/*-------------------- libraries --------------------*/
import java.util.Date;

/*-------------------- modules --------------------*/
import model.entities.Department;
import model.entities.Seller;

/*-------------------- class Program --------------------*/
public class Program {

	/*-------------------- functions --------------------*/
	private static Integer generateId() {
		return (int) (Math.random() * 1000);
	}
	
	private static Department generateInstanceDepartment(Integer id, String name) {
		return new Department(id, name);
	}
	
	private static Seller generateInstanceSeller(Integer id, String name, String email, Date date, Double base_salary, Department department) {
		return new Seller(id, name, email, date, base_salary, department);
	}
	
	private static Date generateInstanceDate() {
		return new Date();
	}
	
	private static void displayMessageEndProgram() {
		System.out.println("\n---> fim do programa <---");
	}

	private static void runProgram() {
		Department department = generateInstanceDepartment(generateId(), "Livros");
		Seller seller = generateInstanceSeller(generateId(), "Bob", "bob@gmail.com", generateInstanceDate(), 3000.0, department);
		seller.printSeller();
	}
	
	/*-------------------- main method --------------------*/
	public static void main(String[] args) {
		runProgram();
		displayMessageEndProgram();
	}
}
