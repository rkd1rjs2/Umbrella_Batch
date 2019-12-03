package com.rainbow.um.dto;

public class SmsData {

	private String user_phone; 
	private String user_email; 
	private String book_name;
	
	public SmsData() {
	}
	
	public SmsData(String user_phone, String user_email, String book_name) {
		super();
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.book_name = book_name;
	}

	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	@Override
	public String toString() {
		return "smsData [user_phone=" + user_phone + ", user_email=" + user_email + ", book_name=" + book_name + "]";
	}
	
	
}
