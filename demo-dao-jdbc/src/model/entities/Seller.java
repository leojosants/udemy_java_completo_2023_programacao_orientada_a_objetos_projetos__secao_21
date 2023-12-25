/*-------------------- packages --------------------*/
package model.entities;

import java.util.Date;
import java.util.Objects;

/*-------------------- class Seller --------------------*/
public class Seller {

	/*-------------------- attributes --------------------*/
	private Integer id;
	private String name;
	private String email;
	private Date birth_date;
	private Double base_salary;
	
	/*-------------------- constructors --------------------*/
	public Seller() {
		//
	}

	public Seller(Integer id, String name, String email, Date birth_date, Double base_salary) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birth_date = birth_date;
		this.base_salary = base_salary;
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

	/*-------------------- methods --------------------*/
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", email=" + email + ", birth_date=" + birth_date
				+ ", base_salary=" + base_salary + "]";
	}
}
