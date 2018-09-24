/**
 * 
 */
package com.hkt.cwp.response;

/**
 * @author HP
 *
 */
public class RankEmployee {

	private Integer employee_id;
	private String employy_name;
	private String code;
	private Integer skill_id;
	private String skill_name;
	private Integer max_point;
	private Integer rank;
	
	public RankEmployee() {
		// TODO Auto-generated constructor stub
	}
	public RankEmployee(Integer employee_id, String employy_name, Integer skill_id, String skill_name, Integer max_point) {
		this.employee_id = employee_id;
		this.employy_name = employy_name;
		this.skill_id = skill_id;
		this.skill_name = skill_name;
		this.max_point = max_point;
	}

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
	 * @return the employy_name
	 */
	public String getEmployy_name() {
		return employy_name;
	}

	/**
	 * @param employy_name the employy_name to set
	 */
	public void setEmployy_name(String employy_name) {
		this.employy_name = employy_name;
	}

	/**
	 * @return the skill_id
	 */
	public Integer getSkill_id() {
		return skill_id;
	}

	/**
	 * @param skill_id the skill_id to set
	 */
	public void setSkill_id(Integer skill_id) {
		this.skill_id = skill_id;
	}

	/**
	 * @return the skill_name
	 */
	public String getSkill_name() {
		return skill_name;
	}

	/**
	 * @param skill_name the skill_name to set
	 */
	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}

	/**
	 * @return the max_point
	 */
	public Integer getMax_point() {
		return max_point;
	}

	/**
	 * @param max_point the max_point to set
	 */
	public void setMax_point(Integer max_point) {
		this.max_point = max_point;
	}

	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
