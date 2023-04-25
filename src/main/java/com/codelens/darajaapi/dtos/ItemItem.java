package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemItem {

	@JsonProperty("Value")
	private String value;
	
	@JsonProperty("Name")
	private String name;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemItem() {

	}

	public ItemItem(String value, String name) {
		this.value = value;
		this.name = name;
	}

	@Override
	public String toString() {
		return "ItemItem [value=" + value + ", name=" + name + "]";
	}
	
	
}
