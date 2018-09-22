package com.hkt.cwp.response;

public class EmployeeCapacity {
	private Integer employee_id;
	private Integer skill_id;
	private String employee_name;
	private String skill_name;
	private String technique_name;
	private String point;
	
	public EmployeeCapacity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeCapacity(Integer employee_id, Integer skill_id, String employee_name, String skill_name,
			String technique_name, String point) {
		super();
		this.employee_id = employee_id;
		this.skill_id = skill_id;
		this.employee_name = employee_name;
		this.skill_name = skill_name;
		this.technique_name = technique_name;
		this.point = point;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(Integer skill_id) {
		this.skill_id = skill_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getSkill_name() {
		return skill_name;
	}
	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}
	public String getTechnique_name() {
		return technique_name;
	}
	public void setTechnique_name(String technique_name) {
		this.technique_name = technique_name;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	

}
