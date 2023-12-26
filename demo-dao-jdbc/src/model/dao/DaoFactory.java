/*-------------------- packages --------------------*/
package model.dao;

/*-------------------- modules --------------------*/
import db.DB;
import model.dao.impl.SellerDaoJDBC;

/*-------------------- class DaoFactory --------------------*/
public class DaoFactory {
	
	/*-------------------- methods --------------------*/
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
}
