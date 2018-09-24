package com.hkt.cwp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@Table(name="employee")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String code;

	private String name;

	private String password;

	private String username;

	//bi-directional many-to-one association to Role
	@JsonIgnore
	@ManyToOne
	private Role role;

	//bi-directional many-to-one association to Technique
	@JsonIgnore
	@ManyToOne
	private Technique technique;

	//bi-directional many-to-one association to EmployeeSkillTest
	
	@JsonIgnore
	@OneToMany(mappedBy="employee")
	private List<EmployeeSkillTest> employeeSkillTests;

	public Employee() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Technique getTechnique() {
		return this.technique;
	}

	public void setTechnique(Technique technique) {
		this.technique = technique;
	}

	public List<EmployeeSkillTest> getEmployeeSkillTests() {
		return this.employeeSkillTests;
	}

	public void setEmployeeSkillTests(List<EmployeeSkillTest> employeeSkillTests) {
		this.employeeSkillTests = employeeSkillTests;
	}

	public EmployeeSkillTest addEmployeeSkillTest(EmployeeSkillTest employeeSkillTest) {
		getEmployeeSkillTests().add(employeeSkillTest);
		employeeSkillTest.setEmployee(this);

		return employeeSkillTest;
	}

	public EmployeeSkillTest removeEmployeeSkillTest(EmployeeSkillTest employeeSkillTest) {
		getEmployeeSkillTests().remove(employeeSkillTest);
		employeeSkillTest.setEmployee(null);

		return employeeSkillTest;
	}

}