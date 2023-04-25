package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionStatusAsyncResponse {

	@JsonProperty("Result")
	private Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public TransactionStatusAsyncResponse() {

	}

	public TransactionStatusAsyncResponse(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "TransactionStatusAsyncResponse [result=" + result + "]";
	}
	
	
}
