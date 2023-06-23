package com.proxy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kafka")
public class KafkaController {

	// https://www.baeldung.com/spring-kafka

	// @Autowired
	Dispatcher dispatcher;

	@GetMapping("/index")
	@ResponseBody
	public String index() {
		return "This is Kafla";
	}

	@GetMapping("/send")
	@ResponseBody
	public String send() {
		// dispatcher.sendMessage("Good things");
		// dispatcher.logMessage("SystemError", 1, 1, "info", "Other Info", "Log
		// Location");
		runner();
		return "done";
	}

	public static void runner() {
		System.out.println("{{{{{{{{{{{{{{{{{{{{{{{}}}}}}}}}}}}}}}}}}}}}}}");
		Dispatcher.sendMessage("We are testing ");
	}

}
