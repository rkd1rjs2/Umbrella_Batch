package com.rainbow.um.dto;

import java.io.Serializable;

public class BobDto implements Serializable{

	private static final long serialVersionUID = 8272312149823739684L;
	private String book_name;
	private String cu;
	public BobDto() {
		super();
	}
	public BobDto(String book_name, String cu) {
		super();
		this.book_name = book_name;
		this.cu = cu;
	}
	@Override
	public String toString() {
		return "BobDto [book_name=" + book_name + ", cu=" + cu + "]";
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getCu() {
		return cu;
	}
	public void setCu(String cu) {
		this.cu = cu;
	}
	
	
	
}
