package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalTransactionStatusRequest {

	@JsonProperty("TransactionID")
	private String transactionID;

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public InternalTransactionStatusRequest() {

	}

	public InternalTransactionStatusRequest(String transactionID) {
		this.transactionID = transactionID;
	}

	@Override
	public String toString() {
		return "InternalTransactionStatusRequest [transactionID=" + transactionID + "]";
	}
	
	
}
