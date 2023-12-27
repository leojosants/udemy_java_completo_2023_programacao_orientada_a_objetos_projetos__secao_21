/*-------------------- packages --------------------*/
package model.dao;

/*-------------------- libraries --------------------*/
import java.util.List;

/*-------------------- modules --------------------*/
import model.entities.Department;

/*-------------------- interface DepartmentDao --------------------*/
public interface DepartmentDao {

	/*-------------------- methods --------------------*/
	public void insert(Department obj);
	public void update(Department obj);
	public void deleteById(Integer id);
	public Department findById(Integer id);
	public List<Department> findAll();
}
