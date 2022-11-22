package com.student.student.helpers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.Map.Entry;

@Service
public class RestcallsHelper {

	protected static final String EMPTY_BODY_RESPOSE = "A terminating resource not found";
	protected static final String ERROR_404_MESSAGE = "A terminating resource not found";
	//protected static final String USER_AGENT = AppConstants.CLOSED_HTTP_CODE;

	//@Value(value = "${app.config.connection.timeout}")
	private int connectionTimeout;

	//@Value(value = "${app.config.read.timeout}")
	private int readTimeout;

	public RestcallsHelper() {
	}

	public RestcallsHelper(int connectionTimeout, int readTimeout) {
		this.connectionTimeout = connectionTimeout;
		this.readTimeout = readTimeout;
	}

	protected ResponseEntity<String> httpsHelper(HttpMethod method, String url, MediaType content,
			String uniqueIdentifier) throws Exception {

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(content);
		headers.add("User-Agent", "USER_AGENT");

		HttpEntity<String> requestEntityApp = new HttpEntity<>("{}", headers);

		// SimpleClientHttpRequestFactory simpleFactory = new
		// SimpleClientHttpRequestFactory();
		HttpComponentsClientHttpRequestFactory simpleFactory = new HttpComponentsClientHttpRequestFactory();

		simpleFactory.setConnectTimeout(connectionTimeout);
		simpleFactory.setReadTimeout(readTimeout);
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(simpleFactory);

		RestTemplate restTemplate = new RestTemplate(factory);
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RestTemplateLoggingInterceptor(uniqueIdentifier, url));
		restTemplate.setInterceptors(interceptors);

		final ResponseEntity<String> apisResponse = restTemplate.exchange(url, method, requestEntityApp, String.class);

		return apisResponse;

	}

	protected ResponseEntity<String> httpsHelper(HttpMethod method, String url, String apiKey, String body,
			MediaType content, String uniqueIdentifier) throws Exception {
		System.out.println("Inside helper");
		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(content);
		//headers.setBearerAuth(token);
		headers.add("Api-Key", apiKey);

		HttpEntity<String> requestEntityApp = new HttpEntity<>(body, headers);

//		RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(connectionTimeout))
//				.setReadTimeout(Duration.ofMillis(readTimeout)).build();
try {
	HttpComponentsClientHttpRequestFactory simpleFactory = new HttpComponentsClientHttpRequestFactory();
	simpleFactory.setConnectTimeout(1000);
	simpleFactory.setReadTimeout(1000);

	ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(simpleFactory);

	RestTemplate restTemplate = new RestTemplate(factory);
	List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
//		if (CollectionUtils.isEmpty(interceptors)) {
//			interceptors = new ArrayList<>();
//		}
//		interceptors.add(new RestTemplateLoggingInterceptor(uniqueIdentifier, url));
//		restTemplate.setInterceptors(interceptors);
	System.out.println("helper3 url" + url);
	System.out.println("helper3 body " + requestEntityApp);
	final ResponseEntity<String> apisResponse = restTemplate.exchange(url, method, requestEntityApp, String.class);

	System.out.println("Response Body........." + apisResponse);

	return apisResponse;
}
catch (Exception ex){
	final ResponseEntity<String> apisResponse=null;
	System.out.println("EXCEPTION............."+ex);
	return apisResponse;
}

	}

	protected ResponseEntity<String> httpsHelper(HttpMethod method, String url, String body, MediaType content,
			String uniqueIdentifier) throws Exception {
		System.out.println("Inside helper");

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(content);
		headers.add("User-Agent", "USER_AGENT");

		HttpEntity<String> requestEntityApp = new HttpEntity<>(body, headers);

//		RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(connectionTimeout))
//				.setReadTimeout(Duration.ofMillis(readTimeout)).build();

		HttpComponentsClientHttpRequestFactory simpleFactory = new HttpComponentsClientHttpRequestFactory();
		simpleFactory.setConnectTimeout(connectionTimeout);
		simpleFactory.setReadTimeout(readTimeout);
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(simpleFactory);

		RestTemplate restTemplate = new RestTemplate(factory);
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RestTemplateLoggingInterceptor(uniqueIdentifier, url));
		restTemplate.setInterceptors(interceptors);

		final ResponseEntity<String> apisResponse = restTemplate.exchange(url, method, requestEntityApp, String.class);

		String apiResponse = apisResponse.getBody();

		return apisResponse;

	}

	protected ResponseEntity<String> httpsHelper(HttpMethod method, String url, String body,
			Map<String, String> headersMap, MediaType content, String uniqueIdentifier) throws Exception {

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(content);
		for (Entry<String, String> entry : headersMap.entrySet()) {
			headers.set(entry.getKey(), entry.getValue());
		}
		headers.add("User-Agent", "USER_AGENT");

		HttpEntity<String> requestEntityApp = new HttpEntity<>(body, headers);

//		RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(connectionTimeout))
//				.setReadTimeout(Duration.ofMillis(readTimeout)).build();

		HttpComponentsClientHttpRequestFactory simpleFactory = new HttpComponentsClientHttpRequestFactory();
		simpleFactory.setConnectTimeout(connectionTimeout);
		simpleFactory.setReadTimeout(readTimeout);
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(simpleFactory);

		RestTemplate restTemplate = new RestTemplate(factory);
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RestTemplateLoggingInterceptor(uniqueIdentifier, url));
		restTemplate.setInterceptors(interceptors);

		final ResponseEntity<String> apisResponse = restTemplate.exchange(url, method, requestEntityApp, String.class);

		return apisResponse;

	}

	protected ResponseEntity<String> httpsHelper(HttpMethod method, String url, HashMap<String, Object> params,
			MediaType contentType, String uniqueIdentifier) throws Exception {

		String urlparams = convertToHashMaptoQueryString(params);

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(contentType);
		headers.add("User-Agent", "USER_AGENT");

		HttpEntity<String> requestEntityApp = new HttpEntity<>(urlparams, headers);

//		RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(connectionTimeout))
//				.setReadTimeout(Duration.ofMillis(readTimeout)).build();

		HttpComponentsClientHttpRequestFactory simpleFactory = new HttpComponentsClientHttpRequestFactory();
		simpleFactory.setConnectTimeout(connectionTimeout);
		simpleFactory.setReadTimeout(readTimeout);
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(simpleFactory);

		RestTemplate restTemplate = new RestTemplate(factory);
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RestTemplateLoggingInterceptor(uniqueIdentifier, url));
		restTemplate.setInterceptors(interceptors);

		final ResponseEntity<String> apisResponse = restTemplate.exchange(url, method, requestEntityApp, String.class);

		return apisResponse;

	}

	protected ResponseEntity<String> httpsHelper(HttpMethod method, String url, HashMap<String, Object> params,
			String uniqueIdentifier) {

		String urlparams = convertToHashMaptoQueryString(params);

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();

		// headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON
		// }));
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("User-Agent", "USER_AGENT");

		HttpEntity<String> requestEntityApp = new HttpEntity<>(urlparams, headers);

//		RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(connectionTimeout))
//				.setReadTimeout(Duration.ofMillis(readTimeout)).build();

		HttpComponentsClientHttpRequestFactory simpleFactory = new HttpComponentsClientHttpRequestFactory();
		simpleFactory.setConnectTimeout(connectionTimeout);
		simpleFactory.setReadTimeout(readTimeout);
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(simpleFactory);

		RestTemplate restTemplate = new RestTemplate(factory);
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RestTemplateLoggingInterceptor(uniqueIdentifier, url));
		restTemplate.setInterceptors(interceptors);

		final ResponseEntity<String> apisResponse = restTemplate.exchange(url, method, requestEntityApp, String.class);

		return apisResponse;

	}

	private static String convertToHashMaptoQueryString(HashMap<String, Object> params) {

		StringBuilder sb = new StringBuilder();

		Iterator<?> iter = params.entrySet().iterator();
		while (iter.hasNext()) {
			if (sb.length() > 0) {
				sb.append('&');
			}
			Entry<?, ?> entry = (Entry<?, ?>) iter.next();
			sb.append(entry.getKey()).append("=").append(entry.getValue());
		}

		return sb.toString();
	}

}
