package com.hkt.cwp.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the technique database table.
 * 
 */
@Entity
@Table(name="technique")
@NamedQuery(name="Technique.findAll", query="SELECT t FROM Technique t")
public class Technique implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to Employee
	@OneToMany(mappedBy="technique")
	private List<Employee> employees;

	//bi-directional many-to-one association to Skill
	@OneToMany(mappedBy="technique")
	private List<Skill> skills;

	public Technique() {
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

	public List<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee addEmployee(Employee employee) {
		getEmployees().add(employee);
		((Employee) employee).setTechnique(this);

		return employee;
	}

	public Employee removeEmployee(Employee employee) {
		getEmployees().remove(employee);
		employee.setTechnique(null);

		return employee;
	}

	public List<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Skill addSkill(Skill skill) {
		getSkills().add(skill);
		skill.setTechnique(this);

		return skill;
	}

	public Skill removeSkill(Skill skill) {
		getSkills().remove(skill);
		skill.setTechnique(null);

		return skill;
	}

}