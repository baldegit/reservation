package com.balde.beans;

public class CheckedObject <T>{
	
	private T object;
	private int isChecked;
	
	public CheckedObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckedObject(T object) {
		super();
		this.object = object;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public int getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}
}
