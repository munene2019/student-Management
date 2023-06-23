package com.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Dispatcher {

	private static String LOG_TOPIC;

	private static String APPLICATION_NAME;

	@Value("${topic.log}")
	public void setLogTopic(String logTopic) {
		Dispatcher.LOG_TOPIC = logTopic;
	}

	@Value("${spring.application.name}")
	public void setAppName(String appName) {
		Dispatcher.APPLICATION_NAME = appName;
	}

	static MessageProducer messageProducer;

	public Dispatcher(MessageProducer messageProducer) {
		Dispatcher.messageProducer = messageProducer;
	}

	public static void logSystemErrorMsg(String logInfo, Integer code, String otherDetails, String logLocation) {
		logMessage("SystemError", code, 1, logInfo, otherDetails, logLocation);
	}

	public static void logMessage(String logType, Integer code, Integer priority, String logInfo, String otherDetails,
			String logLocation) {

		// Format data to be sent
		// ProducerEvent event;

//		event = new ProducerEvent(AppConstants.KafkaEvent.LOG_EVENT,
//				LogKafkaModel.map("Billing_service", logType, code, priority, logInfo, otherDetails, logLocation));
		// publish event to a topic
		System.out.println("TOPIC--------------------------------------------------" + LOG_TOPIC);
		messageProducer.publish(LOG_TOPIC, "DATA");
	}

	public static void sendMessage(String msg) {
		messageProducer.publish("test", msg);
	}

}
