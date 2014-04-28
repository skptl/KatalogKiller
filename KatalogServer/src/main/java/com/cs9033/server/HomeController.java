package com.cs9033.server;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.cs9033.server.models.Response;
import com.cs9033.server.parsers.ResponseParser;
import com.cs9033.server.utilities.Database;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@RequestMapping(value = "/")
	public @ResponseBody
	String home(@RequestParam(defaultValue = "ERROR!") String data) {

		return data;
	}

	@RequestMapping(value = "/register.s", method = RequestMethod.GET)
	public @ResponseBody
	String register(@RequestParam(defaultValue = "ERROR!") String data) {

		return data;
	}

	@RequestMapping(value = "/unsubscribe.s", method = RequestMethod.GET)
	public @ResponseBody
	String unsubscribe(
			@RequestParam(defaultValue = "ERROR!", required = true) String data) {

		return data;
	}

	@RequestMapping(value = "/suggestions.s", method = RequestMethod.GET)
	public @ResponseBody
	String suggestions(
			@RequestParam(defaultValue = "ERROR!", required = true) String data) {

		return data;
	}

	@RequestMapping(value = "/authenticate.s", method = RequestMethod.GET)
	public @ResponseBody
	String authenticate(
			@RequestParam(defaultValue = "ERROR!", required = true) String data) {

		return data;
	}
	
	@RequestMapping(value = "/update.s", method = RequestMethod.GET)
	public @ResponseBody
	String update(
			@RequestParam(defaultValue = "ERROR!", required = true) String data) {

		return data;
	}

}
