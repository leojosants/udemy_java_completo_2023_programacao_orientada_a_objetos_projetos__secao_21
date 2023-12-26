/*-------------------- packages --------------------*/
package application;

/*-------------------- libraries --------------------*/
import java.util.Locale;

/*-------------------- modules --------------------*/
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

/*-------------------- class Program --------------------*/
public class Program {

	/*-------------------- functions --------------------*/	
	private static void test01(SellerDao seller_dao) {
		System.out.println("\n--- TEST 1: seller findById ---");
		Seller seller = seller_dao.findById(3);		
		seller.printSeller();
	}
	
	private static void displayMessageEndProgram() {
		System.out.println("\n---> fim do programa <---");
	}

	private static void runProgram() {
		Locale.setDefault(Locale.US);
		SellerDao seller_dao = DaoFactory.createSellerDao();
		test01(seller_dao);
	}
	
	/*-------------------- main method --------------------*/
	public static void main(String[] args) {
		runProgram();
		displayMessageEndProgram();
	}
}
