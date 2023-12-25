/*-------------------- packages --------------------*/
package model.entities;

import java.io.Serializable;
import java.util.Objects;

/*-------------------- class Department --------------------*/
public class Department implements Serializable {

	/*-------------------- attributes --------------------*/
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
	/*-------------------- constructors --------------------*/
	// default
	public Department() {
		//
	}

	// overload
	public Department(Integer id, String name) {
		this.setId(id);
		this.setName(name);
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
		Department other = (Department) obj;
		return Objects.equals(this.getId(), other.getId());
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
	
	public void printDepartment() {
		System.out.println(toString());
	}
}
