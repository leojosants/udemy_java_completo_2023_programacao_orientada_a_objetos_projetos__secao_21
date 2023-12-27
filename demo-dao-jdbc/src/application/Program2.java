/*-------------------- packages --------------------*/
package application;

/*-------------------- libraries --------------------*/
import java.util.List;
import java.util.Scanner;

/*-------------------- modules --------------------*/
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

/*-------------------- class Program2 --------------------*/
public class Program2 {
	
	/*-------------------- functions --------------------*/	
	private static Scanner instanceateScanner() {
		return new Scanner(System.in);
	}
	
	private static void test01(DepartmentDao department_dao) {
		System.out.println("\n--- TEST 1: department findById ---");
		Department dep = department_dao.findById(1);
		dep.printDepartment();
	}
	
	private static void test02(DepartmentDao department_dao) {
		System.out.println("\n--- TEST 2: department findAll ---");
		List<Department> departments_list = department_dao.findAll();
		departments_list.forEach(dep -> dep.printDepartment());
	}
	
	private static Department instanceateDepartment(String name) {
		return new Department(null, name);
	}
	
	private static void test03(DepartmentDao department_dao) {
		System.out.println("\n--- TEST 3: department insert ---");
		Department department = instanceateDepartment("Music");
		department_dao.insert(department);
		System.out.printf("Inserido! Novo id ..: %d%n", department.getId());
	}
	
	private static void test04(DepartmentDao department_dao) {
		System.out.println("\n--- TEST 4: department update ---");
		Department department = department_dao.findById(1);
		department.setName("Food");
		department_dao.update(department);
		System.out.println("Atualização concluída!");
	}
	
	private static Integer requestId(Scanner scanner) {
		Integer id = null;
		
		System.out.print("Entre como id para deleção ..: ");
		id = scanner.nextInt();
		scanner.nextLine(); // buffer
		
		while (id < 0) {
			System.out.println("-> Dados inválidos, tente novamente! Entre como id para deleção ..: ");
			id = scanner.nextInt();
			scanner.nextLine(); // buffer
		}
		
		return id;
	}
	
	private static void test05(DepartmentDao department_dao) {
		System.out.println("\n--- TEST 5: department delete ---");
		Scanner scanner = instanceateScanner();
		Integer id = requestId(scanner);
		department_dao.deleteById(id);
		System.out.println("Remoção concluída!");
	}
	
	private static void runProgram() {
		Scanner scanner = instanceateScanner();
		DepartmentDao department_dao = DaoFactory.createDepartmentDao‎();
		test01(department_dao);
		test02(department_dao);
		test03(department_dao);
		test04(department_dao);
		test05(department_dao);
		scanner.close();
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
