/*-------------------- packages --------------------*/
package model.dao;

/*-------------------- libraries --------------------*/
import java.util.List;

/*-------------------- modules --------------------*/
import model.entities.Department;

/*-------------------- interface DepartmentDao --------------------*/
public interface DepartmentDao {

	/*-------------------- methods --------------------*/
	public void insert(Department department);
	public void update(Department department);
	public void deleteById(Integer id);
	public Department findById(Integer id);
	public List<Department> findAll();
}
