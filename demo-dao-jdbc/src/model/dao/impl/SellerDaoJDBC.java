/*-------------------- packages --------------------*/
package model.dao.impl;

/*-------------------- libraries --------------------*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*-------------------- modules --------------------*/
import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

/*-------------------- class SellerDaoJDBC --------------------*/
public class SellerDaoJDBC implements SellerDao {

	/*-------------------- attributes --------------------*/
	private Connection connection;
	
	/*-------------------- constructors --------------------*/
	public SellerDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	/*-------------------- methods --------------------*/
	@Override
	public void insert(Seller department) {
		// TODO Auto-generated method stub
	}


	@Override
	public void update(Seller department) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement prepared_statement = null;
		ResultSet result_set = null;
		 
		try {
			prepared_statement = connection.prepareStatement(
				"SELECT seller.*, department.Name as DepName " +
				"FROM seller INNER JOIN department " +
				"ON seller.DepartmentId = department.Id " +
				"WHERE seller.Id = ?"
			);

			prepared_statement.setInt(1, id);
			result_set = prepared_statement.executeQuery();
			
			if (result_set.next()) {
				Department department = instanceateDepartment(result_set);
				Seller seller = instanceateSeller(result_set, department);
				return seller;
			}
		} 
		 catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(prepared_statement);
			DB.closeResultSet(result_set);
		}
		return null;
	}

	private Seller instanceateSeller(ResultSet result_set, Department department) throws SQLException {
		Seller seller = new Seller();
		seller.setId(result_set.getInt("Id"));
		seller.setName(result_set.getString("Name"));
		seller.setEmail(result_set.getString("Email"));
		seller.setBirthDate(result_set.getDate("BirthDate"));
		seller.setBaseSalary(result_set.getDouble("BaseSalary"));
		seller.setDepartment(department);
		return seller;
	}

	private Department instanceateDepartment(ResultSet result_set) throws SQLException {
		Department department = new  Department();
		department.setId(result_set.getInt("DepartmentId"));
		department.setName(result_set.getString("DepName"));
		return department;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement prepared_statement = null;
		ResultSet result_set = null;
		 
		try {
			prepared_statement = connection.prepareStatement(
				"SELECT seller.*,department.Name as DepName " +
				"FROM seller INNER JOIN department " +
				"ON seller.DepartmentId = department.Id " +
				"ORDER BY Name"
			);

			result_set = prepared_statement.executeQuery();
			List<Seller> seller_list = instanceateArrayList();
			Map<Integer, Department> department_map = instanceateHashMap();
			
			while (result_set.next()) {
				Department dep = department_map.get(result_set.getInt("DepartmentId"));
				
				if (dep == null) {
					dep = instanceateDepartment(result_set);					
					department_map.put(result_set.getInt("DepartmentId"), dep);
				}
				
				Seller seller = instanceateSeller(result_set, dep);
				seller_list.add(seller);
			}
			
			return seller_list;
		} 
		 catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(prepared_statement);
			DB.closeResultSet(result_set);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement prepared_statement = null;
		ResultSet result_set = null;
		 
		try {
			prepared_statement = connection.prepareStatement(
				"SELECT seller.*,department.Name as DepName " +
				"FROM seller INNER JOIN department " +
				"ON seller.DepartmentId = department.Id " +
				"WHERE DepartmentId = ? "+
				"ORDER BY Name"
			);

			prepared_statement.setInt(1, department.getId());
			result_set = prepared_statement.executeQuery();
			List<Seller> seller_list = instanceateArrayList();
			Map<Integer, Department> department_map = instanceateHashMap();
			
			while (result_set.next()) {
				Department dep = department_map.get(result_set.getInt("DepartmentId"));
				
				if (dep == null) {
					dep = instanceateDepartment(result_set);					
					department_map.put(result_set.getInt("DepartmentId"), dep);
				}
				
				Seller seller = instanceateSeller(result_set, dep);
				seller_list.add(seller);
			}
			
			return seller_list;
		} 
		 catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(prepared_statement);
			DB.closeResultSet(result_set);
		}
	}

	private Map<Integer, Department> instanceateHashMap() {
		return new HashMap<>();
	}

	private List<Seller> instanceateArrayList() {
		return new ArrayList<>();
	}
}
