package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimulateTransactionResponse {

	@JsonProperty("ResponseDescription")
	private String responseDescription;
	
	@JsonProperty("ResponseCode")
	private String responseCode;
	
	@JsonProperty("OriginatorCoversationID")
	private String originatorCoversationID;
	
	

	public String getresponseDescription() {
		return responseDescription;
	}

	public void setresponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getOriginatorCoversationID() {
		return originatorCoversationID;
	}

	public void setOriginatorCoversationID(String originatorCoversationID) {
		this.originatorCoversationID = originatorCoversationID;
	}

	public SimulateTransactionResponse() {

	}

	public SimulateTransactionResponse(String responseDescription, String responseCode,
			String originatorCoversationID) {
		this.responseDescription = responseDescription;
		this.responseCode = responseCode;
		this.originatorCoversationID = originatorCoversationID;
	}

	
	
}
