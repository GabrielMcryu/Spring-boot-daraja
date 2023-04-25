package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class B2CTransactionAsyncResponse {

	@JsonProperty("Result")
	private Result result;

	public Result getResult() {
		System.out.println(result);
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public B2CTransactionAsyncResponse() {

	}

	public B2CTransactionAsyncResponse(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "B2CTransactionAsyncResponse [result=" + result + "]";
	}
	
	
}
