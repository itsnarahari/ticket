package com.agilecrm.model;

import java.util.Date;



public class Ticket  {
	
	private int ticketId;
	private String name;
	private String email;
	private long mobile;
	private String problem_type;	
	private String problem_desc;
	private String dept;
	private String assignee;
	private String status;
	private String resolution;
	private String problemReport;
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getProblem_type() {
		return problem_type;
	}
	public void setProblem_type(String problem_type) {
		this.problem_type = problem_type;
	}
	public String getProblem_desc() {
		return problem_desc;
	}
	public void setProblem_desc(String problem_desc) {
		this.problem_desc = problem_desc;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getProblemReport() {
		return problemReport;
	}
	public void setProblemReport(String problemReport) {
		this.problemReport = problemReport;
	}
	
	
	
	
	
	
}
