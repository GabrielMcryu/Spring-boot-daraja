package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalStkPushRequest {

	@JsonProperty("Amount")
	private String amount;
	
	@JsonProperty("PhoneNumber")
	private String phoneNumber;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public InternalStkPushRequest() {

	}

	public InternalStkPushRequest(String amount, String phoneNumber) {
		this.amount = amount;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "InternalStkPushRequest [amount=" + amount + ", phoneNumber=" + phoneNumber + "]";
	}
	
	
}
