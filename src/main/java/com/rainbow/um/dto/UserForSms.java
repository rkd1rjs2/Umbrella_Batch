package com.rainbow.um.dto;

import java.io.Serializable;

public class UserForSms implements Serializable{

	private static final long serialVersionUID = 4218213466182361830L;
	private String user_phone;
	private String book_name;
	@Override
	public String toString() {
		return "UserForSms [user_phone=" + user_phone + ", book_name=" + book_name + "]";
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public UserForSms(String user_phone, String book_name) {
		super();
		this.user_phone = user_phone;
		this.book_name = book_name;
	}
	
	public UserForSms() {
		// TODO Auto-generated constructor stub
	}
}
