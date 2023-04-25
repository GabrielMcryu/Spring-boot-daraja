package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckAccountBalanceRequest {

	@JsonProperty("QueueTimeOutURL")
	private String queueTimeOutURL;
	
	@JsonProperty("Initiator")
	private String initiator;
	
	@JsonProperty("Remarks")
	private String remarks;
	
	@JsonProperty("SecurityCredential")
	private String securityCredential;
	
	@JsonProperty("CommandID")
	private String commandID;
	
	@JsonProperty("PartyA")
	private String partyA;
	
	@JsonProperty("IdentifierType")
	private String identifierType;
	
	@JsonProperty("ResultURL")
	private String resultURL;

	public String getQueueTimeOutURL() {
		return queueTimeOutURL;
	}

	public void setQueueTimeOutURL(String queueTimeOutURL) {
		this.queueTimeOutURL = queueTimeOutURL;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSecurityCredential() {
		return securityCredential;
	}

	public void setSecurityCredential(String securityCredential) {
		this.securityCredential = securityCredential;
	}

	public String getCommandID() {
		return commandID;
	}

	public void setCommandID(String commandID) {
		this.commandID = commandID;
	}

	public String getPartyA() {
		return partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public String getIdentifierType() {
		return identifierType;
	}

	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}

	public CheckAccountBalanceRequest() {

	}

	public CheckAccountBalanceRequest(String queueTimeOutURL, String initiator, String remarks,
			String securityCredential, String commandID, String partyA, String identifierType, String resultURL) {
		this.queueTimeOutURL = queueTimeOutURL;
		this.initiator = initiator;
		this.remarks = remarks;
		this.securityCredential = securityCredential;
		this.commandID = commandID;
		this.partyA = partyA;
		this.identifierType = identifierType;
		this.resultURL = resultURL;
	}

	@Override
	public String toString() {
		return "CheckAccountBalanceRequest [queueTimeOutURL=" + queueTimeOutURL + ", initiator=" + initiator
				+ ", remarks=" + remarks + ", securityCredential=" + securityCredential + ", commandID=" + commandID
				+ ", partyA=" + partyA + ", identifierType=" + identifierType + ", resultURL=" + resultURL + "]";
	}
	
	
}
