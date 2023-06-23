package com.paymentGateway.student.constant;

public interface ResponseMessage {

	// Statuses
	String SUCCESS = "success";
	String UNEXPECTED_ERROR = "unexpected.error";
	String NULL_POINTER_ERROR = "null.pointer.exception";


	// Auth module codes
	String PLAIN_TEXT_REQUIRED = "plain-text.required";
	String INVALID_API_KEY = "invalid.api-key";
	String MERCHANT_CODE_NOT_FOUND = "merchant.code.not.found";
	String MERCHANT_PUBLIC_KEY_NOT_FOUND = "merchant.public-key.not.found";
	String INVALID_TOKEN = "invalid.token";
	String NO_SIGNATURE_CONFIGURED = "no.signature.configured";
	String TOKEN_REQUIRED = "token.required";
	String TOKEN_EXPIRED = "token.expired";

	// SIGNATURE
	String SIGNATURE_REQUIRED = "signature.required";
	String INVALID_SIGNATURE_KEYS_MAPPING = "invalid.signature.keys.mapping";
	String INVALID_SIGNATURE = "invalid.signature";

	// MERCHANT AUTHORIZATION
	String INVALID_MERCHANT_API_TOKEN = "invalid.merchant.api.token";

	// Service Authorization
	String ACCESS_TO_SERVICE_NOT_AUTHOROZED = "access.to.service.not.authorozed";
	String ACCESS_TO_SERVICE_AUTHOROZATION_NOT_ACTIVE = "access.to.service.authorization.not.active";

	// Configuration Errors
	String ACCOUNT_NOT_CONFIGURED_FOR_THE_SERVICE = "account.not.configured.for.the.service";
	String ACCOUNT_NOT_FOUND_OR_INACTIVE = "account.not.found.or.inactive";
	String CHARGE_CONFIGURATION_ERRORS = "charge.configuration.errors";
	String CHARGE_INSUFFICIENT_FUNDS = "charge.insufficient.funds";
	String CHARGE_ACCOUNT_VALIDATION_ERRORRS = "charge.account.validation.errors";

	// General Processing Errors
	String CHARGE_PROCESSING_ERRORS = "charge.processing.errors";
	String DUPLICATE_TRANSACTION_REFERENCE = "duplicate.transaction.reference";
	String INTERNAL_AUTHENTICATION_ERRORS = "internal.authentication.errors";
	String INTERNAL_PROCESSING_ERRORS = "internal.processing.errors";
	String THIRDPARTY_GENERAL_PROCESSING_ERRORS = "thirdparty.general.processing.errors";
	String THIRDPARTY_NOT_AVAILABLE_ERRORS = "thirdparty.not.available.errors";
	String UNABLE_TO_PARSE_REQUEST_ERRORS = "unable.to.parse.request.errors";
	String DATABASE_ACCESS_ERRORS = "database.access.errors";
	String CURRENCY_CONVERSION_ERRORS = "currency.conversion.errors";

	String UNKNOWN_PROCESSING_STATE = "unknown.processing.state";

	// Limits Error
	String DAILY_TRANSACTIONS_LIMITS_EXCEEDED = "daily.transactions.limit.exceeded";
	String MIN_AMOUNT_LIMIT_NOT_REACHED = "min.amount.limit.not.reached";
	String MAX_AMOUNT_LIMIT_EXCEEDED = "min.amount.limit.not.reached";


	// Accounts validation errors
	String ACCOUNT_DETAILS_ERROR = "account.details.error";
	String ACCOUNT_NOT_FOUND_ERROR = "account.not.found.error";
	String ACCOUNT_INSUFFICIENT_FUNDS = "account.insufficient.funds";

	// Statements
	String ACCOUNT_MISSING_TRANSACTIONS = "account.missing.transactions";
	String STATEMENT_INVALID_DATE_RANGE = "statement.invalid.date.range";

	// Billers
	String COULD_NOT_FETCH_BILLERS = "could.not.fetch.billers";
	String BILLER_NOT_FOUND = "biller.not.found";
	String INVALID_BILLER_REFERENCE = "invalid.biller.reference";
	String BILL_DOES_NOT_EXIST = "bill.does.not.exist";
	String BILL_PAYMENT_COULD_NOT_COMPLETE = "bill.payment.could.not.complete";
	String BILL_VALIDATION_COULD_NOT_COMPLETE = "bill.validation.could.not.complete";

	// Airtime
	String AIRTIME_PURCHASE_INVALID_TELCO = "airtime.purchase.invalid.telco";
	String AIRTIME_PURCHASE_INVALID_MOBILE_NUMBER = "airtime.purchase.invalid.mobile.number";
	String AIRTIME_PURCHASE_COULD_NOT_COMPLETE = "airtime.purchase.could.not.complete";

	// Till - Pay
	String COULD_NOT_FETCH_EAZZY_PAY_MERCHANTS = "could.not.fetch.eazzy.pay.merchants";
	String TILL_NUMBER_IS_NOT_CORRECT = "till.number.is.not.correct";
	String TILL_PAY_COULD_NOT_COMPLETE = "till.pay.could.not.complete";

	// Send Money Wallets
	String INVALID_SEND_MONEY_WALLET = "invalid.send.money.wallet";
	String SEND_MONEY_TRANSACTION_ACCEPTED = "send.money.transaction.accepted";
	String SEND_MONEY_WALLET_COULD_NOT_COMPLETE = "send.money.wallet.could.not.complete";
	String SEND_MONEY_WALLET_AMOUNT_LESS = "send.money.wallet.amount.less";
	String SEND_MONEY_WALLET_AMOUNT_MORE = "send.money.wallet.amount.more";

	// Pesalink
	String PESALINK_COULD_NOT_COMPLETE = "pesalink.could.not.complete";
	String PESALINK_AMOUNT_LESS = "pesalink.amount.less";
	String PESALINK_AMOUNT_MORE = "pesalink.amount.more";

	// IFT
	String IFT_DESTINATION_ACCOUNT_DOES_NOT_EXIST = "ift.destination.account.does.not.exist";
	String IFT_SOURCE_ACCOUNT_INSUFFICIENT_BALANCE = "ift.source.account.insufficient.balance";

	// RTGS
	String RTGS_INVALID_RECEPIENT_BANK_CODE = "rtgs.invalid.recepient.bank.code";

	// SWIFT
	String SWIFT_TRANSFER_DESTINATION_CURRENCY_MISMATCH = "swift.transfer.destination.currency.mismatch";
	String SWIFT_INVALID_INCOUNTRY_DESTINATION_CURRENCY = "swift.invalid.incountry.destination.currency";
	String SWIFT_INVALID_BIC_CODE = "swift.invalid.bic.code";

	// Transaction Query
	String TRANSACTION_REFERENCE_NOT_FOUND = "transaction.reference.not.found";

	// IPRS
	String IPRS_UNABLE_TO_VALIDATE = "iprs.unable.to.validate";



}
