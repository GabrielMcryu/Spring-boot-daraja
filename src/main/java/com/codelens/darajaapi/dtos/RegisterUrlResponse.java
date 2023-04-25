package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUrlResponse {

//	@JsonProperty("ConversationID")
//	private String conversationID;
	
	@JsonProperty("ResponseCode")
	private String responseCode;
	
	@JsonProperty("ResponseDescription")
	private String responseDescription;
	
	@JsonProperty("OriginatorCoversationID")
	private String originatorCoversationID;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getOriginatorCoversationID() {
		return originatorCoversationID;
	}

	public void setOriginatorConversationID(String originatorConversationID) {
		this.originatorCoversationID = originatorConversationID;
	}

	public RegisterUrlResponse() {
		
	}

	public RegisterUrlResponse(String responseCode, String responseDescription, String originatorCoversationID) {
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
		this.originatorCoversationID = originatorCoversationID;
	}

	@Override
	public String toString() {
		return "RegisterUrlResponse [responseCode=" + responseCode + ", responseDescription=" + responseDescription
				+ ", originatorCoversationID=" + originatorCoversationID + "]";
	}
	
	
}
