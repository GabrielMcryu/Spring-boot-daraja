package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalStkPushRequest {

	@JsonProperty("TransactionType")
	private String transactionType;
	
	@JsonProperty("Amount")
	private String amount;
	
	@JsonProperty("CallBackURL")
	private String callBackURL;
	
	@JsonProperty("PhoneNumber")
	private String phoneNumber;
	
	@JsonProperty("PartyA")
	private String partyA;
	
	@JsonProperty("PartyB")
	private String partyB;
	
	@JsonProperty("AccountReference")
	private String accountReference;
	
	@JsonProperty("TransactionDesc")
	private String transactionDesc;
	
	@JsonProperty("BusinessShortCode")
	private String businessShortCode;
	
	@JsonProperty("Timestamp")
	private String timestamp;
	
	@JsonProperty("Password")
	private String password;

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCallBackURL() {
		return callBackURL;
	}

	public void setCallBackURL(String callBackURL) {
		this.callBackURL = callBackURL;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPartyA() {
		return partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public String getPartyB() {
		return partyB;
	}

	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}

	public String getAccountReference() {
		return accountReference;
	}

	public void setAccountReference(String accountReference) {
		this.accountReference = accountReference;
	}

	public String getTransactionDesc() {
		return transactionDesc;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}

	public String getBusinessShortCode() {
		return businessShortCode;
	}

	public void setBusinessShortCode(String businessShortCode) {
		this.businessShortCode = businessShortCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ExternalStkPushRequest() {

	}

	public ExternalStkPushRequest(String transactionType, String amount, String callBackURL, String phoneNumber,
			String partyA, String partyB, String accountReference, String transactionDesc, String businessShortCode,
			String timestamp, String password) {
		this.transactionType = transactionType;
		this.amount = amount;
		this.callBackURL = callBackURL;
		this.phoneNumber = phoneNumber;
		this.partyA = partyA;
		this.partyB = partyB;
		this.accountReference = accountReference;
		this.transactionDesc = transactionDesc;
		this.businessShortCode = businessShortCode;
		this.timestamp = timestamp;
		this.password = password;
	}

	@Override
	public String toString() {
		return "ExternalStkPushRequest [transactionType=" + transactionType + ", amount=" + amount + ", callBackURL="
				+ callBackURL + ", phoneNumber=" + phoneNumber + ", partyA=" + partyA + ", partyB=" + partyB
				+ ", accountReference=" + accountReference + ", transactionDesc=" + transactionDesc
				+ ", businessShortCode=" + businessShortCode + ", timestamp=" + timestamp + ", password=" + password
				+ "]";
	}
	
	
}


