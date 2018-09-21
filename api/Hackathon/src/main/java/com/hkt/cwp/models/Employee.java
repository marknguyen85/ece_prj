package com.hkt.cwp.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	private Integer id;

	private String code;

	private String name;

	private String password;

	private String username;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;

	//bi-directional many-to-one association to Technique
	@ManyToOne
	private Technique technique;

	//bi-directional many-to-one association to EmployeeSkillTest
	@OneToMany(mappedBy="employee")
	private List<EmployeeSkillTest> employeeSkillTests;

	public Employee() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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