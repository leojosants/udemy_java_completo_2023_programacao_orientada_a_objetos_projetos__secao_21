/*-------------------- packages --------------------*/
package model.dao.impl;

/*-------------------- libraries --------------------*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		PreparedStatement prepared_statement = null;
		try {
			prepared_statement = connection.prepareStatement(
				"INSERT INTO seller " +
				"(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
				"VALUES (?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS
			);
			
			prepared_statement.setString(1, department.getName());
			prepared_statement.setString(2, department.getEmail());
			prepared_statement.setDate(3, new java.sql.Date(department.getBirthDate().getTime()));
			prepared_statement.setDouble(4, department.getBaseSalary());
			prepared_statement.setInt(5, department.getDepartment().getId());
			
			int rows_affected = prepared_statement.executeUpdate();
			
			if (rows_affected > 0) {
				ResultSet result_set = prepared_statement.getGeneratedKeys();
				
				if (result_set.next()) {
					int id = result_set.getInt(1);
					department.setId(id);
				}
				
				DB.closeResultSet(result_set);
			}
			else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada");
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(prepared_statement);
		}
	}


	@Override
	public void update(Seller obj) {
		PreparedStatement prepared_statement = null;
		try {
			prepared_statement = connection.prepareStatement(
				"UPDATE seller " +
				"SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " +
				"WHERE Id = ?"
			);
			
			prepared_statement.setString(1, obj.getName());
			prepared_statement.setString(2, obj.getEmail());
			prepared_statement.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			prepared_statement.setDouble(4, obj.getBaseSalary());
			prepared_statement.setInt(5, obj.getDepartment().getId());
			prepared_statement.setInt(6, obj.getId());
			prepared_statement.executeUpdate();
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(prepared_statement);
		}
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
