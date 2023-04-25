package com.codelens.darajaapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codelens.darajaapi.dtos.AccessTokenResponse;
import com.codelens.darajaapi.dtos.AcknowledgeResponse;
import com.codelens.darajaapi.dtos.B2CTransactionAsyncResponse;
import com.codelens.darajaapi.dtos.CommonSyncResponse;
import com.codelens.darajaapi.dtos.InternalB2CTransactionRequest;
import com.codelens.darajaapi.dtos.InternalStkPushRequest;
import com.codelens.darajaapi.dtos.InternalTransactionStatusRequest;
import com.codelens.darajaapi.dtos.MpesaValidationResponse;
import com.codelens.darajaapi.dtos.RegisterUrlResponse;
import com.codelens.darajaapi.dtos.SimulateTransactionRequest;
import com.codelens.darajaapi.dtos.SimulateTransactionResponse;
import com.codelens.darajaapi.dtos.StkPushAsyncResponse;
import com.codelens.darajaapi.dtos.StkPushSyncResponse;
import com.codelens.darajaapi.dtos.TransactionStatusSyncResponse;
import com.codelens.darajaapi.services.DarajaApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("mobile-money")
public class MpesaController {
	private final DarajaApi darajaApi;
	private final AcknowledgeResponse acknowledgeResponse;
	private final ObjectMapper objectMapper;
		
	public MpesaController(DarajaApi darajaApi, AcknowledgeResponse acknowledgeResponse, ObjectMapper objectMapper) {
		this.darajaApi = darajaApi;
		this.acknowledgeResponse = acknowledgeResponse;
		this.objectMapper = objectMapper;
	}

	@GetMapping(path = "/token", produces = "application/json")
	public ResponseEntity<AccessTokenResponse> getAccessToken() {
		return ResponseEntity.ok(darajaApi.getAccessToken());
	}
	
	@PostMapping(path = "/register-url", produces = "application/json")
	public ResponseEntity<RegisterUrlResponse> registerUrl() {
		return ResponseEntity.ok(darajaApi.registerUrl());
	}
	
	@PostMapping(path = "/validation", produces = "application/json")
	public ResponseEntity<AcknowledgeResponse> mpesaValidation(@RequestBody MpesaValidationResponse mpesaValidationResponse) {
		return ResponseEntity.ok(acknowledgeResponse);
	}
	
	@PostMapping(path = "/simulate-c2b", produces = "application/json")
	public ResponseEntity<SimulateTransactionResponse> simulateB2CTransaction(@RequestBody SimulateTransactionRequest simulateTransactionRequest) {
		return ResponseEntity.ok(darajaApi.simulateC2BTransaction(simulateTransactionRequest));
	}
	
	// === B2C Transaction Region ====
	
	@PostMapping(path = "/transaction-result", produces = "application/json")
	public ResponseEntity<AcknowledgeResponse> b2cTransactionAsyncResults(@RequestBody B2CTransactionAsyncResponse b2CTransactionAsyncResponse) throws JsonProcessingException {
		Logger log = LoggerFactory.getLogger(this.getClass());
	    log.info("============ B2C Transaction Response =============");
	    log.info(objectMapper.writeValueAsString(b2CTransactionAsyncResponse));
	    return ResponseEntity.ok(acknowledgeResponse);
	}
	
	
	@PostMapping(path = "/b2c-queue-timeout", produces = "application/json")
	public ResponseEntity<AcknowledgeResponse> queueTimeout(@RequestBody Object object) {
		return ResponseEntity.ok(acknowledgeResponse);
	}
	
//	@PostMapping(path = "/b2c-transaction", produces = "application/json")
//	public ResponseEntity<B2CTransactionSyncResponse> performB2CTransaction(@RequestBody InternalB2CTransactionRequest internalB2CTransactionRequest) {
//		return ResponseEntity.ok(darajaApi.performB2CTransaction(internalB2CTransactionRequest));
//	}
	
	@PostMapping(path = "/b2c-transaction", produces = "application/json")
	public ResponseEntity<CommonSyncResponse> performB2CTransaction(@RequestBody InternalB2CTransactionRequest internalB2CTransactionRequest) {
		return ResponseEntity.ok(darajaApi.performB2CTransaction(internalB2CTransactionRequest));
	}
	
	@PostMapping(path = "/simulate-transaction-result", produces = "application/json")
	public ResponseEntity<TransactionStatusSyncResponse> getTransactionStatusResult(@RequestBody InternalTransactionStatusRequest internalTransactionStatusRequest) {
		return ResponseEntity.ok(darajaApi.getTransactionResult(internalTransactionStatusRequest));
	}
	
	@GetMapping(path = "/check-account-balance", produces = "application/json")
	public ResponseEntity<CommonSyncResponse> checkAccountBalance() {
		return ResponseEntity.ok(darajaApi.checkAccountBalance());
	}
	
	@PostMapping(path = "/stk-transaction-request", produces = "application/json")
	public ResponseEntity<StkPushSyncResponse> performStkPushTransaction(@RequestBody InternalStkPushRequest internalStkPushRequest) {
		return ResponseEntity.ok(darajaApi.performStkPushTransaction(internalStkPushRequest));
	}
	
	@PostMapping(path = "/stk-transaction-result", produces = "application/json")
	public ResponseEntity<AcknowledgeResponse> acknowledgeStkPushResponse(@RequestBody StkPushAsyncResponse stkPushAsyncResponse) throws JsonProcessingException {
		Logger log = LoggerFactory.getLogger(this.getClass());
	    log.info("============ STK Push Async Response =============");
	    log.info(objectMapper.writeValueAsString(stkPushAsyncResponse));
	    return ResponseEntity.ok(acknowledgeResponse);
	}
}
