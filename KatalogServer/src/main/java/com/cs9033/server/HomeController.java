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
import com.cs9033.server.utilities.Database;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Jedis jedis = Database.getJedis();

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Oops.. You reached a wrong place!!");

		jedis.set("ID", "Hello");
		model.addAttribute("serverTime", jedis.time().get(0));
		return "home";
	}

	@RequestMapping("/register.do")
	public @ResponseBody
	Response register(
			@RequestParam(value = "name", required = false) String name) {

		return null;
	}

}
