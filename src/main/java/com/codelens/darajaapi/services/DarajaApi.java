package com.codelens.darajaapi.services;

import com.codelens.darajaapi.dtos.AccessTokenResponse;
import com.codelens.darajaapi.dtos.CommonSyncResponse;
import com.codelens.darajaapi.dtos.InternalB2CTransactionRequest;
import com.codelens.darajaapi.dtos.InternalStkPushRequest;
import com.codelens.darajaapi.dtos.InternalTransactionStatusRequest;
import com.codelens.darajaapi.dtos.RegisterUrlResponse;
import com.codelens.darajaapi.dtos.SimulateTransactionRequest;
import com.codelens.darajaapi.dtos.SimulateTransactionResponse;
import com.codelens.darajaapi.dtos.StkPushSyncResponse;
import com.codelens.darajaapi.dtos.TransactionStatusSyncResponse;

public interface DarajaApi {

	AccessTokenResponse getAccessToken();
	
	RegisterUrlResponse registerUrl();
	
	SimulateTransactionResponse simulateC2BTransaction(SimulateTransactionRequest simulateTransactionRequest);
	
	CommonSyncResponse performB2CTransaction(InternalB2CTransactionRequest internalB2CTransactionRequest);
//	B2CTransactionSyncResponse performB2CTransaction(InternalB2CTransactionRequest internalB2CTransactionRequest);

	TransactionStatusSyncResponse getTransactionResult(InternalTransactionStatusRequest internalTransactionStatusRequest);
	
	CommonSyncResponse checkAccountBalance();
	
	StkPushSyncResponse performStkPushTransaction(InternalStkPushRequest internalStkPushRequest);
}
