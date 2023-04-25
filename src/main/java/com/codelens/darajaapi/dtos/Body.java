package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Body {

	@JsonProperty("stkCallback")
	private StkCallback stkCallback;

	public StkCallback getStkCallback() {
		return stkCallback;
	}

	public void setStkCallback(StkCallback stkCallback) {
		this.stkCallback = stkCallback;
	}
	
	public Body() {

	}

	public Body(StkCallback stkCallback) {
		this.stkCallback = stkCallback;
	}

	@Override
	public String toString() {
		return "Body [stkCallback=" + stkCallback + "]";
	}
	
	
}
