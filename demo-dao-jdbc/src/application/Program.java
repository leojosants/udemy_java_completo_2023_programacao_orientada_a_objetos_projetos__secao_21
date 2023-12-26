/*-------------------- packages --------------------*/
package application;

import java.util.Locale;

/*-------------------- libraries --------------------*/

/*-------------------- modules --------------------*/
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

/*-------------------- class Program --------------------*/
public class Program {

	/*-------------------- functions --------------------*/	
	private static void displayMessageEndProgram() {
		System.out.println("\n---> fim do programa <---");
	}

	private static void runProgram() {
		Locale.setDefault(Locale.US);
		SellerDao seller_dao = DaoFactory.createSellerDao();
		Seller seller = seller_dao.findById(3);		
		seller.printSeller();
	}
	
	/*-------------------- main method --------------------*/
	public static void main(String[] args) {
		runProgram();
		displayMessageEndProgram();
	}
}
