/**
 * 
 */
package com.hkt.cwp.response;

import java.util.List;

import com.hkt.cwp.models.Skill;

/**
 * @author CaoTT
 *
 */
public class TechniqueEmp {

	private Integer employee_id;
	private String employee_name;
	private Integer technique_id;
	private String technique_name;
	private List<SkillTestResponse> skills;
	/**
	 * @return the employee_id
	 */
	public Integer getEmployee_id() {
		return employee_id;
	}
	/**
	 * @param employee_id the employee_id to set
	 */
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	/**
	 * @return the employee_name
	 */
	public String getEmployee_name() {
		return employee_name;
	}
	/**
	 * @param employee_name the employee_name to set
	 */
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	/**
	 * @return the technique_id
	 */
	public Integer getTechnique_id() {
		return technique_id;
	}
	/**
	 * @param technique_id the technique_id to set
	 */
	public void setTechnique_id(Integer technique_id) {
		this.technique_id = technique_id;
	}
	/**
	 * @return the technique_name
	 */
	public String getTechnique_name() {
		return technique_name;
	}
	/**
	 * @param technique_name the technique_name to set
	 */
	public void setTechnique_name(String technique_name) {
		this.technique_name = technique_name;
	}
	
	/**
	 * @return the skills
	 */
	public List<SkillTestResponse> getSkills() {
		return skills;
	}
	/**
	 * @param skills the skills to set
	 */
	public void setSkills(List<SkillTestResponse> skills) {
		this.skills = skills;
	}
	
}
