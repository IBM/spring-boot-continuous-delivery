package com.ibm.account.demo;

import java.util.Date;

public class Account {

	public Account(String name) {
		this.name = name;
	}

	private String name;
	private String date = new Date().toString();

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
