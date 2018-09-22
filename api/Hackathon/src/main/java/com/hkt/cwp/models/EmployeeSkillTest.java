package com.hkt.cwp.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int point;

	@Temporal(TemporalType.TIMESTAMP)
	private Date starttime;

	//bi-directional many-to-one association to Employee
	@JsonIgnore
	@ManyToOne
	private Employee employee;

	//bi-directional many-to-one association to Skill
	@JsonIgnore
	@ManyToOne
	private Skill skill;

	//bi-directional many-to-one association to EmployeeTestDetail
	@JsonIgnore
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

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
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