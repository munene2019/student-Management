package com.paymentGateway.student.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AppConstants {

	String PACKAGE_NAME = "africa.finserve.jengaadminapisv3";
	String DEFAULT_USER = "Super Admin";
	String CLOSED_HTTP_CODE = "JENGA-SYSTEM-CLOSED-HTTP-CODE";

	class Pagination {

		// Pagination default settings
		public static final String DEFAULT_ORDER_DIRECTION = "DESC";
		public static final String DEFAULT_PAGE_NUMBER = "0";
		public static final String DEFAULT_PAGE_SIZE = "1000000000"; // Set default size to 1 billion records
		public static final String DEFAULT_ORDER_BY = "id";
		public static final String DEFAULT_ORDER_BY_CODE = "code";
		public static final int MAX_PAGE_SIZE = 50;
	}

	class LogCategory {

		public static final String ORDER_LOG = "ORDER";
		public static final String TRANSACTION_LOG = "TRANSACTION";
		public static final String NOTIFICATION_LOG = "NOTIFICATION";
		public static final String MERCHANT_ON_BOARDING = "MERCHANT_ON_BOARDING";
		public static final String USER_LOG = "USER";
		public static final String UNIVERSAL_LOG = "UNIVERSAL";
		public static final String AUTH_LOGS = "AUTH";
		public static final String NETWORK_LOGS = "NETWORK";

	}

	class LogType {

		public static final String REQUEST = "REQUEST";
		public static final String RESPONSE = "RESPONSE";
		public static final String SYSTEM_ERROR = "SYSTEM_ERROR";
		public static final String STATE = "STATE";

		public static final List<String> logTypes = new ArrayList<>();

		static {
			logTypes.add(REQUEST);
			logTypes.add(RESPONSE);
			logTypes.add(SYSTEM_ERROR);
			logTypes.add(STATE);
		}
	}

	class Priority {

		public static final Integer PRIORITY_1 = 1;
		public static final Integer PRIORITY_2 = 2;
		public static final Integer PRIORITY_3 = 3;
		public static final List<Integer> priorities = new ArrayList<>();

		static {
			priorities.add(PRIORITY_1);
			priorities.add(PRIORITY_2);
			priorities.add(PRIORITY_3);
		}
	}

	class TokenAuthType {
		public static final String MERCHANT = "MERCHANT";
		public static final String USER = "USER";

	}

	class ResponseStatus {
		public static final Boolean TRUE = Boolean.TRUE;
		public static final Boolean FALSE = Boolean.FALSE;
	}

	class KafkaEvent {

		public static final String LOG_EVENT = "log_event";
		public static final String CREATE_SMS_EVENT = "create_sms_event";
		public static final String CREATE_EMAIL_EVENT = "create_email_event";
		public static final String CREATE_AUDIT = "create_audit";
	}

	class UserType {
		public static final String SYSTEM_USER = "SYS_USER";
		public static final String EXTERNAL = "EXTERNAL";
		public static final String INTERNAL = "INTERNAL";
		// For token purpose only
		public static final String MERCHANT = "MERCHANT";

	}

	class NotificationStatus {

		public static final String PENDING = "PD";
		public static final String SENT = "ST";
	}

	class ApprovalType {

		public static final String USER = "USER";
		public static final String ROLE = "ROLE";
	}

	class NotificationType {

		public static final String EMAIL = "EMAIL";
		public static final String SMS = "SMS";
		public static final String PUSH_NOTIFICATION = "PUSH";
		public static final List<String> notificationTypes = new ArrayList<>();

		static {
			notificationTypes.add(EMAIL);
			notificationTypes.add(SMS);
			notificationTypes.add(PUSH_NOTIFICATION);
		}
	}

	class NotificationRequest {

		public static final String PASSWORD_RESET = "resetPassword";
		public static final String ON_BOARDING_SUMMARY = "onboardingSummary";
		public static final String NEW_USER_TO_ORG = "newUserToOrg";
		public static final String NEW_USER_ACCOUNT_DETAILS = "newUserDetails";
		public static final String INVITATION_ACCEPTED = "invitationAccepted";
		public static final String CONFIRM_PASSWORD_CHANGE = "confirmPasswordChange";
		public static final String CONFIRM_EMAIL = "confirmEmail";
		public static final String COMPLETE_APPLICATION = "completeApplication";

		public static final List<String> requests = new ArrayList<>();

		static {
			requests.add(PASSWORD_RESET);
			requests.add(ON_BOARDING_SUMMARY);
			requests.add(NEW_USER_TO_ORG);
			requests.add(NEW_USER_ACCOUNT_DETAILS);
			requests.add(INVITATION_ACCEPTED);
			requests.add(CONFIRM_PASSWORD_CHANGE);
			requests.add(CONFIRM_EMAIL);
			requests.add(COMPLETE_APPLICATION);

		}
	}

	class ContentType {
		public static final String APPLICATION_JSON = "application/json";
		public static final String APPICATION_XML = "application/xml";

	}

	class ValueType {
		public static final String FIXED = "FXD";
		public static final String PERCENTAGE = "PRC";

	}

	class ChargeType {
		public static final String IPN = "IPN";
		public static final String TRANSACTIONAL = "TXN";
		public static final String API = "API";

	}

	class ProductType {
		public static final String IPN = "IPN";
		public static final String API = "API";

	}

	class EntityTypeCode {
		public static final String MERCHANT = "MCHT";
		public static final String CURRENCY = "CURR";
		public static final String COUNTRY = "CTRY";
		public static final String CITY = "CITY";
		public static final String INDUSTRY = "IDRY";
		public static final String SERVICES = "SRVS";
		public static final String CARDACQUIRER = "CAQR";
		public static final String PAYMENTTYPE = "PMTT";
		public static final String PRODUCT = "PRDT";
		public static final String SUBPRODUCT = "SPRT";
		public static final String BANKADDRESS = "BADR";
		public static final String BANKBRANCHADDRESS = "BBDR";
		public static final String SETTLEMENTMODES = "STMD";
		public static final String SUBBUSINESSCATEGORY = "SBCY";
		public static final String BUSINESSCATEGORY = "BSCY";
		public static final String BUSINESSTYPES = "BTYP";
		public static final String DOCUMENTTYPES = "DCMT";
		public static final String SETTLEMENTACCOUNT = "SETL";
		public static final String MERCHANTPRODUCT = "MCTP";
		public static final String MOBILEOPERATOR = "MBOP";
		public static final String MERCHANT_SERVICE = "MSVC";
	}

	class SystemCode {
		public static final Integer REQUEST_TIMEOUT = 408;
		public static final Integer GATEWAY_TIMEOUT = 504;
		public static final Integer BAD_REQUEST = 400;
		public static final Integer UNAUTHORIZED = 401;
		public static final Integer FORBIDDEN_ACCESS = 403;
		public static final Integer CONFLICT = 409;
		public static final Integer OK = 200;
		public static final Integer NO_CONTENT = 204;
		public static final Integer RESOURCE_NOT_FOUND = 404;
		public static final Integer UNKNOWN_ERROR = 900;
		public static final Integer NULL_POINTER_EXCEPTION = 700;
		public static final Integer NUMBER_FORMAT_EXCEPTION = 701;
		public static final Integer ILLEGAL_ARGUMENT_EXCEPTION = 702;
		public static final Integer RUNTIME_EXCEPTION = 703;
		public static final Integer ILLEGAL_STATE_EXCEPTION = 704;
		public static final Integer NO_SUCH_METHOD_EXCEPTION = 705;
		public static final Integer CLASS_CAST_EXCEPTION = 706;
		public static final Integer EXCEPTION = 707;
		public static final Integer PARSE_EXCEPTION = 708;
		public static final Integer INVOCATION_TARGET_EXCEPTION = 709;
		public static final Integer BAD_KAFKA_RESPONSE = 800;
	}

	class StatusValue {
		public static final String PENDING = "PEND";
		public static final String ACTIVE = "ACTV";
		public static final String DELETED = "DEL";
		public static final String INACTIVE = "IACTV";
		public static final String APPROVED = "APPR";
		public static final String PARTIALLY_APPROVED = "PAPP";
		public static final String REJECTED = "REJ";
		public static final String PAID = "PAID";
		public static final String SUCCESS = "SUC";

	}

	class HttpStatusCode {
		public static final Integer OK = org.springframework.http.HttpStatus.OK.value();
		public static final Integer UNAUTHORIZED = org.springframework.http.HttpStatus.UNAUTHORIZED.value();
		public static final Integer BAD_REQUEST = org.springframework.http.HttpStatus.BAD_REQUEST.value();
		public static final Integer FORBIDDEN_ACCESS = org.springframework.http.HttpStatus.FORBIDDEN.value();
		public static final Integer NOT_FOUND = org.springframework.http.HttpStatus.NOT_FOUND.value();
		public static final Integer SERVER_ERROR = org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR.value();

	}

	class HttpStatus {

		public static org.springframework.http.HttpStatus getStatus(int code) {

			if (code == 400) {
				return org.springframework.http.HttpStatus.BAD_REQUEST;
			} else if (code == 401) {
				return org.springframework.http.HttpStatus.UNAUTHORIZED;
			} else if (code == 403) {
				return org.springframework.http.HttpStatus.FORBIDDEN;
			} else if (code == 404) {
				return org.springframework.http.HttpStatus.NOT_FOUND;
			} else if (code == 500) {
				return org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
			} else {
				return org.springframework.http.HttpStatus.OK;
			}
		}

	}

	class HttpResponseCode {
		public static final Integer OK = 200;
		public static final Integer BAD_REQUEST = 400;
		public static final Integer UNAUTHORIZED = 401;
		public static final Integer FORBIDDEN = 403;
		public static final Integer INTERNAL_SERVER_ERROR = 500;
	}

	class DocumentType {
		public static final String idCardFile = "idCardFile";
		public static final String passportFile = "passportFile";
		public static final String termsFile = "termsFile";
		public static final String cancellationPolicyFile = "cancellationPolicyFile";
		public static final String certificateOfRegistrationFile = "certificateOfRegistrationFile";
		public static final String certificateOfIncorporationFile = "certificateOfIncorporationFile";
		public static final String patnershipDeedFile = "patnershipDeedFile";
	}

	class DownloadFileType {

		public static final String EXCEL = "excel";
		public static final String PDF = "pdf";
		public static final String CSV = "csv";
		public static final String TEXT = "text";
	}

	class CountryCurrency {

		public static final Map<String, String> countryCurrencyMap = new HashMap<>();
		static {
			countryCurrencyMap.put("KE", "KES");
			countryCurrencyMap.put("US", "USD");
			countryCurrencyMap.put("TZ", "TZS");
			countryCurrencyMap.put("JP", "JPY");
		}
	}

	class APIS {
		public static final double AMOUNT = 0.0;
		public static final String CURRENCY = "KES";
	}

	class SecureKeys {

		public static final List<String> KEYSLIST = new ArrayList<>();
		public static final int MASKS = 7;

		static {
			KEYSLIST.add("client_secret");
			KEYSLIST.add("password");
			KEYSLIST.add("accessToken");
			KEYSLIST.add("access_token");
			KEYSLIST.add("Bearer ");
			KEYSLIST.add("consumerSecret");
		}

	}

	class Blacklist {

		public static final List<String> WORDS = new ArrayList<>();

		static {
			WORDS.add("SELECT");
			WORDS.add("UPDATE");
			WORDS.add("INSERT");
			WORDS.add("DELETE");
			WORDS.add("GRANT");
			WORDS.add("ALTER");
			WORDS.add("REVOKE");
			WORDS.add("UNION");
			WORDS.add("DROP");
			WORDS.add("TRUNCATE");
			WORDS.add("IFRAME");
			WORDS.add(".EXE");

		}
	}

	enum TransactionStatus {

		SUCCESS(2, "Success"), FAILED(1, "Failed"), INITIATED(0, "Initiated"), AWAITING(-1, "Awaiting"),
		UNKNOWN(-2, "Unknown");

		private int value;
		private String description;

		public String getDescription() {
			return description;
		}

		public int getValue() {
			return value;
		}

		private TransactionStatus(int value, String description) {
			this.value = value;
			this.description = description;
		}
	}

}
