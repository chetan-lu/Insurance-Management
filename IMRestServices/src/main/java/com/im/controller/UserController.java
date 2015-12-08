package com.im.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.im.collection.UserDetails;
import com.im.entity.Registration;
import com.im.service.UserDetailsService;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserDetailsService registerService;

	@RequestMapping(value = "/imregister", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Registration registerUser(@RequestBody Registration registration) {

		registerService.insertUser(
				new UserDetails(registration.getFirstName(), registration.getLastName(), registration.getAddress(),
						registration.getState(), registration.getCity(), registration.getContact_no(),
						registration.getEmail(), registration.getUsername(), registration.getPassword()));
		return registration;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserDetails deleteUser(@RequestBody UserDetails registration) {

		registerService.deleteUser(registration.getUsername());
		return registration;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody String loginUser(@RequestBody com.im.entity.Registration user) {
		UserDetails userDetails = registerService.getUser(user.getUsername(), user.getPassword());
		if (userDetails != null) {
			return "{status: true}";
		} else {
			return "{status: false}";

		}
	}

}