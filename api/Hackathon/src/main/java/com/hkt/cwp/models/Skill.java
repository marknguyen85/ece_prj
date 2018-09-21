package com.hkt.cwp.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the skill database table.
 * 
 */
@Entity
@Table(name="skill")
@NamedQuery(name="Skill.findAll", query="SELECT s FROM Skill s")
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to EmployeeSkillTest
	@OneToMany(mappedBy="skill")
	private List<EmployeeSkillTest> employeeSkillTests;

	//bi-directional many-to-one association to Technique
	@ManyToOne
	private Technique technique;

	//bi-directional many-to-one association to TestDetail
	@OneToMany(mappedBy="skill")
	private List<TestDetail> testDetails;

	public Skill() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EmployeeSkillTest> getEmployeeSkillTests() {
		return this.employeeSkillTests;
	}

	public void setEmployeeSkillTests(List<EmployeeSkillTest> employeeSkillTests) {
		this.employeeSkillTests = employeeSkillTests;
	}

	public EmployeeSkillTest addEmployeeSkillTest(EmployeeSkillTest employeeSkillTest) {
		getEmployeeSkillTests().add(employeeSkillTest);
		employeeSkillTest.setSkill(this);

		return employeeSkillTest;
	}

	public EmployeeSkillTest removeEmployeeSkillTest(EmployeeSkillTest employeeSkillTest) {
		getEmployeeSkillTests().remove(employeeSkillTest);
		employeeSkillTest.setSkill(null);

		return employeeSkillTest;
	}

	public Technique getTechnique() {
		return this.technique;
	}

	public void setTechnique(Technique technique) {
		this.technique = technique;
	}

	public List<TestDetail> getTestDetails() {
		return this.testDetails;
	}

	public void setTestDetails(List<TestDetail> testDetails) {
		this.testDetails = testDetails;
	}

	public TestDetail addTestDetail(TestDetail testDetail) {
		getTestDetails().add(testDetail);
		testDetail.setSkill(this);

		return testDetail;
	}

	public TestDetail removeTestDetail(TestDetail testDetail) {
		getTestDetails().remove(testDetail);
		testDetail.setSkill(null);

		return testDetail;
	}

}