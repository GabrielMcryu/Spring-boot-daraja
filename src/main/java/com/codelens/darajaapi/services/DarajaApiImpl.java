package com.codelens.darajaapi.services;


import static com.codelens.darajaapi.utils.Constants.AUTHORIZATION_HEADER_STRING;
import static com.codelens.darajaapi.utils.Constants.BASIC_AUTH_STRING;
import static com.codelens.darajaapi.utils.Constants.BEARER_AUTH_STRING;
import static com.codelens.darajaapi.utils.Constants.CACHE_CONTROL_HEADER;
import static com.codelens.darajaapi.utils.Constants.CACHE_CONTROL_HEADER_VALUE;
import static com.codelens.darajaapi.utils.Constants.JSON_MEDIA_TYPE;
import static com.codelens.darajaapi.utils.Constants.SHORT_CODE_IDENTIFIER;
import static com.codelens.darajaapi.utils.Constants.TRANSACTION_STATUS_QUERY_COMMAND;
import static com.codelens.darajaapi.utils.Constants.TRANSACTION_STATUS_VALUE;

import java.io.IOException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.codelens.darajaapi.config.MpesaConfiguration;
import com.codelens.darajaapi.dtos.AccessTokenResponse;
import com.codelens.darajaapi.dtos.B2CTransactionRequest;
import com.codelens.darajaapi.dtos.CheckAccountBalanceRequest;
import com.codelens.darajaapi.dtos.CommonSyncResponse;
import com.codelens.darajaapi.dtos.ExternalStkPushRequest;
import com.codelens.darajaapi.dtos.InternalB2CTransactionRequest;
import com.codelens.darajaapi.dtos.InternalStkPushRequest;
import com.codelens.darajaapi.dtos.InternalTransactionStatusRequest;
import com.codelens.darajaapi.dtos.RegisterUrlRequest;
import com.codelens.darajaapi.dtos.RegisterUrlResponse;
import com.codelens.darajaapi.dtos.SimulateTransactionRequest;
import com.codelens.darajaapi.dtos.SimulateTransactionResponse;
import com.codelens.darajaapi.dtos.StkPushSyncResponse;
import com.codelens.darajaapi.dtos.TransactionStatusRequest;
import com.codelens.darajaapi.dtos.TransactionStatusSyncResponse;
import com.codelens.darajaapi.utils.Constants;
import com.codelens.darajaapi.utils.HelperUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class DarajaApiImpl implements DarajaApi {

	private final MpesaConfiguration mpesaConfiguration;
	private final OkHttpClient okHttpClient;
	private final ObjectMapper objectMapper;
	
	public DarajaApiImpl(MpesaConfiguration mpesaConfiguration, OkHttpClient okHttpClient, ObjectMapper objectMapper) {
		this.mpesaConfiguration = mpesaConfiguration;
		this.okHttpClient = okHttpClient;
		this.objectMapper = objectMapper;
	}


    

	@Override
	public AccessTokenResponse getAccessToken() {
		 // get the Base64 rep of consumerKey + ":" + consumerSecret
        String encodedCredentials = HelperUtility.toBase64String(String.format("%s:%s", mpesaConfiguration.getConsumerKey(),
                mpesaConfiguration.getConsumerSecret()));

        Request request = new Request.Builder()
                .url(String.format("%s?grant_type=%s", mpesaConfiguration.getOauthEndpoint(), mpesaConfiguration.getGrantType()))
                .get()
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BASIC_AUTH_STRING, encodedCredentials))
                .addHeader(CACHE_CONTROL_HEADER, CACHE_CONTROL_HEADER_VALUE)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            assert response.body() != null;

            // use Jackson to Decode the ResponseBody ...
            return objectMapper.readValue(response.body().string(), AccessTokenResponse.class);
        } catch (IOException e) {
        	LoggerFactory.getLogger(DarajaApiImpl.class).error("Could not get access token. -> %s", e.getLocalizedMessage());
            return null;
        }
	}


	@Override
	public RegisterUrlResponse registerUrl() {
		AccessTokenResponse accessTokenResponse = getAccessToken();
		
		RegisterUrlRequest registerUrlRequest = new RegisterUrlRequest();
		registerUrlRequest.setConfirmationURL(mpesaConfiguration.getConfirmationURL());
		registerUrlRequest.setResponseType(mpesaConfiguration.getResponseType());
		registerUrlRequest.setShortCode(mpesaConfiguration.getShortCode());
		registerUrlRequest.setValidationURL(mpesaConfiguration.getValidationURL());
		
		@SuppressWarnings("deprecation")
		RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJson(registerUrlRequest)));
		
		Request request = new Request.Builder()
				.url(mpesaConfiguration.getRegisterUrlEndpoint())
				.post(body)
				.addHeader("Authorization", String.format("%s %s", BEARER_AUTH_STRING, accessTokenResponse.getAccessToken()))
				.build();
		
		try {
			Response response = okHttpClient.newCall(request).execute();
			
			assert response.body() != null;
			
			return objectMapper.readValue(response.body().string(), RegisterUrlResponse.class);
			
		} catch (IOException e) {
			String errorMessage = String.format("Could not register url -> %s", e.getMessage());
		    LoggerFactory.getLogger(DarajaApiImpl.class).error(errorMessage, e);
		    return null;
        }
		
	}




	@Override
	public SimulateTransactionResponse simulateC2BTransaction(SimulateTransactionRequest simulateTransactionRequest) {
		AccessTokenResponse accessTokenReponse = getAccessToken();
		
		@SuppressWarnings("deprecation")
		RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJson(simulateTransactionRequest)));
		
		Request request = new Request.Builder()
				.url(mpesaConfiguration.getSimulateTransactionEndpoint())
				.post(body)
				.addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, accessTokenReponse.getAccessToken()))
				.build();
		
		System.out.println(request);
		
		try {
			Response response = okHttpClient.newCall(request).execute();
			assert response.body() != null;
			
			System.out.println(response);
			return objectMapper.readValue(response.body().string(), SimulateTransactionResponse.class);
		} catch (IOException e) {
        	LoggerFactory.getLogger(DarajaApiImpl.class).error("Could not simulate C2B transaction -> {}", e.getLocalizedMessage());
            return null;
        }
		
		
	}




//	@Override
//	public B2CTransactionSyncResponse performB2CTransaction(
//			InternalB2CTransactionRequest internalB2CTransactionRequest) {
//		AccessTokenResponse accessTokenResponse = getAccessToken();
//		Logger logger = LoggerFactory.getLogger(DarajaApiImpl.class);
//		logger.info("Access Token: {}", accessTokenResponse.getAccessToken());
//
//        B2CTransactionRequest b2CTransactionRequest = new B2CTransactionRequest();
//
//        b2CTransactionRequest.setCommandID(internalB2CTransactionRequest.getCommandID());
//        b2CTransactionRequest.setAmount(internalB2CTransactionRequest.getAmount());
//        b2CTransactionRequest.setPartyB(internalB2CTransactionRequest.getPartyB());
//        b2CTransactionRequest.setRemarks(internalB2CTransactionRequest.getRemarks());
//        b2CTransactionRequest.setOccassion(internalB2CTransactionRequest.getOccassion());
//
//        // get the security credentials ...
//        try {
//			b2CTransactionRequest.setSecurityCredential(HelperUtility.getSecurityCredentials(mpesaConfiguration.getB2cInitiatorPassword()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        logger.info("Security Creds: {}", b2CTransactionRequest.getSecurityCredential());
//
//        // set the result url ...
//        b2CTransactionRequest.setResultURL(mpesaConfiguration.getB2cResultUrl());
//        b2CTransactionRequest.setQueueTimeOutURL(mpesaConfiguration.getB2cQueueTimeoutUrl());
//        b2CTransactionRequest.setInitiatorName(mpesaConfiguration.getB2cInitiatorName());
//        b2CTransactionRequest.setPartyA(mpesaConfiguration.getShortCode());
//
//        
//        
//        @SuppressWarnings("deprecation")
//		RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
//                Objects.requireNonNull(HelperUtility.toJson(b2CTransactionRequest)));
//
//        Request request = new Request.Builder()
//                .url(mpesaConfiguration.getB2cTransactionEndpoint())
//                .post(body)
//                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, accessTokenResponse.getAccessToken()))
//                .build();
//
//        try {
//            Response response = okHttpClient.newCall(request).execute();
//            
////            System.out.println(b2CTransactionRequest);
//
//            assert response.body() != null;
//
//            return objectMapper.readValue(response.body().string(), B2CTransactionSyncResponse.class);
//        } catch (IOException e) {
//        	logger.error("Could not perform B2C transaction ->{}", e.getLocalizedMessage());
//            return null;
//        }
//
//
//	}

	@Override
	public CommonSyncResponse performB2CTransaction(
			InternalB2CTransactionRequest internalB2CTransactionRequest) {
		AccessTokenResponse accessTokenResponse = getAccessToken();
		Logger logger = LoggerFactory.getLogger(DarajaApiImpl.class);
		logger.info("Access Token: {}", accessTokenResponse.getAccessToken());

        B2CTransactionRequest b2CTransactionRequest = new B2CTransactionRequest();

        b2CTransactionRequest.setCommandID(internalB2CTransactionRequest.getCommandID());
        b2CTransactionRequest.setAmount(internalB2CTransactionRequest.getAmount());
        b2CTransactionRequest.setPartyB(internalB2CTransactionRequest.getPartyB());
        b2CTransactionRequest.setRemarks(internalB2CTransactionRequest.getRemarks());
        b2CTransactionRequest.setOccassion(internalB2CTransactionRequest.getOccassion());

        // get the security credentials ...
        try {
			b2CTransactionRequest.setSecurityCredential(HelperUtility.getSecurityCredentials(mpesaConfiguration.getB2cInitiatorPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        logger.info("Security Creds: {}", b2CTransactionRequest.getSecurityCredential());

        // set the result url ...
        b2CTransactionRequest.setResultURL(mpesaConfiguration.getB2cResultUrl());
        b2CTransactionRequest.setQueueTimeOutURL(mpesaConfiguration.getB2cQueueTimeoutUrl());
        b2CTransactionRequest.setInitiatorName(mpesaConfiguration.getB2cInitiatorName());
        b2CTransactionRequest.setPartyA(mpesaConfiguration.getShortCode());

        
        
        @SuppressWarnings("deprecation")
		RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
                Objects.requireNonNull(HelperUtility.toJson(b2CTransactionRequest)));

        Request request = new Request.Builder()
                .url(mpesaConfiguration.getB2cTransactionEndpoint())
                .post(body)
                .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, accessTokenResponse.getAccessToken()))
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            
//            System.out.println(b2CTransactionRequest);

            assert response.body() != null;

            return objectMapper.readValue(response.body().string(), CommonSyncResponse.class);
        } catch (IOException e) {
        	logger.error("Could not perform B2C transaction ->{}", e.getLocalizedMessage());
            return null;
        }


	}


	@Override
	public TransactionStatusSyncResponse getTransactionResult(
			InternalTransactionStatusRequest internalTransactionStatusRequest) {

		Logger logger = LoggerFactory.getLogger(DarajaApiImpl.class);
		
		TransactionStatusRequest transactionStatusRequest = new TransactionStatusRequest();
	    transactionStatusRequest.setTransactionID(internalTransactionStatusRequest.getTransactionID());
	
	    transactionStatusRequest.setInitiator(mpesaConfiguration.getB2cInitiatorName());
	    try {
			transactionStatusRequest.setSecurityCredential(HelperUtility.getSecurityCredentials(mpesaConfiguration.getB2cInitiatorPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    transactionStatusRequest.setCommandID(TRANSACTION_STATUS_QUERY_COMMAND);
	    transactionStatusRequest.setPartyA(mpesaConfiguration.getShortCode());
	    transactionStatusRequest.setIdentifierType(SHORT_CODE_IDENTIFIER);
	    transactionStatusRequest.setResultURL(mpesaConfiguration.getB2cResultUrl());
	    transactionStatusRequest.setQueueTimeOutURL(mpesaConfiguration.getB2cQueueTimeoutUrl());
	    transactionStatusRequest.setRemarks(TRANSACTION_STATUS_VALUE);
	    transactionStatusRequest.setOccasion(TRANSACTION_STATUS_VALUE);
	
	    AccessTokenResponse accessTokenResponse = getAccessToken();
	
	    @SuppressWarnings("deprecation")
		RequestBody body = RequestBody.create(JSON_MEDIA_TYPE,
	            Objects.requireNonNull(HelperUtility.toJson(transactionStatusRequest)));
	
	    Request request = new Request.Builder()
	            .url(mpesaConfiguration.getTransactionResultUrl())
	            .post(body)
	            .addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, accessTokenResponse.getAccessToken()))
	            .build();
	
		try {
		    Response response = okHttpClient.newCall(request).execute();
		    assert response.body() != null;
		    // use Jackson to Decode the ResponseBody ...
		
		    return objectMapper.readValue(response.body().string(), TransactionStatusSyncResponse.class);
		} catch (IOException e) {
			logger.error("Could not fetch transaction result -> %s", e.getLocalizedMessage());
		    return null;
		}
	}




	@Override
	public CommonSyncResponse checkAccountBalance() {
		
		Logger logger = LoggerFactory.getLogger(DarajaApiImpl.class);
		CheckAccountBalanceRequest checkAccountBalanceRequest = new CheckAccountBalanceRequest();
		checkAccountBalanceRequest.setInitiator(mpesaConfiguration.getB2cInitiatorName());
		try {
			checkAccountBalanceRequest.setSecurityCredential(HelperUtility.getSecurityCredentials(mpesaConfiguration.getB2cInitiatorPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkAccountBalanceRequest.setCommandID(Constants.ACCOUNT_BALANCE_COMMAND);
		checkAccountBalanceRequest.setPartyA(mpesaConfiguration.getShortCode());
		checkAccountBalanceRequest.setIdentifierType(Constants.SHORT_CODE_IDENTIFIER);
		checkAccountBalanceRequest.setRemarks("Check Account Balance");
		checkAccountBalanceRequest.setQueueTimeOutURL(mpesaConfiguration.getB2cQueueTimeoutUrl());
		checkAccountBalanceRequest.setResultURL(mpesaConfiguration.getB2cResultUrl());
		
		AccessTokenResponse accessTokenResponse = getAccessToken();
		
		RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJson(checkAccountBalanceRequest)));
		
		Request request = new Request.Builder()
				.url(mpesaConfiguration.getCheckAccountBalanceUrl())
				.post(body)
				.addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, accessTokenResponse.getAccessToken()))
				.build();
		
		try {
			Response response = okHttpClient.newCall(request).execute();
			assert response.body() != null;
			// use Jackson to Decode the ResponseBody ...
			
			return objectMapper.readValue(response.body().string(), CommonSyncResponse.class);
		} catch (IOException e) {
			logger.error("Could not fetch the account balance -> %s", e.getLocalizedMessage());
			return null;
		}
		
	}




	@Override
	public StkPushSyncResponse performStkPushTransaction(InternalStkPushRequest internalStkPushRequest) {
		Logger logger = LoggerFactory.getLogger(DarajaApiImpl.class);
		
		ExternalStkPushRequest externalStkPushRequest = new ExternalStkPushRequest();
		externalStkPushRequest.setBusinessShortCode(mpesaConfiguration.getStkPushShortCode());
		
		String transactionTimestamp = HelperUtility.getTransactionTimestamp();
		String stkPushPassword = HelperUtility.getStkPushPassword(mpesaConfiguration.getStkPushShortCode(), mpesaConfiguration.getStkPassKey(), transactionTimestamp);
		
		externalStkPushRequest.setPassword(stkPushPassword);
		externalStkPushRequest.setTimestamp(transactionTimestamp);
		externalStkPushRequest.setTransactionType(Constants.CUSTOMER_PAYBILL_ONLINE);;
		externalStkPushRequest.setAmount(internalStkPushRequest.getAmount());
		externalStkPushRequest.setPartyA(internalStkPushRequest.getPhoneNumber());
		externalStkPushRequest.setPartyB(mpesaConfiguration.getStkPushShortCode());
		externalStkPushRequest.setPhoneNumber(internalStkPushRequest.getPhoneNumber());
		externalStkPushRequest.setCallBackURL(mpesaConfiguration.getStkPushRequestCallbackUrl());
		externalStkPushRequest.setAccountReference(HelperUtility.getTransactionUniqueNumber());
		externalStkPushRequest.setTransactionDesc(String.format("%s Transaction", internalStkPushRequest.getPhoneNumber()));
		
		AccessTokenResponse accessTokenResponse = getAccessToken();
		
		@SuppressWarnings("deprecation")
		RequestBody body = RequestBody.create(JSON_MEDIA_TYPE, Objects.requireNonNull(HelperUtility.toJson(externalStkPushRequest)));
		
		Request request = new Request.Builder()
				.url(mpesaConfiguration.getStkPushRequestUrl())
				.post(body)
				.addHeader(AUTHORIZATION_HEADER_STRING, String.format("%s %s", BEARER_AUTH_STRING, accessTokenResponse.getAccessToken()))
				.build();
		
		try {
			Response response = okHttpClient.newCall(request).execute();
			assert response.body() != null;
			// use Jackson to Decode the ResponseBody
			
			return objectMapper.readValue(response.body().string(), StkPushSyncResponse.class);
		} catch (IOException e) {
			logger.error(String.format("Could not perform the STK push request -> %s", e.getLocalizedMessage()));
			return null;
		}
		
	}
	
	
}
