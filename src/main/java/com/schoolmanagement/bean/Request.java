package com.schoolmanagement.bean;

import java.sql.Timestamp;

public class Request {

	private int id;
	private String sid;
	private String type;
	private String description;
	private Timestamp req_date;
	private String status;
	
	public Request(int id, String sid, String type, String description) {
		super();
		this.id = id;
		this.sid = sid;
		this.type = type;
		this.description = description;
	}
	public Request(String sid, String type, String description) {
		super();
		this.sid = sid;
		this.type = type;
		this.description = description;
		
	}
	public Request(int id, String sid, String type, String description, Timestamp req_date, String status) {
		super();
		this.id = id;
		this.sid = sid;
		this.type = type;
		this.description = description;
		this.req_date = req_date;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getReq_date() {
		return req_date;
	}
	public void setReq_date(Timestamp req_date) {
		this.req_date = req_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
