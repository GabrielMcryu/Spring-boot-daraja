package com.codelens.darajaapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StkCallback {

	@JsonProperty("MerchantRequestID")
	private String merchantRequestID;
	
	@JsonProperty("CheckoutRequestID")
	private String checkoutRequestID;
	
	@JsonProperty("ResultDesc")
	private String resultDesc;
	
	@JsonProperty("ResultCode")
	private int resultCode;
	
	@JsonProperty("CallbackMetadata")
	private CallbackMetadata callbackMetadata;

	public String getMerchantRequestID() {
		return merchantRequestID;
	}

	public void setMerchantRequestID(String merchantRequestID) {
		this.merchantRequestID = merchantRequestID;
	}

	public String getCheckoutRequestID() {
		return checkoutRequestID;
	}

	public void setCheckoutRequestID(String checkoutRequestID) {
		this.checkoutRequestID = checkoutRequestID;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public CallbackMetadata getCallbackMetadata() {
		return callbackMetadata;
	}

	public void setCallbackMetadata(CallbackMetadata callbackMetadata) {
		this.callbackMetadata = callbackMetadata;
	}

	public StkCallback() {

	}

	public StkCallback(String merchantRequestID, String checkoutRequestID, String resultDesc, int resultCode,
			CallbackMetadata callbackMetadata) {
		this.merchantRequestID = merchantRequestID;
		this.checkoutRequestID = checkoutRequestID;
		this.resultDesc = resultDesc;
		this.resultCode = resultCode;
		this.callbackMetadata = callbackMetadata;
	}

	@Override
	public String toString() {
		return "StkCallback [merchantRequestID=" + merchantRequestID + ", checkoutRequestID=" + checkoutRequestID
				+ ", resultDesc=" + resultDesc + ", resultCode=" + resultCode + ", callbackMetadata=" + callbackMetadata
				+ "]";
	}
	
	
}
