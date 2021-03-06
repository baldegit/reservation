package com.balde.api.beans;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShowFromApi {
	
	private int nhits;
	
	@JsonProperty(value = "records")
	private List<Records> records = new ArrayList<Records>();

	public ShowFromApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShowFromApi(int nhits, List<Records> records) {
		super();
		this.nhits = nhits;
		this.records = records;
	}

	public int getNhits() {
		return nhits;
	}

	public void setNhits(int nhits) {
		this.nhits = nhits;
	}

	public List<Records> getRecords() {
		return records;
	}

	public void setRecords(List<Records> records) {
		this.records = records;
	}
	
	
}
