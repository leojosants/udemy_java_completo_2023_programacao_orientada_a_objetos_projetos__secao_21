/*-------------------- packages --------------------*/
package model.dao;

/*-------------------- libraries --------------------*/
import java.util.List;

/*-------------------- modules --------------------*/
import model.entities.Department;
import model.entities.Seller;

/*-------------------- interface Seller --------------------*/
public interface SellerDao {

	/*-------------------- methods --------------------*/
	public void insert(Seller department);
	public void update(Seller department);
	public void deleteById(Integer id);
	public Seller findById(Integer id);
	public List<Seller> findAll();
	public List<Seller> findByDepartment(Department department);
}
