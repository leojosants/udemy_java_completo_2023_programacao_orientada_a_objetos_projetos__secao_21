/*-------------------- packages --------------------*/
package model.dao.impl;

/*-------------------- libraries --------------------*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
/*-------------------- modules --------------------*/
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
		// TODO Auto-generated method stub
		return null;
	}
}
