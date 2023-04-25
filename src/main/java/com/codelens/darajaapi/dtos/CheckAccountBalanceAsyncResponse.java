package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckAccountBalanceAsyncResponse {

	@JsonProperty("Result")
	private Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public CheckAccountBalanceAsyncResponse() {

	}

	public CheckAccountBalanceAsyncResponse(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "CheckAccountBalanceAsyncResponse [result=" + result + "]";
	}
	
	
}
