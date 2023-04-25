package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StkPushAsyncResponse {

	@JsonProperty("Body")
	private Body body;

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public StkPushAsyncResponse() {

	}

	public StkPushAsyncResponse(Body body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "StkPushAsyncResponse [body=" + body + "]";
	}
	
	
}
