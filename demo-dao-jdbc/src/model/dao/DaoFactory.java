/*-------------------- packages --------------------*/
package model.dao;

/*-------------------- modules --------------------*/
import model.dao.impl.SellerDaoJDBC;


/*-------------------- class DaoFactory --------------------*/
public class DaoFactory {
	
	/*-------------------- methods --------------------*/
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
}
