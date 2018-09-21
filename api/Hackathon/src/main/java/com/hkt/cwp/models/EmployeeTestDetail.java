package com.hkt.cwp.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the employee_test_detail database table.
 * 
 */
@Entity
@Table(name="employee_test_detail")
@NamedQuery(name="EmployeeTestDetail.findAll", query="SELECT e FROM EmployeeTestDetail e")
public class EmployeeTestDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String answer1;

	private String answer2;

	private String answer3;

	private String answer4;

	private String answer5;

	private int choice;

	@Column(name="correct_answer")
	private int correctAnswer;

	@Column(name="is_correct")
	private int isCorrect;

	private String question;

	//bi-directional many-to-one association to EmployeeSkillTest
	@ManyToOne
	@JoinColumn(name="est_id")
	private EmployeeSkillTest employeeSkillTest;

	public EmployeeTestDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer1() {
		return this.answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return this.answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return this.answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return this.answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public String getAnswer5() {
		return this.answer5;
	}

	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}

	public int getChoice() {
		return this.choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public int getCorrectAnswer() {
		return this.correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public int getIsCorrect() {
		return this.isCorrect;
	}

	public void setIsCorrect(int isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public EmployeeSkillTest getEmployeeSkillTest() {
		return this.employeeSkillTest;
	}

	public void setEmployeeSkillTest(EmployeeSkillTest employeeSkillTest) {
		this.employeeSkillTest = employeeSkillTest;
	}

}