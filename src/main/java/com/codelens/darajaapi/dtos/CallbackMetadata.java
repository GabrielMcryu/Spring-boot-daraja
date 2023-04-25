package com.codelens.darajaapi.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallbackMetadata {

	@JsonProperty("Item")
	private List<ItemItem> item;

	public List<ItemItem> getItem() {
		return item;
	}

	public void setItem(List<ItemItem> item) {
		this.item = item;
	}

	public CallbackMetadata() {

	}

	public CallbackMetadata(List<ItemItem> item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "CallbackMetadata [item=" + item + "]";
	}
	
	
}
