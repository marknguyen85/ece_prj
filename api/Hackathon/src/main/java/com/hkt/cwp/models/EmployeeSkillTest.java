package com.hkt.cwp.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the employee_skill_test database table.
 * 
 */
@Entity
@Table(name="employee_skill_test")
@NamedQuery(name="EmployeeSkillTest.findAll", query="SELECT e FROM EmployeeSkillTest e")
public class EmployeeSkillTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int point;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	private Employee employee;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	private Skill skill;

	//bi-directional many-to-one association to EmployeeTestDetail
	@OneToMany(mappedBy="employeeSkillTest")
	private List<EmployeeTestDetail> employeeTestDetails;

	public EmployeeSkillTest() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoint() {
		return this.point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Skill getSkill() {
		return this.skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public List<EmployeeTestDetail> getEmployeeTestDetails() {
		return this.employeeTestDetails;
	}

	public void setEmployeeTestDetails(List<EmployeeTestDetail> employeeTestDetails) {
		this.employeeTestDetails = employeeTestDetails;
	}

	public EmployeeTestDetail addEmployeeTestDetail(EmployeeTestDetail employeeTestDetail) {
		getEmployeeTestDetails().add(employeeTestDetail);
		employeeTestDetail.setEmployeeSkillTest(this);

		return employeeTestDetail;
	}

	public EmployeeTestDetail removeEmployeeTestDetail(EmployeeTestDetail employeeTestDetail) {
		getEmployeeTestDetails().remove(employeeTestDetail);
		employeeTestDetail.setEmployeeSkillTest(null);

		return employeeTestDetail;
	}

}