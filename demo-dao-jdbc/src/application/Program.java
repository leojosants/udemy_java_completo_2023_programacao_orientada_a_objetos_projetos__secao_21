/*-------------------- packages --------------------*/
package application;

/*-------------------- libraries --------------------*/
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/*-------------------- modules --------------------*/
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

/*-------------------- class Program --------------------*/
public class Program {

	/*-------------------- functions --------------------*/	
	private static Department instanceateDepartment(Integer id) {
		return new Department(id, null);
	}
	
	private static Seller instanceateSeller(String name, String email, Date date, Double Base_salary, Department department) {
		return new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
	}
	
	private static void displaySellerList(List<Seller> seller_list) {
		seller_list.forEach(seller -> {
			seller.printSeller();
		});
	}
	
	private static Date instanceateDate() {
		return new Date();
	}
	
	private static Scanner instaceateScanner() {
		return new Scanner(System.in);
	}
	
	private static Integer requestIdDelete(Scanner scanner) {
		Integer id = null;
		
		System.out.print("Entre com o id para deletar ..: ");
		id = scanner.nextInt();
		scanner.nextLine(); // buffer
		
		while (id <= 0) {
			System.out.print("-> Valor inválido, tente novamente! Entre com o id para deletar ..: ");
			id = scanner.nextInt();
			scanner.nextLine(); // buffer
		}
		
		return id;
	}
	
	private static void test01(SellerDao seller_dao) {
		System.out.println("\n--- TEST 1: seller findById ---");
		Seller seller = seller_dao.findById(3);		
		seller.printSeller();
	}
	
	private static void test02(SellerDao seller_dao) {
		System.out.println("--- TEST 2: seller findByDepartment ---");
		Department department = instanceateDepartment(2);
		List<Seller> seller_list = seller_dao.findByDepartment(department);
		displaySellerList(seller_list);
	}
	
	private static void test03(SellerDao seller_dao) {
		System.out.println("--- TEST 3: seller findAll ---");
		List<Seller> seller_list = seller_dao.findAll();
		displaySellerList(seller_list);
	}
	
	private static void test04(SellerDao seller_dao) {
		System.out.println("--- TEST 4: seller insert ---");
		Department department = instanceateDepartment(2);
		Seller seller = instanceateSeller("Greg", "greg@gmail.com", instanceateDate(), 4000.0, department);
		seller_dao.insert(seller);
		System.out.printf("Inserido! Novo Id ...: %d%n", seller.getId());
	}
	
	private static void test05(SellerDao seller_dao) {
		System.out.println("\n--- TEST 5: seller update ---");
		Seller seller = seller_dao.findById(1);
		seller.setName("Marta Waine");
		seller_dao.update(seller);
		System.out.println("Atualização concluida!");
	}
	
	private static void test06(SellerDao seller_dao) {
		System.out.println("\n--- TEST 6: seller delet ---");
		Scanner scanner = instaceateScanner();
		Integer id = requestIdDelete(scanner);
		seller_dao.deleteById(id);
		System.out.println("Deleção concluida!");
		scanner.close();
	}
	
	private static void displayMessageEndProgram() {
		System.out.println("\n---> fim do programa <---");
	}

	private static void runProgram() {
		Locale.setDefault(Locale.US);
		SellerDao seller_dao = DaoFactory.createSellerDao();
		test01(seller_dao);
		test02(seller_dao);
		test03(seller_dao);
		test04(seller_dao);
		test05(seller_dao);
		test06(seller_dao);
	}
	
	/*-------------------- main method --------------------*/
	public static void main(String[] args) {
		runProgram();
		displayMessageEndProgram();
	}
}
