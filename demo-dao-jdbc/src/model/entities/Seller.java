/*-------------------- packages --------------------*/
package model.entities;

/*-------------------- libraries --------------------*/
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/*-------------------- class Seller --------------------*/
public class Seller implements Serializable {

	/*-------------------- attributes --------------------*/
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String email;
	private Date birth_date;
	private Double base_salary;
	private Department department; // association composition

	/*-------------------- constructors --------------------*/
	public Seller() {
		//
	}

	public Seller(Integer id, String name, String email, Date birth_date, Double base_salary, Department department) {
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setBirthDate(birth_date);
		this.setBaseSalary(base_salary);
		this.setDepartment(department);
	}

	/*-------------------- getters and setters --------------------*/
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return this.birth_date;
	}

	public void setBirthDate(Date birth_date) {
		this.birth_date = birth_date;
	}

	public Double getBaseSalary() {
		return this.base_salary;
	}

	public void setBaseSalary(Double base_salary) {
		this.base_salary = base_salary;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	/*-------------------- methods --------------------*/
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Seller other = (Seller) obj;
		return Objects.equals(this.getId(), other.getId());
	}

	@Override
	public String toString() {
		return "Vendedor\n" +
				String.format("Id ..................: %d%n", this.getId()) +
				String.format("Nome ................: %s%n", this.getName()) +
				String.format("Email ...............: %s%n", this.getEmail()) +
				String.format("Data de nascimento ..: %s%n", this.getBirthDate()) +
				String.format("Base salarial .......: R$ %.2f%n", this.getBaseSalary()) +
				String.format("Departamento ........: %s%n", this.getDepartment());
	}

	public void printSeller() {
		System.out.println(toString());
	}
}
